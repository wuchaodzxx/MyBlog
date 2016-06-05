package com.wuchao.annoation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String>{
	private String nameValue;  
	private int maxLen;
	private int minLen;
	@Override
	public void initialize(Name name) {
		// TODO Auto-generated method stub
		maxLen=name.maxL();
		minLen=name.minL();
		System.out.println("自定义注解初始化");
		System.out.println("maxLen："+maxLen);
		System.out.println("minLen："+minLen);
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		System.out.println("自定义注解校验:"+name);
		if(name.length()<=maxLen&&name.length()>=minLen){
			System.out.println("自定义注解校验通过："+name);
			return true;
		}
		return false;
	}

}
