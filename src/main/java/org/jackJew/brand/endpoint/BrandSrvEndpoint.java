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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
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

    //@POST
    //@Path("/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("create brand")
    public Brand create(@PathParam("id") String brandId, @FormParam("name") String name) {
        log.info(brandId + "," + name);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(brandId) && !Strings.isNullOrEmpty(name));
        Brand brand = Brand.builder().id(Integer.parseInt(brandId))
                .name(name)
                .business("主营业务：电子商务，互联网金融中介平台")
                .creator("RUNJIA")
                .headQuarter("上海浦东")
                .build();
        brandService.save(brand);
        return brand;
    }

    @POST
    @Path("/create")
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("create brand")
    public Brand create2(@Valid Brand brand) {
        log.info(brand.toString());

        brandService.save(brand);
        return brand;
    }
}
