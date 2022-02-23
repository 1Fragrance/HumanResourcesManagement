package hrm.models;

public class DepartmentViewModel extends ModelBase {
    private String name;

    private int officeId;
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

    public void setOffice(OfficeViewModel office) {
        this.office = office;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
