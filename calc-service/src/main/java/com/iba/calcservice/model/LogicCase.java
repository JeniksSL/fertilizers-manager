package com.iba.calcservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class LogicCase implements Comparable<LogicCase> {

    TreeMap<Long, LogicProduct> productMap;

    @Override
    public int compareTo(LogicCase o) {
        int compare =Integer.compare(productMap.size(), o.productMap.size());
        Iterator<Long> iterator1= productMap.keySet().iterator();
        Iterator<Long> iterator2=o.productMap.keySet().iterator();
        while (compare==0&&iterator1.hasNext()) {
            compare=iterator1.next().compareTo(iterator2.next());
        }
        return compare;
    }


}
