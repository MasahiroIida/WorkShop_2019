package javastudywebapplication.app.aop;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceInterceptor {

	private static Logger logger = Logger.getLogger(ServiceInterceptor.class);

	@Before("execution(* javastudywebapplication.app.core.controller.*.*.*(..))")
	public void invokeControllerBefore(JoinPoint joinPoint) {
		outputLog(joinPoint, true);
	}
	
	@After("execution(* javastudywebapplication.app.core.controller.*.*.*(..))")
	public void invokeControllerAfter(JoinPoint joinPoint) {
		outputLog(joinPoint, false);
	}

	private void outputLog(JoinPoint joinPoint, boolean isStart) {
		String logMessage = "[" + getClassName(joinPoint) + "]:" + (isStart ? "START:" : "END:")
				+ getArguments(joinPoint);
		logger.info(logMessage);
	}

	private String getClassName(JoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().toString();
	}

	private String getArguments(JoinPoint joinPoint) {
		if (joinPoint.getArgs() == null) {
			return "argument is null";
		}
		Object[] arguments = joinPoint.getArgs();
		ArrayList<String> argumentStrings = new ArrayList<String>();

		for (Object argument : arguments) {
			if (argument != null) {
				argumentStrings.add(argument.toString());
			}
		}
		return String.join(",", argumentStrings);
	}
}
