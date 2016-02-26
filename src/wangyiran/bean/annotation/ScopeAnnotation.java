package wangyiran.bean.annotation;

import wangyiran.bean.scope.Scope;
import wangyiran.bean.scope.SingleScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangyiran on 26/2/2016.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ScopeAnnotation {
     Class<? extends Scope> scope() default SingleScope.class;
}
