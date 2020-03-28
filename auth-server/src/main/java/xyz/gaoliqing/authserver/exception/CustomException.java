package xyz.gaoliqing.authserver.exception;


/**
 * @author Mr.GaoLiqing
 * @create 2020-01-19 18:50
 * @description
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 5596773189160085040L;

    private int code;
    private String message;

    public CustomException(CustomExceptionType customExceptionType, String message) {

        this.code = customExceptionType.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
