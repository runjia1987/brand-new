package org.jackJew.brand.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Jack on 2017/3/18.
 */
@Configuration
@Slf4j
public class EnvConfig {

    /**
     * notice <b>static</b> modifier !!!
     * @return
     */
    @Bean
    public static PropertyPlaceholderConfigurer envConfig() {
        ClassPathResource configResource = new ClassPathResource("jdbc.properties");
        log.info("EnvConfig start init.");
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(configResource);
        log.info("EnvConfig end init.");
        return ppc;
    }
}
