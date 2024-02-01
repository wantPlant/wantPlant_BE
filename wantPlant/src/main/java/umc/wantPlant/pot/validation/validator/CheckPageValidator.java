package umc.wantPlant.pot.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.validation.annotation.CheckPage;
import umc.wantPlant.pot.validation.annotation.ExistPot;

@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Long> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(value<=0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }

        return true;
    }
}
