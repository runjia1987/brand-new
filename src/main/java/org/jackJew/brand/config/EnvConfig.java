package org.jackJew.brand.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by Jack on 2017/3/18.
 */
@Configuration
@Slf4j
public class EnvConfig {

  static final String ENCODING = "UTF-8";

  /**
   * notice <b>static</b> modifier.
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

  /**
   * characterEncodingFilter.
   */
  @Bean
  public FilterRegistrationBean characterEncodingFilter() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setForceEncoding(true);
    characterEncodingFilter.setEncoding(ENCODING);

    registrationBean.setFilter(characterEncodingFilter);
    registrationBean.setOrder(1);
    return registrationBean;
  }
}
