package com.kinoko.backend.aop;

import java.lang.annotation.*;

/**
 * @author su
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface ServiceTokenRequired {}
