package org.PTITB22DCCN539.Exception.MyException;

public class MyUnauthorizedException extends RuntimeException {
    public MyUnauthorizedException(String message) {
        super(message);
    }

    public MyUnauthorizedException() {
        super("You are unauthorized");
    }
}
