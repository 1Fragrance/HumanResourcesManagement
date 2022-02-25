package hrm.models.validators;

import hrm.models.ModelBase;

import java.sql.SQLException;

interface IValidator<T extends ModelBase> {
    public ValidationResult Validate(T model) throws SQLException, ClassNotFoundException;
}
