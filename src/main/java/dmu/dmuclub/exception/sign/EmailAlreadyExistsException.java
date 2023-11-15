package dmu.dmuclub.exception.sign;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
