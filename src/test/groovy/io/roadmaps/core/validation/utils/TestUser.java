package io.roadmaps.core.validation.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUser {

    private String name;

    private Integer age;

    private TestUserBillingInfo billingInfo;

    public static TestUser createFilledWithRandomData() {
        TestUserBillingInfo billingInfo = new TestUserBillingInfo(
                RandomStringUtils.randomAlphabetic(10),
                LocalDateTime.now()
        );
        return new TestUser(
                RandomStringUtils.randomAlphabetic(10),
                RandomUtils.nextInt(0, 100),
                billingInfo
        );
    }

    @Data
    @AllArgsConstructor
    public static class TestUserBillingInfo {

        private String address;

        private LocalDateTime dateOfBirth;
    }
}
