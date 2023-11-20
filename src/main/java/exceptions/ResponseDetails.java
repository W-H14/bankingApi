package exceptions;

public class ResponseDetails {
    private Integer status;
    private String message;
    private Object data;


    public ResponseDetails() {
    }

    public ResponseDetails(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDetails(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}