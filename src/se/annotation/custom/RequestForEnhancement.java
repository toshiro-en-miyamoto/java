package se.annotation.custom;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Describes the "request-for-enhancement" (RFE) that led to the presence of
 * the annotated API element.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestForEnhancement {
   int id();           // Unique ID number associated with RFE
   String synopsis();  // Synopsis of RFE
   String engineer() default "[unassigned]";  // Name of engineer who implemented RFE
   String date()  default "[unimplemented]";  // Date RFE was implemented.
}
