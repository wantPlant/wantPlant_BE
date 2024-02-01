package umc.wantPlant.pot.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.wantPlant.pot.validation.validator.GardenExistValidator;
import umc.wantPlant.pot.validation.validator.PotExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GardenExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistGarden {
    String message() default "정원 없음";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
