package hrm.models.validators;

import hrm.entities.Employee;
import hrm.entities.Position;
import hrm.models.EmployeeViewModel;
import hrm.repositories.EmployeeRepository;
import hrm.repositories.PositionRepository;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeValidator implements IValidator<EmployeeViewModel> {

    PositionRepository positionRepository;
    EmployeeRepository employeeRepository;
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    Pattern pattern;
    public EmployeeValidator() {
        positionRepository = new PositionRepository();
        pattern = Pattern.compile(emailRegex);
        employeeRepository = new EmployeeRepository();
    }

    @Override
    public ValidationResult Validate(EmployeeViewModel model) throws SQLException, ClassNotFoundException {

        if(model.getFirstName() == null || model.getFirstName().equals("")) {
            return new ValidationResult("Имя обязателено к заполнению");
        }

        if(model.getLastName() == null || model.getLastName().equals("")) {
            return new ValidationResult("Фамилия обязателена к заполнению");
        }

        if(model.getEmail() == null || model.getEmail().equals("")) {
            return new ValidationResult("Почта обязателена к заполнению");
        } else {
            Matcher matcher = pattern.matcher(model.getEmail());
            if(!matcher.matches()) {
                return new ValidationResult("Некорректный ввод почты");
            }
        }

        if(model.getUserName() == null || model.getUserName().equals("")) {
            return new ValidationResult("Логин обязателен к заполнению");
        } else {
            Employee existingEmployee = null;

            // NOTE: if create
            if(model.getId() == 0) {
                existingEmployee = employeeRepository.GetEmployeeByCredentials(model.getUserName());
            } else {
                existingEmployee = employeeRepository.GetEmployeeByCredentials(model.getUserName(), model.getId());
            }
            if(existingEmployee != null) {
                return new ValidationResult("Пользователь с таким логином уже существует в системе");
            }
        }

        if(model.getPassword() == null || model.getPassword().equals("")) {
            return new ValidationResult("Пароль обязателен к заполнению");
        }

        if(model.getSalary() != 0 && model.getPositionId() == 0) {
            return new ValidationResult("Зарплата не может быть назначена без выбора должности");
        } else if (model.getSalary() != 0 && model.getPositionId() != 0) {
            Position position = positionRepository.GetPositionById(model.getPositionId());
            if(position.getMinSalary() > model.getSalary() || position.getMaxSalary() < model.getSalary()) {
                return new ValidationResult("Зарплата не может превышать или быть ниже заданной в должности");
            }

        }

        return new ValidationResult();
    }
}
