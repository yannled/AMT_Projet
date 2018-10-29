package ch.heigvd.amt.amtproject.model;

public class Error {
    private String errorText;
    private boolean error;
    private String value;

    public Error(String errorText, boolean isError, String value){
        this.errorText = errorText;
        this.error = isError;
        this.value = value;
    }

    public Error(){
        this.errorText = "";
        this.error = false;
        this.value = "";
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
