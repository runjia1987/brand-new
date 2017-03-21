package org.jackJew.brand.endpoint;

import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jackJew.brand.model.Shop;
import org.jackJew.brand.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jack on 2017/3/20.
 */
@Path("/shop")
@Api
@Component
@Slf4j
public class ShopSrvEndpoint {

    @Autowired
    private ShopService shopService;

    @GET
    @Path("query/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("shop query")
    public Response query(@PathParam("id") String shopId) {
        Preconditions.checkNotNull(shopId);
        return Response.status(Response.Status.OK)
                .entity(shopService.queryById(Integer.valueOf(shopId)))
                .build();
    }

    @GET
    @Path("/display/{id}")
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("shop display test")
    public Response display(@PathParam("id") String shopId) {
        Preconditions.checkNotNull(shopId);
        return Response.status(Response.Status.OK)
                .entity(Shop.builder().id(Integer.parseInt(shopId)).build())
                .build(); // for test
    }

    @POST
    @Path("/create")
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("create shop")
    public Shop create2(@Valid Shop shop) {
        log.info(shop.toString());

        shopService.save(shop);
        return shop;
    }
}
