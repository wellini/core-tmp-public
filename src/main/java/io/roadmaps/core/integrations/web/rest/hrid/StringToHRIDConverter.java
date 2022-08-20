package io.roadmaps.core.integrations.web.rest.hrid;

import io.roadmaps.core.hrid.HRID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToHRIDConverter implements Converter<String, HRID> {

    @Override
    public HRID convert(String source) {
        return HRID.fromString(source);
    }
}
