package com.core.ctrl;

import com.core.eng.EngServiceJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
public class ControllerExternal extends AControllerBase {

  /**
   * @param engineJSON
   */
  public ControllerExternal(EngServiceJSON engineJSON) {
    super(null, engineJSON, null);
  }

  /**
   * @return
   */
  @RequestMapping(value = "/ext_test1", method = RequestMethod.GET)
  @CrossOrigin
  public String ext_test1() {
    log.info("OK");
    return getEngineJSON().doLoadJSON("src/main/resources/static/datatest.js");
  }

  /**
   * @return
   */
  @RequestMapping(value = "/ext_test2", method = RequestMethod.GET)
  @CrossOrigin
  public String ext_test2() {
    log.info("OK");
    return getEngineJSON().doLoadJSON("src/main/resources/static/datafpi.js");
  }

  @Override
  protected void updateModel(String name, Model model) {
  }

  /**
   *
   */
  private static Logger log = LoggerFactory.getLogger(ControllerExternal.class);

}
