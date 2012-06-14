package com.hixi_hyi.idumo.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
public @interface IDUMOItemAnnotation {
	String author();
	String name();
	String description() default "";
	IDUMOType type();
	public enum IDUMOType {
		Provider,
		Handler,
		Receiptor,
		Adaptor,
	}
	
	
}
