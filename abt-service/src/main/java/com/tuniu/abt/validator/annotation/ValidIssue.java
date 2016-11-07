package com.tuniu.abt.validator.annotation;

import com.tuniu.abt.validator.IssueRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Created by huangsizhou on 16/4/7.
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IssueRequestValidator.class})
@ReportAsSingleViolation
public @interface ValidIssue {

    String message() default "{validate.issueRequest}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String delimiter() default ",";

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ValidIssue[] value();
    }

}
