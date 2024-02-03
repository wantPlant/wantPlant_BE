package umc.wantPlant.pot.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.wantPlant.pot.validation.validator.CheckPageValidator;
import umc.wantPlant.pot.validation.validator.GardenExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "잘못된 페이지 범위";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
