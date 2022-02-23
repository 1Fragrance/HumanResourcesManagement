package hrm.models.mappers;

import hrm.entities.Position;
import hrm.models.PositionViewModel;

public class PositionMapper {
    public static Position MapToEntity(PositionViewModel model) {
        Position entity = new Position();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setMaxSalary(model.getMaxSalary());
        entity.setMinSalary(model.getMinSalary());

        return entity;
    }

    public static PositionViewModel MapToModel(Position entity) {
        PositionViewModel model = new PositionViewModel();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setMaxSalary(entity.getMaxSalary());
        model.setMinSalary(entity.getMinSalary());

        // NOTE: We do not map relative entities info

        return model;
    }
}
