package cz.kahle.groovy.test
import org.slf4j.Logger

class StageTest extends spock.lang.Specification {

    def "use Spock mock"() {
        given:
        def step = Mock(Step) {
            getName() >> "Joe"
        }
        def stage = new Stage("John")
        stage.step = step

        expect:
        stage.run() == "Joe"
    }
    def "use global GroovySpy static with param any params"() {
        given:
        GroovySpy(Step, global: true) {}
        when:
        def x = Step.staticMethod('B')
        then:
        x == stubbed
        1 * Step.staticMethod2 (*_ )>> "stubbed"

    }
    def "use global GroovySpy static with param"() {
        given:
        GroovySpy(Step, global: true) {}
        Step.staticMethod2('A') >> "stubbed"
        expect:
        Step.staticMethod2('A') == "stubbed"
        Step.staticMethod2('B') == "B"
    }


    def "use global GroovySpy"() {
        given:
        GroovySpy(Step, global: true) {
            getName() >> "Joe"
        }
        Step.staticMethod() >> "stubbed"
        def stage = new Stage("John")
        expect:
        Step.staticMethod() == "stubbed"

        stage.run() == "Joe"
    }

    def "use global GroovyMock"() {
        given:
        GroovyMock(Step, global: true)
        new Step(*_) >> Mock(Step) {
            getName() >> "Joe"
        }
        Step.staticMethod() >> "stubbed"
        def stage = new Stage("John")

        expect:

        Step.staticMethod() == "stubbed"
        stage.run() == "Joe"

    }

    def "mockLogger"() {
        given:
        def stage = new Stage("John")
        stage.logger = Mock(Logger)
        when:
        stage.run() == "Joe"
        then:
        1 * stage.logger.info('test')
    }

    def "testExeption"() {
        when:
        def stage = new Stage("Error")
        then:
        def error = thrown(RuntimeException)
        "Bad name"==error.message
    }
}

