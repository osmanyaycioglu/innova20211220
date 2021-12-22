package com.training.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorObj {

    private List<ErrorObj> subErrors;
    private String         domain;
    private String         subdomain;
    private String         boundedcontext;
    private String         microservice;
    private String         message;
    private Integer        errorCode;


    public ErrorObj addError(final ErrorObj errorObjParam) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(errorObjParam);
        return this;

    }

    public List<ErrorObj> getSubErrors() {
        return this.subErrors;
    }

    public ErrorObj setSubErrors(final List<ErrorObj> subErrorsParam) {
        this.subErrors = subErrorsParam;
        return this;
    }

    public static ErrorObj create() {
        return new ErrorObj();
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorObj setMessage(final String messageParam) {
        this.message = messageParam;
        return this;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public ErrorObj setErrorCode(final Integer errorCodeParam) {
        this.errorCode = errorCodeParam;
        return this;
    }


    public String getDomain() {
        return this.domain;
    }


    public ErrorObj setDomain(final String domainParam) {
        this.domain = domainParam;
        return this;
    }


    public String getSubdomain() {
        return this.subdomain;
    }


    public ErrorObj setSubdomain(final String subdomainParam) {
        this.subdomain = subdomainParam;
        return this;
    }


    public String getBoundedcontext() {
        return this.boundedcontext;
    }


    public ErrorObj setBoundedcontext(final String boundedcontextParam) {
        this.boundedcontext = boundedcontextParam;
        return this;
    }


    public String getMicroservice() {
        return this.microservice;
    }


    public ErrorObj setMicroservice(final String microserviceParam) {
        this.microservice = microserviceParam;
        return this;
    }


}
