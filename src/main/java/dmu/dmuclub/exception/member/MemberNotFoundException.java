package dmu.dmuclub.exception.member;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String msg) {
        super(msg);
    }
}
