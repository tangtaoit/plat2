package com.tt.plat.security.shiro.impl.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ShiroSecurityConstraints {

	ShiroSecurityConstraint[] value();

}

