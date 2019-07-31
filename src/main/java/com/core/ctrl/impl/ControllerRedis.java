package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.data.impl.redis.RedisMovie;
import com.core.redis.IRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Some answers to http requests
 */
@Slf4j
@RestController
public class ControllerRedis extends AControllerBase {

    /************************************************************************
     PUBLIC IMPLEM PART:
     */

    @RequestMapping(value = "/redisadd", method = RequestMethod.POST)
    public ResponseEntity<String> redisadd(
        @RequestParam String key,
        @RequestParam String value) {
        RedisMovie redisMovie = new RedisMovie(key, value);
        redisRepo.add(redisMovie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/redisvalues")
    public Map<String, String> findAll() {
        Map<Object, Object> aa = redisRepo.findAllMovies();
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<Object, Object> entry : aa.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, aa.get(key).toString());
        }
        return map;
    }

    @RequestMapping(value = "/redisdelete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisRepo.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /************************************************************************
     INNER IMPLEM PART:
     */
    /**
     * @return
     * @throws IOException
     */
    protected String getResult(final String message) throws IOException {
        return "{ \"result\": \"" + message + "\" }";
    }

    /**
     * @return
     * @throws IOException
     */
    protected String getError(final String message, final Exception e) {
        e.printStackTrace();
        return "{ \"result\": \"" + message + "\" }";
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param redisRepo
     */
    public ControllerRedis(
        final IRedisRepository redisRepo
    ) {
        super(null, null, null, null);
        this.redisRepo = redisRepo;
    }

    @Autowired
    final IRedisRepository redisRepo;

}
