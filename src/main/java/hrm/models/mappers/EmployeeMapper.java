package hrm.models.mappers;

import hrm.entities.Employee;
import hrm.infrastructure.Constants;
import hrm.infrastructure.EmployeeStatuses;
import hrm.models.EmployeeViewModel;

public final class EmployeeMapper {
    public static Employee MapToEntity(EmployeeViewModel model) {
        Employee entity = new Employee();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setPatronymic(model.getPatronymic());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setHireDate(model.getHireDate());
        entity.setSalary(model.getSalary());
        entity.setEmail(model.getEmail());
        entity.setUserName(model.getUserName());
        entity.setPassword(model.getPassword());
        entity.setAdmin(model.getAdmin());
        entity.setStatus(model.getStatus());
        entity.setDepartmentId(model.getDepartmentId());
        entity.setPositionId(model.getPositionId());

        // NOTE: We do not map relative entities info

        return entity;
    }

    public static EmployeeViewModel MapToModel(Employee entity) {
        EmployeeViewModel model = new EmployeeViewModel();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setPatronymic(entity.getPatronymic());
        model.setPhoneNumber(entity.getPhoneNumber());
        model.setHireDate(entity.getHireDate());
        model.setSalary(entity.getSalary());
        model.setEmail(entity.getEmail());
        model.setUserName(entity.getUserName());
        model.setPassword(entity.getPassword());
        model.setAdmin(entity.getAdmin());
        model.setStatus(entity.getStatus());
        model.setPositionId(entity.getPositionId());
        model.setDepartmentId(entity.getDepartmentId());

        if(entity.getStatus() == EmployeeStatuses.Active) {
            model.setStatusName(Constants.ResourceStrings.ActiveStatus);
        }
        else {
            model.setStatusName(Constants.ResourceStrings.BlockedStatus);
        }

        // NOTE: We do not map relative entities info

        return model;
    }
}
