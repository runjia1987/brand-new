package org.jackjew.brand.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.validation.ValidationFeature;
import org.jackjew.brand.utils.JerseyExceptionMapper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

/**
 * Created by Jack on 2017/3/18.
 */
@Configuration
@ApplicationPath("/api")
@Slf4j
public class JerseyConfig extends ResourceConfig {

  /**
   * init.
   */
  @PostConstruct
  public void init() {
    // register filter class
    register(CsrfProtectionFilter.class)
        .register(RolesAllowedDynamicFeature.class);

    // register exceptionMapper
    register(JerseyExceptionMapper.class);

    // register feature
    register(JacksonFeature.class);
    register(LoggingFeature.class);
    register(ValidationFeature.class);
    // jersey-server incompatibility cause ClassNotFoundException
    //register(MultiPartFeature.class);

    property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

    // scan endpoint
    packages("org.jackjew.brand.endpoint");
    setApplicationName("springboot-jpa-cqrs-swagger-markup");

    log.info("JerseyConfig init done.");

    configureSwagger();
    log.info("Swagger configured.");
  }

  private void configureSwagger() {
    register(ApiListingResource.class);
    register(SwaggerSerializers.class);

    BeanConfig config = new BeanConfig();
    config.setDescription("Spring-boot JPA Swagger integration");
    config.setVersion("1.0");
    config.setTitle("Spring boot integration APIs");
    config.setSchemes(new String[] {"http", "https"});
    config.setHost("http://swagger.io/");
    config.setBasePath("/api");
    config.setResourcePackage("org.jackjew.brand");
    config.setPrettyPrint(true);
    config.setScan(true);
  }
}
