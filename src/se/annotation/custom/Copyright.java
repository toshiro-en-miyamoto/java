package se.annotation.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Associates a copyright notice with the annotated API element.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Copyright {
   String value();
}
