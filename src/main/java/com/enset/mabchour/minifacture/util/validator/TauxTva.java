package com.enset.mabchour.minifacture.util.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TauxTvaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TauxTva {
    String message() default "Taux de TVA invalide (doit être 0, 5.5, 10 ou 20)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 