package org.example.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 파라미터에 해당 애노테이션을 사용할 수 있다는 뜻
@Retention(RetentionPolicy.RUNTIME)  //리플렉션 등을 활용할 수 있도록 런타임까지 애노테이션 정보가 남아있다는 뜻
public @interface Param {
}
