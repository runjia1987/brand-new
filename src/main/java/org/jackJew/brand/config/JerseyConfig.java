package org.jackJew.brand.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jackJew.brand.utils.JerseyExceptionMapper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by Jack on 2017/3/18.
 */
@Configuration
@Slf4j
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        // register filter class
        register(CsrfProtectionFilter.class);
        register(EncodingFilter.class);
        register(RolesAllowedDynamicFeature.class);

        // register exceptionMapper
        register(JerseyExceptionMapper.class);

        // json serializer binding
        register(JacksonJsonProvider.class);

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
        config.setSchemes(new String[] { "http" });
        config.setBasePath("/");
        config.setResourcePackage("org.jackJew.brand.endpoint");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
