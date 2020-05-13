package com.tledu.aaa.filter;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.tledu.aaa.util.AjaxObj;

@Component
@Aspect
public class LogInterceptor {
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}
@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Pointcut("execution(public * com.tledu.aaa.controller..*.*(..))")
	public void myMethod(){
		
	}

	@Around("myMethod()")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		String isRole = (String) request.getAttribute("isRole");
		if (isRole != null) {
			return new AjaxObj(0, isRole);
		}else{
	     
			// 放行,调用被代理的方法
			return pjp.proceed();
		}

	}
	

}
