package org.jackJew.brand.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Jack on 2017/3/18.
 */
@SpringBootApplication(scanBasePackages = {"org.jackJew.brand"})
@EnableWebMvc
public class Bootstrap {

  public static void main(String[] args) {
    SpringApplication.run(Bootstrap.class, args);
  }
}
