package br.com.puc.tcc.web.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.puc.tcc.web.domain.enums.RoleEnum;

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Privado {
        public RoleEnum role();
}
