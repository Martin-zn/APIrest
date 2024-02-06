package com.martin.springboot.app.springbootcrud;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.martin.springboot.app.springbootcrud.entities.Product;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "El nombre es necesario para continuar");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", null, "La descripcion es necesaria para continuar");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", null, "El precio es necesario para continuar");
    }

}
