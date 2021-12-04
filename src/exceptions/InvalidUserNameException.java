package exceptions;
public class InvalidUserNameException extends Exception {
    public InvalidUserNameException(String errorString) {
        super(errorString);
    }
}
