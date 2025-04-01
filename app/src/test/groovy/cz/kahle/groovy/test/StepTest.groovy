package cz.kahle.groovy.test

import spock.lang.Specification
import spock.lang.Unroll

class StepTest extends Specification {
    @Unroll
    def "test getMaxNumberOfRunningInstance with deployerInstance: #deployerInstance"() {
        given:
        def step = new Step()
        def configDeployerInstance = [
                "instance1": [max: 5],
                "instance2": [max: 10]
        ]

        when:
        def result = step.getMaxNumberOfRunningInstance(configDeployerInstance, deployerInstance, maxDefault)

        then:
        result == expectedMax

        where:
        deployerInstance      | maxDefault | expectedMax
        "instance1"           | 3          | 5
        "instance2"           | 3          | 10
        "instance1,instance2" | 3          | 5
        "instance3"           | 3          | 3
        "instance1,instance3" | 3          | 3
    }
}
