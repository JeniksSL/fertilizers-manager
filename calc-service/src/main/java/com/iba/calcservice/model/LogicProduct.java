package com.iba.calcservice.model;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogicProduct  {

    private long id;
    private float price;
    private float rate;
    private TreeMap<Long,Float> substanceMap;


}
