package com.iba.fertilizer_service.dto.mapper.core;

import com.iba.fertilizer_service.domain.Substance;
import com.iba.fertilizer_service.dto.SubstanceDto;


import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ColorConverter {

    public static String getColorString(Map<Substance, BigDecimal> substanceMap){
        int red=0;
        int green=0;
        int blue=0;
        double contentSum=0;
        int size = substanceMap.size();
        for (Map.Entry<Substance, BigDecimal> entry : substanceMap.entrySet()) {
            double content = entry.getValue().doubleValue();
            int rgb = entry.getKey().getColor();
            red += (int) (((rgb >> 16) & 0xFF) * content);
            green += (int) (((rgb >> 8) & 0xFF) * content);
            blue += (int) (((rgb) & 0xFF) * content);
            contentSum += content;
        }
        if (contentSum>1) contentSum=1;
        red/=size*contentSum;
        green/=size*contentSum;
        blue/=size*contentSum;
        int max=Math.max(red,green);
        max=Math.max(max, blue);
        double whiteShift=0xFF*1.0/max;
        whiteShift=(whiteShift-1)*(contentSum)+1;
        red*=whiteShift;
        green*=whiteShift;
        blue*=whiteShift;
        int rgb = ((0x00000000)  | ((red << 16) & 0x00FF0000) | ((green << 8) & 0x0000FF00) | (blue) & 0x0000FF) ;
        return "#".concat(Integer.toHexString(rgb));

    }
    public static String getColorString(Substance substance){
        return "#".concat(Integer.toHexString(substance.getColor()));
    }
    public static int getColorInt(SubstanceDto substanceDto){
        return Optional.of(substanceDto.getColor())
                .map(color->color.replace("#", "").trim())
                .map(Scanner::new)
                .filter(scanner -> scanner.hasNextInt(16))
                .map(Scanner::nextInt)
                .orElse(0);
    }
}
