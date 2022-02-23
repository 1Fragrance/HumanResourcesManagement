package hrm.models.mappers;

import hrm.entities.Office;
import hrm.models.OfficeViewModel;

public class OfficeMapper {
    public static Office MapToEntity(OfficeViewModel model) {
        Office entity = new Office();
        entity.setId(model.getId());
        entity.setStreetAddress(model.getStreetAddress());
        entity.setPostalCode(model.getPostalCode());
        entity.setInternalName(model.getInternalName());
        entity.setCountry(model.getCountry());
        entity.setCity(model.getCity());

        // NOTE: We do not map relative entities info

        return entity;
    }

    public static OfficeViewModel MapToModel(Office entity) {
        OfficeViewModel model = new OfficeViewModel();
        model.setId(entity.getId());
        model.setStreetAddress(entity.getStreetAddress());
        model.setPostalCode(entity.getPostalCode());
        model.setInternalName(entity.getInternalName());
        model.setCountry(entity.getCountry());
        model.setCity(entity.getCity());

        // NOTE: We do not map relative entities info

        return model;
    }
}
