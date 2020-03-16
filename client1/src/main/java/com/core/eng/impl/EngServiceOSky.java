package com.core.eng.impl;

import com.core.eng.EEngTextFiles;
import com.core.eng.IEngModelUpdater;
import lombok.extern.slf4j.Slf4j;
import org.opensky.example.ExampleDecoder;
import org.opensky.libadsb.Position;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Service
public class EngServiceOSky extends ExampleDecoder implements IEngModelUpdater {

    /************************************************************************
     INTERFACE ENFORCING
     */

    /**
     * @brief
     */
    @Override
    public void updateModel(Model model) throws IOException {
        log.debug("Not yet implemented");
    }

    /************************************************************************
     IMPLEM PART
     */

    /**
     * @brief
     */
     public EngServiceOSky() {
        commands.put(5, (final AEngCommandParam param) -> {
            // time,serial,lat,lon,msg
            final long timeStamp = (long) Double.parseDouble(param.getValues()[0])*1000;
            final Position pos = (Position) param.getData();
            pos.setLatitude(Double.parseDouble(param.getValues()[2]));
            pos.setLongitude(Double.parseDouble(param.getValues()[3]));
            decodeMsg(timeStamp, param.getValues()[4], pos);
        });
        commands.put(4, (final AEngCommandParam param) -> {
            // time,lat,lon,msg
            final long timeStamp = (long) Double.parseDouble(param.getValues()[0])*1000;
            final Position pos = (Position) param.getData();
            pos.setLatitude(Double.parseDouble(param.getValues()[1]));
            pos.setLongitude(Double.parseDouble(param.getValues()[2]));
            decodeMsg(timeStamp, param.getValues()[3], pos);
        });
        commands.put(2, (final AEngCommandParam param) -> {
            // time,msg
            final long timeStamp = (long) Double.parseDouble(param.getValues()[0])*1000;
            final Position pos = (Position) param.getData();
            decodeMsg(timeStamp, param.getValues()[1], pos);
        });
    }

    /**
     * @throws Exception
     */
    public void execute() throws Exception {
        final AEngCommandParam param = new AEngCommandParam() {
            @Override
            public Object getRef() {
                return new Integer(getValues().length);
            }
        };
        param.setData(new Position());

        final Resource resource = new ClassPathResource(EEngTextFiles.TEST.getName());
        final List<String> positions = Files.readAllLines(resource.getFile().toPath());
        for(final String entry: positions) {
            param.setValues(entry.split(","));
            commands.get(((Integer)param.getRef()).intValue()).accept(param);
            log.info(param.getData().toString());
        }

    }

    private final HashMap<Integer, Consumer<AEngCommandParam>> commands = new HashMap<>();

}
