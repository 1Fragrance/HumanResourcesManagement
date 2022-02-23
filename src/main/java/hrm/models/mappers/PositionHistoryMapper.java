package hrm.models.mappers;

import hrm.entities.PositionHistory;
import hrm.models.PositionHistoryViewModel;

public class PositionHistoryMapper {
    public static PositionHistory MapToEntity(PositionHistoryViewModel model) {
        PositionHistory entity = new PositionHistory();
        entity.setId(model.getId());
        entity.setStartDate(model.getStartDate());
        entity.setEndDate(model.getEndDate());
        entity.setEndDate(model.getEndDate());

        return entity;
    }

    public static PositionHistoryViewModel MapToModel(PositionHistory entity) {
        PositionHistoryViewModel model = new PositionHistoryViewModel();
        model.setId(entity.getId());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setEndDate(entity.getEndDate());

        // NOTE: We do not map relative entities info

        return model;
    }
}
