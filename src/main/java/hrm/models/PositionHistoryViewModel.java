package hrm.models;

import java.sql.Date;

public class PositionHistoryViewModel extends ModelBase {
    private Date startDate;
    private Date endDate;

    private EmployeeViewModel employee;
    private PositionViewModel position;
    private DepartmentViewModel department;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EmployeeViewModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewModel employee) {
        this.employee = employee;
    }

    public PositionViewModel getPosition() {
        return position;
    }

    public void setPosition(PositionViewModel position) {
        this.position = position;
    }

    public DepartmentViewModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentViewModel department) {
        this.department = department;
    }
}
