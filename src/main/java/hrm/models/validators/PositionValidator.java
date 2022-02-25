package hrm.models.validators;

import hrm.models.PositionViewModel;

public class PositionValidator implements IValidator<PositionViewModel> {
    @Override
    public ValidationResult Validate(PositionViewModel model) {

        if(model.getTitle() == null || model.getTitle().equals("")) {
            return new ValidationResult("Название обязателено к заполнению");
        }

        return new ValidationResult();
    }
}


