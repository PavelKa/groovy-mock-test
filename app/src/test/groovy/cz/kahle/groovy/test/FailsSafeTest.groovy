package cz.kahle.groovy.test

import spock.lang.Specification

class FailsSafeTest extends Specification {
    def "test failSafe all Failed"() {
        def failSafe = GroovySpy(FailSafe)
        when:
        failSafe.failSafe(3, { throw new RuntimeException("Failed") }, "failAllways")
        then:
        RuntimeException e = thrown()
        "failAllways Failed after 3 retries, Exception: [java.lang.RuntimeException: Failed, java.lang.RuntimeException: Failed, java.lang.RuntimeException: Failed]" == e.getMessage()
        3 * failSafe.log("WARN Failed", _)
    }

    def "test failSafe first OK"() {
        def failSafe = GroovySpy(FailSafe)
        when:
        def result = failSafe.failSafe(3, { "OK" }, "failsNever")
        then:
        noExceptionThrown()
        "OK" == result
        0 * failSafe.log(*_)
    }

    def "test failSafe only during first attempt "() {
        def nFail = new NFailUntil()
        def failSafe = GroovySpy(FailSafe)
        when:
        def result = failSafe.failSafe(3, { nFail.call() }, "failsNever")
        then:
        noExceptionThrown()
        "OK" == result
        1 * failSafe.log({it.startsWith( "WARN") },_)
    }

    class NFailUntil {
        int i = 0
        int successOn = 2
        def call() {
            if (++i < successOn) {
                throw new RuntimeException("Failed during $i attemtp")
           }
            "OK"
        }
    }

}
