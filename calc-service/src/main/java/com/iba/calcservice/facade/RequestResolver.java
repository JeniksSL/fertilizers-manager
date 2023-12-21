package com.iba.calcservice.facade;



import com.iba.calcservice.model.*;
import com.iba.fertilizersmanager.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RequestResolver {

    int maxDifference = 5;
    int maxLength = 50;
    int minRate = 3;




    public CalculationResponseDto getResolveCases(CalculationRequestDto calculationRequestDto) {
        CalculationResponseDto calculationResponseDto = CalculationResponseDto
                .builder()
                .cases( new ArrayList<>())
                .build();
        TreeMap<Long, LogicProduct> productTreeMap=new TreeMap<>();
        calculationRequestDto.products().forEach(pcd -> {
            Long productId = pcd.getId();
            TreeMap<Long, Float> substanceMap = new TreeMap<>();
            pcd.getSubstanceSet().forEach(sc->substanceMap.put(sc.getId(), sc.getContent().floatValue()));
            productTreeMap.put(productId, new LogicProduct(productId, pcd.getPrice().floatValue(), 0F, substanceMap));
        });
        TreeMap<Long, Short> substanceWeights =new TreeMap<>();
        calculationRequestDto.substances().forEach(sc->substanceWeights.put(sc.getId(), sc.getContent().shortValue()));


        recursiveResolution(new LogicCase(productTreeMap), substanceWeights, calculationRequestDto, calculationResponseDto);
        return calculationResponseDto;
    }


    private boolean isCanBeSolved(LogicCase logicCase,  TreeMap<Long, Short> substanceWeights) {
        List<Long> substanceTypes = new ArrayList<>(substanceWeights.keySet());
        logicCase.getProductMap()
                .values()
                .stream()
                .map(LogicProduct::getSubstanceMap)
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .forEach(substanceTypes::remove);
        return substanceTypes.isEmpty();
    }

    private boolean isValid(LogicCase logicCase, TreeMap<Long, Short> substanceWeights) {
        int dif=substanceWeights.entrySet()
                .stream()
                .mapToInt((entry)->Math.abs(entry.getValue() - getWeight(logicCase, entry.getKey())))
                .sum();
        return dif<= maxDifference;
    }

    private short getWeight(LogicCase logicCase, Long substanceId) {
        return (short) logicCase
               .getProductMap().values()
               .stream()
               .mapToDouble(logicProduct -> logicProduct.getRate()*Optional.ofNullable(logicProduct.getSubstanceMap().get(substanceId)).orElse(0F))
               .sum();
    }

    private void recursiveResolution(LogicCase startCase, TreeMap<Long, Short> substanceWeights, CalculationRequestDto calculationRequestDto, CalculationResponseDto calculationResponseDto) {

        if (isComplete(calculationResponseDto)) return;
        if (isCanBeSolved(startCase, substanceWeights)) {
            TreeSet<LogicCase> cases = new TreeSet<>();
            NativeFinder.nativeSolve(startCase, substanceWeights, true);
            if (!isValid(startCase, substanceWeights)) {
                NativeFinder.nativeSolve(startCase, substanceWeights, false);
            }
            if (isValid(startCase, substanceWeights)) {
                if (isCleanCase(startCase))  addCaseToResponse(startCase ,calculationRequestDto, calculationResponseDto);
                cases.addAll(getNextCases(startCase));
            }
            cases.forEach(logicCase -> recursiveResolution(logicCase, substanceWeights, calculationRequestDto,calculationResponseDto));
        }
    }
    private void addCaseToResponse(LogicCase logicCase, CalculationRequestDto calculationRequestDto, CalculationResponseDto calculationResponseDto){

        List<SubstanceCompact> substanceCompacts = calculationRequestDto.substances().stream().map(SubstanceCompact::getId).map(id->new SubstanceCompact(id, BigDecimal.valueOf(getWeight(logicCase, id)))).toList();
        List<ProductCompactDto> productCompactDtos = calculationRequestDto
                .products()
                .stream()
                .map(pcd ->
                    Optional.ofNullable(logicCase.getProductMap().get(pcd.getId())).map(LogicProduct::getRate)
                            .filter(value->value>0)
                            .map(BigDecimal::valueOf)
                            .map(value->new ProductCompactDto(pcd.getId(), pcd.getPrice(), value, null))
                            .orElse(null)
                ).filter(Objects::nonNull)
                .toList();
        BigDecimal totalPrice = productCompactDtos.stream().map(pcd->pcd.getPrice().multiply(pcd.getRate())).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        CalculationCaseDto calculationCaseDto = CalculationCaseDto
                .builder()
                .totalPrice(totalPrice)
                .products(productCompactDtos)
                .substances(substanceCompacts)
                .build();
        calculationResponseDto.getCases().add(calculationCaseDto);

    }

    private boolean isComplete(CalculationResponseDto calculationResponseDto) {
        return maxLength<=Optional.ofNullable(calculationResponseDto.getCases()).map(Collection::size).orElse(0);
    }

    private boolean isCleanCase(LogicCase startCase) {
        return startCase.getProductMap().values().stream().mapToDouble(LogicProduct::getRate).noneMatch(rate->rate>0&&rate<minRate);
    }

    private Collection<LogicCase> getNextCases(LogicCase startCase) {

        TreeSet<LogicCase> cases = new TreeSet<>();
        for (LogicProduct lp : startCase.getProductMap().values()) {
            if (lp.getRate() > 0) {
                TreeMap<Long,LogicProduct> productTreeMap = new TreeMap<>(startCase.getProductMap());
                productTreeMap.remove(lp.getId());
                productTreeMap.replaceAll((k, v)->getCopy(v));
                LogicCase nextCase = new LogicCase(productTreeMap);
                cases.add(nextCase);
            }
        }
        return cases;
    }

    private LogicProduct getCopy(LogicProduct logicProduct){
        return new LogicProduct(logicProduct.getId(), logicProduct.getPrice(), logicProduct.getRate(), logicProduct.getSubstanceMap());
    }


}
