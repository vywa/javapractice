package com.youpu.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranException extends Exception {

    protected static final Logger logger = LoggerFactory.getLogger(TranException.class);

    public TranException() {
    }

    public TranException(String message) {
        super(message);
        logger.error(message);
    }

    public TranException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message,cause);
    }

    public TranException(Throwable cause) {
        super(cause);
    }

}
