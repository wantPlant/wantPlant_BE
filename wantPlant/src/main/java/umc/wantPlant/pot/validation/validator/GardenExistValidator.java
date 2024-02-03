package umc.wantPlant.pot.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.validation.annotation.ExistGarden;
import umc.wantPlant.pot.validation.annotation.ExistPot;

@Component
@RequiredArgsConstructor
public class GardenExistValidator implements ConstraintValidator<ExistGarden, Long> {
    private final GardenQueryService gardenQueryService;

    @Override
    public void initialize(ExistGarden constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(!gardenQueryService.existGardenById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.GARDEN_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }

        return true;
    }
}
