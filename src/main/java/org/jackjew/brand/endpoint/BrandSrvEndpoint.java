package org.jackjew.brand.endpoint;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.jackjew.brand.model.Brand;
import org.jackjew.brand.model.Shop;
import org.jackjew.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Jack on 2017/3/18.
 */
@Path("/brand")
@Component
@Slf4j
@Api(tags = "brand APIs")
public class BrandSrvEndpoint {

  @Autowired
  private BrandService brandService;

  /**
   * query specified brand.
   */
  @GET
  @Path("query/{id}")
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation("brand query")
  @ApiResponses( {
      @ApiResponse(code = 200, response = Shop.class, message = "response entity")
  })
  public Response query(@PathParam("id") String brandId) {
    Preconditions.checkNotNull(brandId);
    return Response.status(Response.Status.OK)
        .entity(brandService.queryById(Integer.valueOf(brandId)))
        .build();
  }

  /**
   * display mock brand.
   */
  @GET
  @Path("/display/{id}")
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @ApiOperation("brand display test")
  @ApiResponses( {
      @ApiResponse(code = 200, response = Shop.class, message = "response entity")
  })
  public Response display(@PathParam("id") String brandId) {
    Preconditions.checkNotNull(brandId);
    return Response.status(Response.Status.OK)
        .entity(Brand.builder().id(Integer.parseInt(brandId)).build())
        .build(); // for test
  }

  /**
   * create brand.
   */
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

  /**
   * test info RequestBody.
   */
  @POST
  @Path("test")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @ApiOperation("test")
  public String test(@NotNull @RequestBody String request) {
    log.info(request);
    return request;
  }
}
