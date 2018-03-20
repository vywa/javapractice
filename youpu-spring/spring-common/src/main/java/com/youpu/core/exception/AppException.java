package com.youpu.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends Exception{

    protected final static Logger logger = LoggerFactory.getLogger(AppException.class);

    public AppException() {
    }

    public AppException(String message) {
        super(message);
        logger.error(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message,cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
