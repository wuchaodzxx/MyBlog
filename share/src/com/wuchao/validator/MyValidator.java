package com.wuchao.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.wuchao.Entity.*;
public class MyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object user, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	

}
