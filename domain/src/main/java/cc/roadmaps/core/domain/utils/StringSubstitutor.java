package cc.roadmaps.core.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringSubstitutor {

    private static final char KEY_PREFIX = '$';
    private static final char KEY_START_SYMBOL = '{';
    private static final char KEY_END_SYMBOL = '}';

    private final Map<String, String> kv = new HashMap<>();

    public static StringSubstitutor create() {
        return new StringSubstitutor();
    }

    public StringSubstitutor add(String key, String value) {
        kv.put(key, value);
        return this;
    }

    public StringSubstitutor add(String key, Object value) {
        kv.put(key, value.toString());
        return this;
    }

    public String replace(String str) {
        StringBuilder sb = new StringBuilder();
        char[] strArray = str.toCharArray();
        int i = 0;
        while (i < strArray.length - 1) {
            if (strArray[i] == KEY_PREFIX && strArray[i + 1] == KEY_START_SYMBOL) {
                i = i + 2;
                int begin = i;
                while (strArray[i] != KEY_END_SYMBOL) ++i;
                sb.append(kv.get(str.substring(begin, i++)));
            } else {
                sb.append(strArray[i]);
                ++i;
            }
        }
        if (i < strArray.length) sb.append(strArray[i]);
        return sb.toString();
    }
}
