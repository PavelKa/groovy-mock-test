package cz.kahle.groovy.test

import spock.lang.Specification

class TestedClassSpec extends Specification {
    def "greet should output greeting and name"() {
        given:

        def testedClass = new TestedClass()

        when:
        testedClass.greet()

        then:
        true
    }

    def "greet should output greeting and name from MockedClass"() {
        given:
        def mockedClass = GroovySpy(MockedClass, global: true)
        def testedClass = new TestedClass()

        when:
        testedClass.greet()

        then:
        1 * mockedClass.greet("Hii", "John")
    }

}