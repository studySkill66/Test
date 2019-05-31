//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hksy.framework.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerInterceptor {
    Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);

    public ControllerInterceptor() {
    }

    @Pointcut("execution(* com.yobtc.api..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String allParams = "";
        this.log.debug("");
        this.log.debug("========>请求的方法：{}<========", methodName);
        Object result = null;
        Object[] args = pjp.getArgs();
        Object[] var10 = args;
        int var11 = args.length;

        for(int var12 = 0; var12 < var11; ++var12) {
            Object arg = var10[var12];
            if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest)arg;
                Map<String, String[]> paramMap = request.getParameterMap();
                String key;
                if (paramMap != null && paramMap.size() > 0) {
                    for(Iterator var16 = paramMap.keySet().iterator(); var16.hasNext(); allParams = allParams + key + "=" + ((String[])paramMap.get(key))[0] + ";") {
                        key = (String)var16.next();
                    }
                }
            } else {
                allParams = allParams + "OtherParam=" + arg + ";";
            }
        }

        try {
            if (result == null) {
                Parameter[] parameters = method.getParameters();
                String paramName = "";
                String paramType = "";
                Parameter[] var24 = parameters;
                int var25 = parameters.length;

                for(int var26 = 0; var26 < var25; ++var26) {
                    Parameter param = var24[var26];
                    paramType = paramType + param.getType().getName() + ";";
                    paramName = paramName + param.getName() + ";";
                }

                this.log.debug("========>方法定义参数:[{}]", paramName);
                this.log.debug("========>定义参数类型:[{}]", paramType);
                this.log.debug("========>方法参数:[{}]<========", allParams);
                result = pjp.proceed();
            }
        } catch (ClassNotFoundException var18) {
            this.log.error("exception: {}", var18);
        } catch (Throwable var19) {
            this.log.error("exception:{}", var19);
        }

        long costMs = System.currentTimeMillis() - beginTime;
        this.log.debug("{}请求结束，耗时：{}ms", methodName, costMs);
        this.log.debug("");
        return result;
    }
}
