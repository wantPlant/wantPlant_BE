package umc.wantPlant.goal.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.goal.application.GoalQueryService;
import umc.wantPlant.goal.validation.annotation.ExistGoal;
import umc.wantPlant.pot.application.PotQueryService;

@Component
@RequiredArgsConstructor
public class GoalExistValidator implements ConstraintValidator<ExistGoal, Long> {
    private final GoalQueryService goalQueryService;

    @Override
    public void initialize(ExistGoal constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(!goalQueryService.existGoalById(value)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.GOAL_NOT_FOUND.toString()).addConstraintViolation();
        }

        return true;
    }
}
