package se.annotation.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that the annotated API element is subject to change.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Preliminary {
}
