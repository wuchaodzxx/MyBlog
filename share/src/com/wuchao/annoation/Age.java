package com.wuchao.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=AgeValidator.class)  
public @interface Age {  
   

    int maxL() default 100;
    int minL() default 0;
    String message();  
     
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};  
}  