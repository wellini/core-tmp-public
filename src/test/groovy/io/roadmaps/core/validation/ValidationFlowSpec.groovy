package io.roadmaps.core.validation

import io.roadmaps.core.validation.utils.TestRules
import io.roadmaps.core.validation.utils.TestUser
import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

class ValidationFlowSpec extends Specification {

    def "Scenario: Root flow should not check requirements"() {
        given:
        def rule1 = TestRules.alwaysAccepting()
        def rule2 = TestRules.alwaysFailing()

        when: "There is only root flow with configured requirements"
        def vf = ValidationFlow.start()
                .require(rule1, "")
                .strictlyRequire(rule2, "")

        then: "No root rules checked"
        0 * rule1.check(_)
        0 * rule2.check(_)
        !vf.hasErrors()
        vf.getValidationReport().getErrors().isEmpty()
    }

    def "Scenario: Flow successfully executes all required checks"() {
        given:
        def user = TestUser.createFilledWithRandomData()
        user.setName(RandomStringUtils.randomAlphabetic(1))
        user.setAge(null)
        user.getBillingInfo().setAddress(RandomStringUtils.randomAlphabetic(60, 70))

        when:
        def vf =
                ValidationFlow.start()
                        .forProperty("name", user::getName)
                            .strictlyRequire(Rules.notBlank(), "Name should exist")
                            .require(Rules.minAndMaxLength(2, 100), "Name length should be bigger than 2 and less than 100")
                            .and()
                        .forProperty("age", user::getAge)
                            .strictlyRequire(Rules.notNull(), "Age should exist")
                            .require(Rules.minAndMax(0, 100), "Age should be bigger than 0 and less than 100")
                            .and()
                        .forProperty("billingInfo", user::getBillingInfo)
                            .strictlyRequire(Rules.notNull(), "Billing info should exist")
                            .forProperty("address", () -> user.getBillingInfo().getAddress())
                                .strictlyRequire(Rules.notBlank(), "Address should exist")
                                .require(Rules.minAndMaxLength(0, 50), "Address length should be bigger than 0 and less than 50")
                                .and()
                            .and()
        def report = vf.getValidationReport()

        then:
        report.getErrors().get("name").size() == 1
        report.getErrors().get("name").get(0) == "Name length should be bigger than 2 and less than 100"

        report.getErrors().get("age").size() == 1
        report.getErrors().get("age").get(0) == "Age should exist"

        report.getErrors().get("billingInfo.address").size() == 1
        report.getErrors().get("billingInfo.address").get(0) == "Address length should be bigger than 0 and less than 50"
    }

    def "Scenario: Flow successfully executes all required checks in subflows"() {
        given:
        def rule1 = (String s) -> false
        def rule2 = (String s) -> false

        when:
        def vf =
                ValidationFlow.start()
                        .forProperty("paymentInfo", "")
                            .require(rule1, "MESSAGE1")
                            .forProperty("address", "")
                                .require(rule2, "MESSAGE2")
                            .and()
                        .and()
        def report = vf.getValidationReport()

        then:
        report.getErrors().size() == 2
        report.getErrors().get("paymentInfo").size() == 1
        report.getErrors().get("paymentInfo").get(0) == "MESSAGE1"
        report.getErrors().get("paymentInfo.address").size() == 1
        report.getErrors().get("paymentInfo.address").get(0) == "MESSAGE2"
    }
}
