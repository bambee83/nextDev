package org.example.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 메서드 파라미터에만 사용 가능
@Retention(RetentionPolicy.RUNTIME)  // 런타임에 어노테이션을 사용할 수 있도록 설정
public @interface LogParameter {
}
