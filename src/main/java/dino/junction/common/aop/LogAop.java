package dino.junction.common.aop;

import dino.junction.common.aop.model.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAop {

    public final LogTrace logTrace;

//    @Pointcut("execution(* dino.junction.common..*(..)) && !execution(* dino.junction.common.logger..*(..))")
//    public void PointcutCommon(){}

    @Pointcut("execution(* dino.junction.domain..*(..))")
    public void PointcutDomain(){}

//    @Pointcut("execution(* dino.junction.config.login.jwt..*(..))")
//    public void PointcutJwtUtil(){}

    @Around("PointcutDomain()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
