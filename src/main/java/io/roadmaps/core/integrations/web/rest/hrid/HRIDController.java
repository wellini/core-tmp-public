package io.roadmaps.core.integrations.web.rest.hrid;

import io.roadmaps.core.hrid.HRIDUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HRIDController {

    @GetMapping("/api/hrid/serialize/{rawId}")
    public String serialize(@PathVariable("rawId") Long rawId) {
        return HRIDUtil.serialize(rawId);
    }

    @GetMapping("/api/hrid/deserialize/{serializedId}")
    public Long deserialize(@PathVariable("serializedId") String serializedId) {
        return HRIDUtil.deserialize(serializedId);
    }
}
