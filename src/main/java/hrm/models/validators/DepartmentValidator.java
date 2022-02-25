package hrm.models.validators;

import hrm.models.DepartmentViewModel;

public class DepartmentValidator implements IValidator<DepartmentViewModel> {
    @Override
    public ValidationResult Validate(DepartmentViewModel model) {

        if(model.getName() == null || model.getName().equals("")) {
            return new ValidationResult("Название обязательно к заполнению");
        }

        if(model.getOfficeId() == 0) {
            return new ValidationResult("Офис обязателен к заполнению");
        }


        return new ValidationResult();
    }
}
