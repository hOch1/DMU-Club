package dmu.dmuclub.exception.sign;

public class LoginFailureException extends RuntimeException{
    public LoginFailureException(String msg) {
        super(msg);
    }
}
