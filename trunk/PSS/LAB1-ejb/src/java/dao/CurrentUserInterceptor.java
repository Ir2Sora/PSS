package dao;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class CurrentUserInterceptor implements Serializable{
    @Inject ApplicationInfo applicationInfo;
    
   @AroundInvoke
   public Object businessIntercept(InvocationContext ctx) 
       throws Exception {
       if (ctx.getMethod().getName().equals("logIn")){
           applicationInfo.incrementNumberOfUsers();
       } else if (ctx.getMethod().getName().equals("logOut")){
           applicationInfo.decrementNumberOfUsers();
       }
       return ctx.proceed();
   }
}
