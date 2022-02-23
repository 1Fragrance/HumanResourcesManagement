package hrm.models;

public class DepartmentViewModel extends ModelBase {
    private String name;

    private OfficeViewModel office;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public OfficeViewModel getOffice() {
        return office;
    }

    public void setOfficeId(OfficeViewModel officeId) {
        this.office = office;
    }
}
