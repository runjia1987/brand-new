package org.jackjew.brand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

  @RequestMapping(method = RequestMethod.GET, value = "/display")
  @ResponseBody
  public String display() {
    return "test";
  }
}
