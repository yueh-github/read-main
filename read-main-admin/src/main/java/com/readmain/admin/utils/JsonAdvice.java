package com.readmain.admin.utils;

import com.readmain.common.exception.SafJsonObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice
public class JsonAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonAdvice() {
        super("callback");
    }

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        Object object = bodyContainer.getValue();
        SafJsonObject jsonObject = new SafJsonObject(object);
        bodyContainer.setValue(jsonObject);
        super.beforeBodyWriteInternal(bodyContainer, contentType, returnType, request, response);
    }
}
