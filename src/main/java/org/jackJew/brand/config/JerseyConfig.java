package org.jackJew.brand.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.validation.ValidationFeature;
import org.jackJew.brand.endpoint.BrandSrvEndpoint;
import org.jackJew.brand.utils.JerseyExceptionMapper;
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
        //register(MultiPartFeature.class);  // jersey-server incompatibility cause ClassNotFoundException
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // scan endpoint
        packages("org.jackJew.brand.endpoint");
        setApplicationName("brandnew-app");

        log.info("JerseyConfig init done.");

        configureSwagger();
        log.info("Swagger configured.");
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("Spring boot integration");
        config.setTitle("Spring boot integration APIs");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath("/api");
        config.setResourcePackage("org.jackJew.brand");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
