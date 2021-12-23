package com.training.error;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;


public class HttpErrorHandle implements ErrorDecoder {

    private final ErrorDecoder.Default defErr = new Default();

    @Override
    public Exception decode(final String methodKeyParam,
                            final Response responseParam) {
        try {
            InputStream asInputStreamLoc = responseParam.body()
                                                        .asInputStream();
            ObjectMapper mapperLoc = new ObjectMapper();
            ErrorObj readValueLoc = mapperLoc.readValue(asInputStreamLoc,
                                                        ErrorObj.class);
            return new HttpFeignException(readValueLoc);
        } catch (Exception eLoc) {
            eLoc.printStackTrace();
        }

        return this.defErr.decode(methodKeyParam,
                                  responseParam);
    }

}
