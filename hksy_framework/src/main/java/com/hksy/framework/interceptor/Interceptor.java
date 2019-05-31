package com.hksy.framework.interceptor;

import com.hksy.framework.annotaion.Token;
import com.hksy.framework.common.OperateToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/*     */
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;





@Aspect
public abstract class Interceptor
{
  @Autowired
  OperateToken operateToken;

  @Pointcut
  public abstract void methodParamProintcut();

  @Around("methodParamProintcut()")
  public Object interceptor(ProceedingJoinPoint pjp)
    throws Throwable
  {
/*  34 */     MethodSignature signature = (MethodSignature)pjp.getSignature();
/*  35 */     Method method = signature.getMethod();
/*  36 */     Annotation[][] as = method.getParameterAnnotations();
/*  37 */     Object[] args = pjp.getArgs();

/*  39 */     if (as == null) {
/*  40 */       return pjp.proceed();
    }
/*  42 */     int paramIndex = -1;
/*  43 */     Token t = null;
/*  44 */     RequestBody r = null;


/*  47 */     for (int i = 0; i < as.length; i++) {
/*  48 */       Annotation[] ans = as[i];
/*  49 */       for (int j = 0; j < ans.length; j++)
      {
/*  51 */         if ((ans[j] instanceof Token)) {
/*  52 */           t = (Token)ans[j];
/*  53 */           paramIndex = i;
/*  54 */           args[paramIndex] = (args[paramIndex] == null ? method.getParameters()[paramIndex].getType().newInstance() : args[paramIndex]);
/*  55 */         } else if ((ans[j] instanceof RequestBody)) {
/*  56 */           r = (RequestBody)ans[j];
/*  57 */           paramIndex = i;
/*  58 */           args[paramIndex] = (args[paramIndex] == null ? method.getParameters()[paramIndex].getType().newInstance() : args[paramIndex]);
        }
      }
    }


/*  64 */     r = t != null ? null : r;


/*  67 */     if (r != null) {
/*  68 */       Class clazz = args[paramIndex].getClass();
/*  69 */       Field[] fields = clazz.getDeclaredFields();

/*  71 */       if ((fields != null) && (fields.length > 0))
      {
/*  73 */         for (int i = 0; i < fields.length; i++)
        {
/*  75 */           Annotation[] fAnn = fields[i].getDeclaredAnnotations();
/*  76 */           if ((fAnn != null) && (fAnn.length > 0)) {
/*  77 */             for (int j = 0; j < fAnn.length; j++)
            {
/*  79 */               if ((fAnn[j] instanceof Token)) {
/*  80 */                 Object instance = clazz.newInstance();
/*  81 */                 String token = null;
/*  82 */                 t = (Token)fAnn[j];
/*  83 */                 switch (t.method()) {
                case CREATE:
/*  85 */                   if (t.expire() <= 0) {
/*  86 */                     token = this.operateToken.createToken();
                  } else {
/*  88 */                     token = this.operateToken.createToken(t.expire());
                  }
/*  90 */                   fields[i].setAccessible(true);
/*  91 */                   fields[i].set(instance, token);
/*  92 */                   args[paramIndex] = instance;
/*  93 */                   return pjp.proceed(args);
                case CHECK:
/*  95 */                   fields[i].setAccessible(true);
/*  96 */                   instance = args[paramIndex];
/*  97 */                   token = fields[i].get(instance) == null ? "" : fields[i].get(instance).toString();

/*  99 */                   if (this.operateToken.checkToken(token)) {
/* 100 */                     return pjp.proceed();
                  }
/* 102 */                   return null;
                }

/* 105 */                 return pjp.proceed();
              }
            }
          }
        }
      }
    }


/* 114 */     if (t != null) {
/* 115 */       String token = null;
/* 116 */       switch (t.method())
      {
      case CREATE:
/* 119 */         if (t.expire() <= 0) {
/* 120 */           token = this.operateToken.createToken();
        } else {
/* 122 */           token = this.operateToken.createToken(t.expire());
        }
/* 124 */         args[paramIndex] = token;
/* 125 */         return pjp.proceed(args);


      case CHECK:
/* 129 */         token = null;
/* 130 */         if (paramIndex != -1)
        {
/* 132 */           token = args[paramIndex] == null ? "" : args[paramIndex].toString();
        }


/* 136 */         if (this.operateToken.checkToken(token)) {
/* 137 */           return pjp.proceed();
        }
/* 139 */         return null;
      }

/* 142 */       return pjp.proceed();
    }



/* 147 */     return pjp.proceed();
  }
}


/* Location:              /Users/Edanel/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/98cec777e95bfadfeabf56ef33ad26af/Message/MessageTemp/0c9d95d056c532e28592a9c60e764a85/File/yobtc-task-service_1.1.0.jar!/BOOT-INF/lib/yobtc-framework-1.0.6.RELEASE.jar!/com/yobtc/framework/interceptor/Interceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */