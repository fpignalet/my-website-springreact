package com.core.ctrl;

import com.core.eng.EngService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
public class ControllerExternal extends AControllerBase {

  /**
   * @param engine1
   */
  public ControllerExternal(EngService engine1) {
    super(engine1);
  }

  /**
   * @return
   */
  @RequestMapping(value = "/ext_test1", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3000") //TODO change me!
  public String pong() {
    return "pong";
  }

  /**
   * @return
   */
  @RequestMapping(value = "/ext_test2", method = RequestMethod.GET)
  @CrossOrigin(origins = "http://localhost:3000") //TODO change me!
  public String other() {
    return "other";
  }

}
