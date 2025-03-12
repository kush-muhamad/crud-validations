//package com.kush.valdiationDemo.validations;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailValidator, String> {
//    @Override
//    public void initialize(UniqueEmailValidator constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        // Implement your validation logic here
//        // Return true if validation passes, false otherwise
//        return value != null && value.startsWith("ABC"); // Example validation condition
//    }
////
//}
