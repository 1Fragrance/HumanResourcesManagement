package hrm.models.mappers;

import hrm.entities.Department;
import hrm.models.DepartmentViewModel;

public final class DepartmentMapper {
    public static Department MapToEntity(DepartmentViewModel model) {
        Department entity = new Department();
        entity.setId(model.getId());
        entity.setName(model.getName());

        // NOTE: We do not map relative entities info

        return entity;
    }

    public static DepartmentViewModel MapToModel(Department entity) {
        DepartmentViewModel model = new DepartmentViewModel();
        model.setId(entity.getId());
        model.setName(entity.getName());

        // NOTE: We do not map relative entities info

        return model;
    }

}
