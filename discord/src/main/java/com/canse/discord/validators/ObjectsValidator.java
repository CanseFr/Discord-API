package com.canse.discord.validators;

import com.canse.discord.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); // Factory Instance mere
    private final Validator validator = factory.getValidator();                         // Sous objet specialisé de validation
    public void validate(T objectToValidate){
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);// Creation d'un set unique de violation
        if (!violations.isEmpty()){                                                     // Si violation
            Set<String> errorMessages  = violations.stream()   // Ajout au set du message de la violation annoté sur le DTO **
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
        }
    }
}
