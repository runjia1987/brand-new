package org.jackJew.brand.endpoint;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jackJew.brand.model.Brand;
import org.jackJew.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jack on 2017/3/18.
 */
@Path("/brand")
@Component
@Api
@Slf4j
public class BrandSrvEndpoint {

    @Autowired
    private BrandService brandService;

    @GET
    @Path("query/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("brand query")
    public Response query(@PathParam("id") String brandId) {
        Preconditions.checkNotNull(brandId);
        return Response.status(Response.Status.OK)
                .entity(brandService.queryById(Integer.valueOf(brandId)))
                .build();
    }

    @GET
    @Path("/display/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("brand display test")
    public Response display(@PathParam("id") String brandId) {
        Preconditions.checkNotNull(brandId);
        return Response.status(Response.Status.OK)
                .entity(Brand.builder().id(Integer.parseInt(brandId)).build())
                .build(); // for test
    }

    @POST
    @Path("/create")
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("create brand")
    public Brand create(@Valid Brand brand) {
        log.info(brand.toString());

        brandService.save(brand);
        return brand;
    }

    @POST
    @Path("test")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation("test")
    public String test(@NotNull @RequestBody String request) {
        log.info(request);
        return request;
    }
}
