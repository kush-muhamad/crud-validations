//package com.kush.valdiationDemo.validations;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Target({ElementType.FIELD , ElementType.METHOD})// which type of element we want to validate
//@Retention(RetentionPolicy.RUNTIME)// we want to retain this annotation at runtime
//@Constraint(validatedBy = UniqueEmailValidator.class) // which class will validate this annotation
//public @interface UniqueEmailValidation {
//    String message() default "Email already exists"; // default message
//    Class<?>[] groups() default {}; // incase we want to group multiple validations
//    Class<? extends Payload>[] payload() default {}; // carrys metadata associated with the annotation
//
//}
//
////@Target({ElementType.FIELD, ElementType.METHOD})
////@Retention(RetentionPolicy.RUNTIME)
////@Constraint(validatedBy = CustomValidator.class)
////
////String message() default "Invalid value";
////
////Class<?>[] groups() default {};
////
////Class<? extends Payload>[] payload() default {}
