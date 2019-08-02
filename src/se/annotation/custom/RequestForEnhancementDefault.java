package se.annotation.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestForEnhancementDefault {
   int id();
   String synopsis();
   String engineer() default "[unassigned]";
   String date() default "[unimplemented]";
}
