package com.wuchao.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/*
 * 拦截所有异常，并处理
 * 实现原理：Spring遇到异常会搜索所有实现HandlerExceptionResolver的bean
 * 并将异常提交给该bean处理
 */
@Component
public class HandlerException implements HandlerExceptionResolver{
	@Resource
	private ModelAndView mv;
	@Override
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object obj,
			Exception exception) {
		// TODO Auto-generated method stub
		if(exception!=null){
			System.out.println("拦截到异常："+exception.toString());
			StackTraceElement[] ErrorElements = exception.getStackTrace();
			String errors = "";
			for(int i=0;i<ErrorElements.length;i++){
				errors=errors+"\n"+ErrorElements[i];
			}
			mv.addObject("error", errors);
			mv.setViewName("public/error");
			return mv;
		}
		return null;
	}

	
	
}
