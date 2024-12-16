package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    // Around 태그: Aop을 적용할 범위
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timems = finish - start;
            System.out.println("END: "+joinPoint.toString() + " " + timems + "ms");
        }

    }
}
