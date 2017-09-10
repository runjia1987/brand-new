package org.jackjew.brand.endpoint;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.jackjew.brand.model.Shop;
import org.jackjew.brand.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jack on 2017/3/20.
 */
@Path("/shop")
@Component
@Slf4j
@Api(tags = "shops APIs")
public class ShopSrvEndpoint {

  @Autowired
  private ShopService shopService;

  /**
   * query specified shop.
   */
  @GET
  @Path("query/{id}")
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "shop query", notes = "query specified shop")
  @ApiResponses( {
      @ApiResponse(code = 200, response = Shop.class, message = "response entity")
  })
  public Response query(@PathParam("id") String shopId) {
    Preconditions.checkNotNull(shopId);
    return Response.status(Response.Status.OK)
        .entity(shopService.queryById(Integer.valueOf(shopId)))
        .build();
  }

  /**
   * display mock.
   */
  @GET
  @Path("/display/{id}")
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "shop display test", notes = "display mock")
  @ApiResponses( {
      @ApiResponse(code = 200, response = Shop.class, message = "response entity")
  })
  public Response display(@PathParam("id") String shopId) {
    Preconditions.checkNotNull(shopId);
    return Response.status(Response.Status.OK)
        .entity(Shop.builder().id(Integer.parseInt(shopId)).build())
        .build(); // for test
  }

  /**
   * create shop.
   */
  @POST
  @Path("/create")
  @ResponseBody
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation("create shop")
  public Shop create(@Valid Shop shop) {
    log.info(shop.toString());

    shopService.save(shop);
    return shop;
  }
}
