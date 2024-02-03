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
public class PotExistValidator implements ConstraintValidator<ExistPot, Long> {
    private final PotQueryService potQueryService;

    @Override
    public void initialize(ExistPot constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if(!potQueryService.existPotById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.POT_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
