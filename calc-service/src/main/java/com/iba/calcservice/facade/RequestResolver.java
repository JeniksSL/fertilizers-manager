package com.iba.calcservice.facade;



import com.iba.calcservice.model.*;
import com.iba.fertilizersmanager.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestResolver {

    int maxDifference = 5;
    int maxLength = 50;
    int minRate = 5;




    public CalculationResponseDto getResolveCases(CalculationRequestDto calculationRequestDto, Integer length) {
        CalculationResponseDto calculationResponseDto = CalculationResponseDto
                .builder()
                .unitsType(calculationRequestDto.unitsType())
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
        calculationResponseDto.setCases(calculationResponseDto.getCases().stream().sorted(Comparator.comparing(CalculationCaseDto::totalPrice)).toList());
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

    private void recursiveResolution(LogicCase allCases, TreeMap<Long, Short> substanceWeights, CalculationRequestDto calculationRequestDto, CalculationResponseDto calculationResponseDto) {
        if (isComplete(calculationResponseDto)) return;
        if (isCanBeSolved(allCases, substanceWeights)) {
            TreeSet<LogicCase> cases = new TreeSet<>();
            NativeFinder.nativeSolve(allCases, substanceWeights, true);
            if (!isValid(allCases, substanceWeights)) {
                NativeFinder.nativeSolve(allCases, substanceWeights, false);
            }
            cleanCase(allCases);
            if (isValid(allCases, substanceWeights)) {
                if (!checkIfExists(calculationResponseDto, allCases)&isCleanCase(allCases)) addCaseToResponse(allCases ,calculationRequestDto, calculationResponseDto);
                cases.addAll(getNextCases(allCases));
            }
            cases.forEach(logicCase -> recursiveResolution(logicCase, substanceWeights, calculationRequestDto,calculationResponseDto));
        }
    }
    private void recursiveQueuedResolution(List<LogicCase> allCases, TreeMap<Long, Short> substanceWeights, CalculationRequestDto calculationRequestDto, CalculationResponseDto calculationResponseDto) {


    }




    private void cleanCase(LogicCase startCase) {
        startCase.getProductMap().values().stream().filter(it->it.getRate()<minRate).forEach(it->it.setRate(0));
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
                            .map(
                                    value->new ProductCompactDto(
                                            pcd.getId(),
                                            pcd.getPrice(),
                                            value,
                                            pcd.getSubstanceSet().stream().map(it->new SubstanceCompact(it.getId(), it.getContent().multiply(value))).collect(Collectors.toSet()))
                            )
                            .orElse(null)
                ).filter(Objects::nonNull)
                .toList();
        BigDecimal totalPrice = productCompactDtos
                .stream()
                .map(pcd->pcd
                        .getPrice()
                        .multiply(pcd.getRate())
                        .divide(BigDecimal.valueOf(calculationRequestDto.unitsType().unitsProductWeight().getCoefficient()), RoundingMode.CEILING)
                )
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        CalculationCaseDto calculationCaseDto = CalculationCaseDto
                .builder()
                .totalPrice(totalPrice)
                .products(productCompactDtos)
                .substances(substanceCompacts)
                .build();
        calculationResponseDto.getCases().add(calculationCaseDto);

    }

    private boolean isComplete(CalculationResponseDto calculationResponseDto) {
        int current = Optional.ofNullable(calculationResponseDto.getCases()).map(Collection::size).orElse(0);
        return maxLength<=current;
    }

    private boolean isCleanCase(LogicCase startCase) {
        return startCase.getProductMap().values().stream().mapToDouble(LogicProduct::getRate).noneMatch(rate->rate>0&&rate<minRate);
    }

    private Collection<LogicCase> getNextCases(LogicCase startCase) {

        TreeSet<LogicCase> cases = new TreeSet<>();
        for (LogicProduct lp : startCase.getProductMap().values()) {
            if (lp.getRate() >= minRate) {
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

    private boolean checkIfExists(CalculationResponseDto calculationResponseDto, LogicCase logicCase){
        return calculationResponseDto.getCases().stream().anyMatch(it->checkIsSame(it, logicCase));
    }
    private boolean checkIsSame(CalculationCaseDto calculationCaseDto,LogicCase logicCase){
        Set<Long> inCaseDto = calculationCaseDto.products().stream().filter(it->it.getRate().doubleValue()>0).map(ProductCompactDto::getId).collect(Collectors.toSet());
        Set<Long> inCase = logicCase.getProductMap().values().stream().filter(it->it.getRate()>0).map(LogicProduct::getId).collect(Collectors.toSet());
        return inCaseDto.equals(inCase);
    }


}
