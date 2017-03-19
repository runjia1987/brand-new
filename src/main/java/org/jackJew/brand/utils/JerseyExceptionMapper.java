package org.jackJew.brand.utils;

import lombok.extern.slf4j.Slf4j;
import org.jackJew.brand.model.ErrorEntity;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Jack on 2017/3/18.
 */
@Provider
@Slf4j
public class JerseyExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        log.error("", e);
        ErrorEntity entity = ErrorEntity.builder().status("0001")
                .msg(e == null ? "unknown" : "server internal error").build();
        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }
}
