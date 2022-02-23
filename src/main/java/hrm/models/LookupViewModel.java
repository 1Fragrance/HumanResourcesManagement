package hrm.models;

public class LookupViewModel {
    private String name;
    private String value;

    public LookupViewModel() {

    }

    public LookupViewModel(String _name, String _value) {
        name = _name;
        value = _value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
