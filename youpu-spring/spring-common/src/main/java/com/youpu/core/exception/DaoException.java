package com.youpu.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

public class DaoException extends DataAccessException{

    private static final Logger logger = LoggerFactory.getLogger(DaoException.class);

    public DaoException(String msg) {
        super(msg);
        logger.error(msg);
    }

    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
        logger.error(msg,cause);
    }
}
