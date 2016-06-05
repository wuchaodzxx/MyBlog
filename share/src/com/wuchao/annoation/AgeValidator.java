package com.wuchao.annoation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, String>{
	private String nameValue;  
	private int maxLen;
	private int minLen;
	@Override
	public void initialize(Age name) {
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
		int number;
		System.out.println("自定义注解校验");
		try{
			number = Integer.parseInt(name);
		}catch (Exception e){
			return false;
		}
		if(number<=maxLen&&number>=minLen){
			System.out.println("自定义注解校验通过："+number);
			return true;
		}
		return false;
	}

}
