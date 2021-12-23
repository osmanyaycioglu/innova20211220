package com.training.error;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ConfigurationProperties(prefix = "app")
public class MyRestErrorHandler {

    private String domain;
    private String subdomain;
    private String boundedcontext;
    private String microservice;


    @ExceptionHandler(HttpFeignException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorObj handleException(final HttpFeignException exp) {
        return this.createBaseErrorObj()
                   .setMessage("Error while calling anothor MS")
                   .setErrorCode(13350)
                   .addError(exp.getErrorObj());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(final IllegalArgumentException exp) {
        return this.createBaseErrorObj()
                   .setMessage(exp.getMessage())
                   .setErrorCode(10040);
    }

    private ErrorObj createBaseErrorObj() {
        return ErrorObj.create()
                       .setDomain(this.domain)
                       .setSubdomain(this.subdomain)
                       .setMicroservice(this.microservice)
                       .setBoundedcontext(this.boundedcontext);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(final MethodArgumentNotValidException exp) {
        ErrorObj rootLoc = this.createBaseErrorObj()
                               .setMessage("Validation error")
                               .setErrorCode(11030);

        List<ObjectError> allErrorsLoc = exp.getAllErrors();
        for (ObjectError objectErrorLoc : allErrorsLoc) {
            rootLoc.addError(this.createBaseErrorObj()
                                 .setMessage(objectErrorLoc.toString())
                                 .setErrorCode(11031));
        }
        return rootLoc;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(final ConstraintViolationException exp) {
        ErrorObj rootLoc = this.createBaseErrorObj()
                               .setMessage("Validation error")
                               .setErrorCode(11070);

        Set<ConstraintViolation<?>> allErrorsLoc = exp.getConstraintViolations();
        for (ConstraintViolation<?> objectErrorLoc : allErrorsLoc) {
            rootLoc.addError(this.createBaseErrorObj()
                                 .setMessage(objectErrorLoc.toString())
                                 .setErrorCode(11071));
        }
        return rootLoc;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleException(final Exception exp) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(this.createBaseErrorObj()
                                       .setMessage(exp.getMessage())
                                       .setErrorCode(15000));
    }


    public String getDomain() {
        return this.domain;
    }


    public void setDomain(final String domainParam) {
        this.domain = domainParam;
    }


    public String getSubdomain() {
        return this.subdomain;
    }


    public void setSubdomain(final String subdomainParam) {
        this.subdomain = subdomainParam;
    }


    public String getBoundedcontext() {
        return this.boundedcontext;
    }


    public void setBoundedcontext(final String boundedcontextParam) {
        this.boundedcontext = boundedcontextParam;
    }


    public String getMicroservice() {
        return this.microservice;
    }


    public void setMicroservice(final String microserviceParam) {
        this.microservice = microserviceParam;
    }


}
