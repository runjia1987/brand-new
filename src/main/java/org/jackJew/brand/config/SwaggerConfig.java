package org.jackJew.brand.config;

import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Created by Jack on 2017/3/18.
 */
@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream().forEach(
                (converter) -> log.info("configuredMessageConverters: " + converter.getClass()));
        super.configureMessageConverters(converters);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // This is intended to be used when the Spring MVC {@link DispatcherServlet} is mapped to "/"
        // thus overriding the Servlet container's default handling of static resources
        configurer.enable();
    }

    @Bean
    public Docket restConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(paths())
                .build()
                .useDefaultResponseMessages(false);
    }

    private Predicate<String> paths() {
        return (path) -> {return true; };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 RESTful APIs")
                .description("Swagger2 RESTful APIs")
                .version("1.0")
                .build();
    }
}
