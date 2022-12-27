package com.example.common.aop

import com.example.config.logger
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class ServiceAspect {
    private val log = logger()

//    @Pointcut("execution(* com.example.springbootkotlinexample.domain..*.*(..))")
//    fun request() {
//    }
//
//    @Before("request()")
//    fun before(joinPoint: JoinPoint) {
//        log.info("before '{}' service", getMethodName(joinPoint))
//    }
//
//    @After("request()")
//    fun after(joinPoint: JoinPoint) {
//        log.info("after '{}' service", getMethodName(joinPoint))
//    }
//
//    @AfterReturning("request()")
//    fun afterReturning(joinPoint: JoinPoint) {
//        log.info("after returning '{}' service", getMethodName(joinPoint))
//    }
//
//    @AfterThrowing("request()")
//    fun afterThrowing(joinPoint: JoinPoint) {
//        log.info("after throwing '{}' service", getMethodName(joinPoint))
//    }
//
//    @Around("request()")
//    @Throws(Throwable::class)
//    fun around(pjp: ProceedingJoinPoint): Any {
//        log.info("around before '{}' service", getMethodName(pjp))
//        val result: Any = pjp.proceed()
//        log.info("around after '{}' service", getMethodName(pjp))
//        return result
//    }
//
//    private fun getMethodName(joinPoint: JoinPoint): String {
//        return joinPoint.signature.name
//    }
}
