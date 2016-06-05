package com.wuchao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wuchao.Entity.User;

/**
 * 该拦截器用于拦截特定访问路径
 * 这些访问路径需要登陆才能访问
 * 如果拦截到未登录用户访问
 * 则跳转到登陆界面
 * 
 * */
public class MyInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User userValid = (User) session.getAttribute("userValid");
		if(userValid==null){
			mv.setViewName("public/login");
			System.out.println("Spring拦截器postHandle:"+"用户未登录");
		}else{
			System.out.println("Spring拦截器postHandle:"+"已登陆用户"+userValid.getName());
		}
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User userValid = (User) session.getAttribute("userValid");
		if(userValid==null){
			System.out.println("Spring拦截器preHandle:"+"用户未登录");
			res.sendRedirect(req.getContextPath()+"//login.do");
			System.out.println("Spring拦截器preHandle:"+"重定向");
			return false;
		}
		System.out.println("Spring拦截器preHandle:"+"用户已登录");
		return true;
	}

}
