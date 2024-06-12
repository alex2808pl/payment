package de.telran.payment.exception;

public class NotFoundInDbException extends RuntimeException{
        public NotFoundInDbException(String message) {
            super(message);
        }
}
