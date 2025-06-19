package com.enset.mabchour.minifacture.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class TauxTvaValidator implements ConstraintValidator<TauxTva, Double> {
    private static final Set<Double> VALID_TAUX = Set.of(0.0, 5.5, 10.0, 20.0);

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && VALID_TAUX.contains(value);
    }
} 