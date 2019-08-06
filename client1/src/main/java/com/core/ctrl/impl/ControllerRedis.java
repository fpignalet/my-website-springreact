package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.data.IRDRepository;
import com.core.data.impl.redis.RDItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Some answers to http requests
 * code ripped from https://github.com/michaelcgood/spring-data-redis-example
 */
@Slf4j
@Controller
public class ControllerRedis extends AControllerBase {

    /************************************************************************
     PUBLIC IMPLEM PART:
     * @param key
     * @param value
     * @return
     */

    @RequestMapping(value = "/redisadd", method = RequestMethod.POST)
    public ResponseEntity<String> redisadd(
        @RequestParam String key,
        @RequestParam String value) {
        final RDItem rdItem = new RDItem(key, value);
        redisRepo.add(rdItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @return
     */
    @RequestMapping("/redisvalues")
    public @ResponseBody
    Map<String, String> findAll() {
        final Map<Object, Object> aa = redisRepo.findAllMovies();
        final Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<Object, Object> entry : aa.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, aa.get(key).toString());
        }

        return map;
    }

    /**
     * @param key
     * @return
     */
    @RequestMapping(value = "/redisdelete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisRepo.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /************************************************************************
     INIT PART
     */

    @Autowired
    private IRDRepository redisRepo;

}
