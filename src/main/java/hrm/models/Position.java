package hrm.models;

public class Position extends EntityBase {
    private String title;
    private float minSalary;
    private float maxSalary;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(float minSalary) {
        this.minSalary = minSalary;
    }

    public float getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(float maxSalary) {
        this.maxSalary = maxSalary;
    }
}
