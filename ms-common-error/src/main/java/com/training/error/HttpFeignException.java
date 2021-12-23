package com.training.error;

import com.training.error.ErrorObj;

public class HttpFeignException extends RuntimeException {

    private static final long serialVersionUID = 1114630941797819930L;

    private ErrorObj          errorObj;

    public HttpFeignException() {
    }

    public HttpFeignException(final ErrorObj errorObjParam) {
        super();
        this.setErrorObj(errorObjParam);
    }

    public ErrorObj getErrorObj() {
        return this.errorObj;
    }

    public void setErrorObj(final ErrorObj errorObjParam) {
        this.errorObj = errorObjParam;
    }


}
