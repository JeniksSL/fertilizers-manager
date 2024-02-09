package com.iba.fertilizersmanager.dto;

public record UnitsType (
        UnitSystem unitSystem,
        WeightUnitsType unitsNutrientWeight,
        AreaType unitsArea,
        PriceUnits unitsPrice,
        WeightUnitsType unitsProductWeight
        ){
}
