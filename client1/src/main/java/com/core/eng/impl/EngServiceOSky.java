package com.core.eng.impl;

import com.core.eng.IEngModelUpdater;
import lombok.extern.slf4j.Slf4j;
import org.opensky.example.ExampleDecoder;
import org.opensky.libadsb.Position;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
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
        for(final String entry: positions) {
            param.setValues(entry.split(","));
            commands.get(((Integer)param.getRef()).intValue()).accept(param);
            log.info(param.getData().toString());
        }

    }

    private static final String[] positions = {
        "1,48.0775474,11.6425398,8d4b19f39911088090641010b9b0",
        "2,48.0775474,11.6425398,8d4ca513587153a8184a2fb5adeb",
        "3,48.0775474,11.6425398,8d3413c399014e23c80f947ce87c",
        "4,48.0775474,11.6425398,5d4ca88c079afe",
        "5,48.0775474,11.6425398,a0001838ca3e51f0a8000047a36a",
        "6,48.0775474,11.6425398,8d47a36a58c38668ffb55f000000",
        "7,48.0775474,11.6425398,5d506c28000000",
        "8,48.0775474,11.6425398,a8000102fe81c1000000004401e3",
        "9,48.0775474,11.6425398,a0001839000000000000004401e3"
    };

    private final HashMap<Integer, Consumer<AEngCommandParam>> commands = new HashMap<>();

}
