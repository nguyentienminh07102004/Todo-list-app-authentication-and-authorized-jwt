package org.PTITB22DCCN539.Exception.MyException;

public class DataInvalidException extends RuntimeException {
    public DataInvalidException() {
        super("Data invalid exception !!");
    }

    public DataInvalidException(String message) {
        super(message);
    }
}
