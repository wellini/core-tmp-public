package cc.roadmaps.validation.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(staticName = "create")
public class ValidationReport {

    @Getter
    private final Map<String, List<String>> errors = new HashMap<>();

    public void add(String location, List<String> messages) {
        errors.put(location, messages);
    }
}
