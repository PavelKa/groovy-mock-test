package cz.kahle.groovy.test

import spock.lang.Specification

class GlobalMockTest extends Specification {
    def "Step.staticMethod with Mock"() {
        given:
        GroovySpy(Step, global: true)

        when:
        def result = Step.staticMethod()
        def result2 = Step.staticMethod()


        then:
        result == "stubbed"
        result2 == "stubbed2"
        1 * Step.staticMethod() >> "stubbed"
        1 * Step.staticMethod() >> "stubbed2"

    }

    def "Step.staticMethod without Mock"() {
        expect:
        Step.staticMethod() == "original"

    }
}