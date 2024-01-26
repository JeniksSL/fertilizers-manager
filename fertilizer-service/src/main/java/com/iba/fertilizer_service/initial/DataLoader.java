package com.iba.fertilizer_service.initial;



import com.iba.fertilizer_service.domain.Product;
import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.service.ProductService;
import com.iba.fertilizer_service.service.SubstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final SubstanceService substanceService;

    private final ProductService productService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Substance nSub = substanceService.getByName("N")
                .orElseGet(()->substanceService.create(Substance
                        .builder()
                        .name("N")
                        .color(16772864)
                        .description("Nitrogen")
                        .build()
                ));
        Substance p2o5Sub = substanceService.getByName("P2O5")
                .orElseGet(()->substanceService.create(Substance
                        .builder()
                        .name("P2O5")
                        .color(8945787)
                        .description("Phosphorus oxide")
                        .build()
                ));
        Substance k2oSub = substanceService.getByName("K2O")
                .orElseGet(()->substanceService.create(Substance
                        .builder()
                        .name("K2O")
                        .color(8731495)
                        .description("Potassium oxide")
                        .build()
                ));
        Substance so3Sub = substanceService.getByName("SO3")
                .orElseGet(()->substanceService.create(Substance
                        .builder()
                        .name("SO3")
                        .color(9313821)
                        .description("Sulphur oxide")
                        .build()
                ));
        if (productService.getByIdAndUserIdOrCommon(1L, 1L).isPresent()) return;
        productService.createForUser(Product
                .builder()
                        .name("MAP 12-52")
                        .fullName("Monoammonium Phosphate")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                nSub, new BigDecimal("0.12"),
                                p2o5Sub, new BigDecimal("0.52"),
                                so3Sub, new BigDecimal("0.075")
                        ))
                .build()
        , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("DAP 18-47")
                        .fullName("Diammonium Phosphate")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                nSub, new BigDecimal("0.18"),
                                p2o5Sub, new BigDecimal("0.47"),
                                so3Sub, new BigDecimal("0.037")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("CSP 45")
                        .fullName("Concentrated Superphosphate")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                p2o5Sub, new BigDecimal("0.45"),
                                so3Sub, new BigDecimal("0.037")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("Urea 46")
                        .fullName("Urea")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                nSub, new BigDecimal("0.46")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("AS 21")
                        .fullName("Ammonium sulfate")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                p2o5Sub, new BigDecimal("0.21"),
                                so3Sub, new BigDecimal("0.24")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("AN 34")
                        .fullName("Ammonium Nitrate")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                p2o5Sub, new BigDecimal("0.34")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("MOP 60")
                        .fullName("Potassium chloride")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                k2oSub, new BigDecimal("0.60")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("SOP 50")
                        .fullName("Potassium sulfate")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                k2oSub, new BigDecimal("0.5"),
                                so3Sub, new BigDecimal("0.425")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("NPK 7-20-30+5SO3")
                        .fullName("Complex 7-20-30")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                nSub, new BigDecimal("0.07"),
                                p2o5Sub, new BigDecimal("0.20"),
                                k2oSub, new BigDecimal("0.30"),
                                so3Sub, new BigDecimal("0.05")
                        ))
                        .build()
                , 0L);
        productService.createForUser(Product
                        .builder()
                        .name("NPK 16-16-16+18SO3")
                        .fullName("Complex 16-16-16")
                        .isCommon(true)
                        .substanceMap(Map.of(
                                nSub, new BigDecimal("0.16"),
                                p2o5Sub, new BigDecimal("0.16"),
                                k2oSub, new BigDecimal("0.16"),
                                so3Sub, new BigDecimal("0.18")
                        ))
                        .build()
                , 0L);
    }
 }
