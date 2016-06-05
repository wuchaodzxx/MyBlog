package com.wuchao.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=EmailValidator.class)  
public @interface Email {  
	String required() default "false"; 
    String message();  
     
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};  
}  