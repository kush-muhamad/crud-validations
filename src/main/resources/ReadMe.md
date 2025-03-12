***Java Validations SpringBoot***
This project is a simple example of how to use the validation annotations in a Spring Boot project.

Validations help us check if our program is following the set rules  and constraints  that have been
defined for the data that is being used in the program.

**In Spring Validations have annotations**
These annotations set the rules and constraints that the data must follow.

**Some of the annotations are:**
@NotNull: Ensures a field is not null.

@NotBlank: Enforces non-nullity and requires at least one non-whitespace character.

@NotEmpty: Guarantees that collections or arrays are not empty.

@Min(value): Checks if a numeric field is greater than or equal to the specified minimum value.

@Max(value): Checks if a numeric field is less than or equal to the specified maximum value.

@Size(min, max): Validates if a string or collection size is within a specific range.

@Pattern(regex): Verifies if a field matches the provided regular expression.

@Email: Ensures a field contains a valid email address format.

@Digits(integer, fraction): Validates that a numeric field has a specified number of integer and fraction digits.
@Past and @Future : Checks that a date or time field is in the past and future respectively.

@AssertTrue and @AssertFalse: Ensures that a boolean field is true. and false respectively.

@CreditCardNumber: Validates that a field contains a valid credit card number.

@Valid: Triggers validation of nested objects or properties.

@Validated: Specifies validation groups to be applied at the class or method level.


**@Valid:**
is used in the controller to validate the request body. 
It is used to validate the object that is passed in the request body.

**@Validated:**
is used to validate the request parameters.

**Making a custom validation annotation:**
To make a custom validation annotation, we need to create a class that implements the ConstraintValidator interface.
This interface has two methods: initialize and isValid.


