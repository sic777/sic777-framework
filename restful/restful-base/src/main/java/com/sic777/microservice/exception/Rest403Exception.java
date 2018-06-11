package com.sic777.microservice.exception;

import com.sic777.microservice.exception.error.RESTFUL_ERROR;

/**
 * <p></p>
 *
 * @author Zhengzhenxie<br>
 * <br>2017-12-18
 * @version v1.0
 * @since 1.7
 */
public final class Rest403Exception extends RestException {

    private static final long serialVersionUID = 3487325839481274723L;


    public Rest403Exception(long code, String msg) {
        this(code, msg, true);
    }

    public Rest403Exception(long code, String msg, boolean log) {
        super(code, msg, log, null);
    }

    public Rest403Exception(RESTFUL_ERROR ERROR, Object... format) {
        this(ERROR, false, format);
    }

    public Rest403Exception(RESTFUL_ERROR ERROR, boolean log, Object... format) {
        super(ERROR, log, format);
    }

    @Override
    public int getHttpStatus() {
        return 403;
    }
}