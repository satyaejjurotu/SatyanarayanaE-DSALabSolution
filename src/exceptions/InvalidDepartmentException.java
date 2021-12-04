package exceptions;

public class InvalidDepartmentException extends Exception {
    public InvalidDepartmentException(String error){
        super(error);
    }
}
