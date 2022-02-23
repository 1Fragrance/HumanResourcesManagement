package hrm.models;

import java.sql.Date;

public class EmployeeViewModel extends ModelBase {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private Date hireDate;
    private float salary;

    private String email;
    private String userName;
    private String password;
    private Boolean isAdmin;
    private int status;
    private String statusName;


    private int positionId;
    private PositionViewModel position;

    private int departmentId;
    private DepartmentViewModel department;

    public EmployeeViewModel() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getFullName() {
        String str = lastName + " " + firstName;
        if(patronymic != null && patronymic != ""){
            return str + " " + patronymic;
        }

        return str;
    }
}
