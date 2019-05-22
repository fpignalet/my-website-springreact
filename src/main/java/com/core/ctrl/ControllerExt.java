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
  @RequestMapping(value = "/exthttpgetjson0", method = RequestMethod.GET)
  @CrossOrigin
  public String exthttpgetjson0() {
    return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[0]);
  }

  /**
   * @return
   */
  @RequestMapping(value = "/exthttpgetjson1", method = RequestMethod.GET)
  @CrossOrigin
  public String exthttpgetjson1() {
    return getEngineJSON().doLoadJSON(EngServiceJSON.getDataRepo() + EngServiceJSON.getFileNames()[1]);
  }

}
