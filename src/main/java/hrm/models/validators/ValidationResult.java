package hrm.models.validators;

public class ValidationResult {
    private boolean isSuccess = true;
    private String error;

    public ValidationResult() {

    }

    public ValidationResult(String errorText) {
        error = errorText;
        isSuccess = false;
    }

    public ValidationResult(boolean success, String errorText) {
        error = errorText;
        isSuccess = success;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
