package org.jackJew.brand.endpoint;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jackJew.brand.model.Brand;
import org.jackJew.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jack on 2017/3/18.
 */
@Path("/brand")
@Component
@ApiModel
@Slf4j
public class BrandSrvEndpoint {

    @Autowired
    private BrandService brandService;

    @GET
    @Path("query/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("brand query")
    public Brand query(@PathParam("id") String brandId) {
        Preconditions.checkNotNull(brandId);
        return brandService.queryById(Integer.valueOf(brandId));
    }

    @GET
    @Path("/display/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("brand display test")
    public Brand display(@PathParam("id") String brandId) {
        Preconditions.checkNotNull(brandId);
        return Brand.builder().id(Integer.parseInt(brandId)).build(); // for test
    }

    @POST
    @Path("/{id}")
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
}
