package com.iba.calcservice.facade;




import com.iba.calcservice.model.*;

import java.util.*;


public abstract class NativeFinder {


    private static native short[] nativeSolve(short[] weights, float[] contentData, float[] priceData, short noAlign);

    public static void nativeSolve(LogicCase logicCase, TreeMap<Long, Short> substanceWeights, boolean noAlign){
        TreeMap<Long, LogicProduct> productTreeMap = logicCase.getProductMap();
        short[] weights=getDemandWeightsArray(substanceWeights);
        float[] contentData=getContentData(productTreeMap, substanceWeights);
        float[] priceData=getPriceData(productTreeMap);
        short align=0;
        if (noAlign) align=1;
        short[] rates=NativeSolve.likeNativeSolve(weights,contentData,priceData, noAlign);//nativeSolve(weights,contentData,priceData, align);
        Iterator<Long> iterator= productTreeMap.keySet().iterator();
        for (int i = 0; i < rates.length; i++) {
            productTreeMap.get(iterator.next()).setRate(rates[i]);
        }
    }

    //weights of all substances
    private static short[] getDemandWeightsArray(TreeMap<Long, Short> substanceWeights){
        short[] weights = new short[substanceWeights.size()];
        Iterator<Short> iterator = substanceWeights.values().iterator();
        for (int i = 0; i < weights.length; i++) {
            weights[i]=iterator.next();
        }
        return weights;
    }

    //all substances content for all products
    private static float[] getContentData(TreeMap<Long, LogicProduct> productTreeMap, TreeMap<Long, Short> substanceWeights){
        float[] contentData = new float[substanceWeights.size()* productTreeMap.size()];
        List<Float> content = productTreeMap.values().stream().flatMap(lp->substanceWeights.keySet().stream().map(id->Optional.ofNullable(lp.getSubstanceMap().get(id)).orElse(0F)))
                .toList();
        /*List<Float> content = substanceWeights
                .keySet()
                .stream()
                .flatMap(id->productTreeMap.values().stream().map(logicProduct -> Optional.ofNullable(logicProduct.getSubstanceMap().get(id)).orElse(0F)))
                .toList();*/
        for (int i = 0; i < contentData.length; i++) {
            contentData[i] = content.get(i);
        }
        return contentData;
    }

    //price array
    private static float[] getPriceData(TreeMap<Long, LogicProduct> productTreeMap){
        float[] priceData = new float[productTreeMap.size()];

        Iterator<Long> iterator= productTreeMap.keySet().iterator();
        for (int i = 0; i < priceData.length; i++) {
            priceData[i]=productTreeMap.get(iterator.next()).getPrice();
        }
        return priceData;
    }

}
