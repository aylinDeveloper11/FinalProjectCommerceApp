package az.developia.CommerceApp.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	@Before("execution(* az.developia.CommerceApp.service.UserService.register(..))")
	public void beforeSafe() {
		System.out.println("метод register был вызван");
	}

	@Before("execution(* az.developia.CommerceApp.service.UserService.*(..))")
	public void beforeAllInUserService() {
		System.out.println("все методы вызваны");
	}

	@After("execution(* az.developia.CommerceApp.service.UserService.register(..))")
	public void afterSave() {
		System.out.println("метод register завершился");
	}
}
