package com.zjf.qrcodedishcheck.common.validate;

import com.zjf.qrcodedishcheck.common.constant.AdviceOrder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
@Order(AdviceOrder.PARAM_VALIDATE)
public class ParamsValidateAdvice {

    private ParamsValidator validator = new ParamsValidator();

    /**
     * 拦截所有参数上加了@Validated的注解的方法
     * 排除掉controller，因为controller有自己的参数校验实现，不需要aop
     */
    @Pointcut("execution(* com.choice..*(.., @org.springframework.validation.annotation.Validated (*), ..)) && "
            + "!execution(* com.choice..controller..*(..)) ")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();

        // 验证参数上的注解
        for (int i = 0; i < parameters.length; i++) {
            Parameter p = parameters[i];
            Validated validated = p.getAnnotation(Validated.class);
            if (validated == null) {
                continue;
            }
            // 如果设置了group
            if (validated.value() != null && validated.value().length > 0) {
                validator.validate(params[i], null, validated.value());
            } else {
                validator.validate(params[i], null);
            }
        }
    }
}
