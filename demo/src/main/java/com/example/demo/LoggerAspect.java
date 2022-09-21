package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggerAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ObjectMapper mapper;

	@Pointcut("execution(* com.example.demo.Jezik.JezikController.*(..))"
			+ "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void logMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		RequestMapping mapping = signature.getMethod().getAnnotation(RequestMapping.class);

		Map<String, Object> parameters = getParameters(joinPoint);

		try {
			logger.info("==> path(s): {}, method(s): {}, arguments: {} ",
					mapping.path(), mapping.method(), mapper.writeValueAsString(parameters));
		} catch (JsonProcessingException e) {
			logger.error("Error while converting", e);
		}
	}

	private Map<String, Object> getParameters(JoinPoint joinPoint) {
		CodeSignature signature = (CodeSignature) joinPoint.getSignature();

		HashMap<String, Object> map = new HashMap<>();

		String[] parameterNames = signature.getParameterNames();

		for (int i = 0; i < parameterNames.length; i++) {
			map.put(parameterNames[i], joinPoint.getArgs()[i]);
		}

		return map;
	}
}
