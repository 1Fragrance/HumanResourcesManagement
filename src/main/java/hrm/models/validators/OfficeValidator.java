package hrm.models.validators;

import hrm.models.OfficeViewModel;

public class OfficeValidator implements IValidator<OfficeViewModel> {
    @Override
    public ValidationResult Validate(OfficeViewModel model) {
        if(model.getStreetAddress() == null || model.getStreetAddress().equals("")) {
            return new ValidationResult("Адрес обязателен к заполнению");
        }

        if(model.getInternalName() == null || model.getInternalName().equals("")) {
            return new ValidationResult("Название обязателено к заполнению");
        }

        if(model.getCountry() == null || model.getCountry().equals("")) {
            return new ValidationResult("Страна обязателена к заполнению");
        }

        if(model.getCity() == null || model.getCity().equals("")) {
            return new ValidationResult("Город обязателен к заполнению");
        }

        return new ValidationResult();
    }
}
