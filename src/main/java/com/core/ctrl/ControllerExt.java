package com.core.ctrl;

import com.core.eng.EngServiceJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@Slf4j
public class ControllerExt extends AControllerBase {

  /**
   * @param engineJSON
   */
  public ControllerExt(EngServiceJSON engineJSON) {
    super(null, engineJSON, null);
  }

  /**
   * @return
   */
  @RequestMapping(value = "/httpext1", method = RequestMethod.GET)
  @CrossOrigin
  public String httpext1() {
    return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[0]);
  }

  /**
   * @return
   */
  @RequestMapping(value = "/httpext2", method = RequestMethod.GET)
  @CrossOrigin
  public String httpext2() {
    return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
  }

}
