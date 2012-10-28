package jp.idumo.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import jp.idumo.core.data.DataElement;


@Target({ ElementType.TYPE })
public @interface IDUMOProvider {
	Class<? extends DataElement> send();
}
