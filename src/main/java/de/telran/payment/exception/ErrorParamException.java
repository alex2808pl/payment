package de.telran.payment.exception;

public class ErrorParamException extends RuntimeException{
    public ErrorParamException(String message) {
        super(message);
    }
}
