package umc.wantPlant.goal.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.wantPlant.goal.validation.validator.GoalExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GoalExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistGoal {
    String message() default "없는 목표";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
