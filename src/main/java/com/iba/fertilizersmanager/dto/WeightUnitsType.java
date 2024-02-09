package com.iba.fertilizersmanager.dto;

import lombok.Getter;

@Getter
public enum WeightUnitsType {
    KG (UnitSystem.METRIC, 1.0),
    METRIC_TON(UnitSystem.METRIC, 1000),
    LB (UnitSystem.ENGLISH, 1.0),
    SHORT_TON(UnitSystem.ENGLISH, 2000);

    private double coefficient;
    private UnitSystem unitSystem;

    WeightUnitsType(UnitSystem unitSystem, double coefficient) {
        this.coefficient =coefficient;
        this.unitSystem=unitSystem;
    }

}
