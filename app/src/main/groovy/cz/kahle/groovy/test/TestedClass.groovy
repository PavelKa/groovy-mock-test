package cz.kahle.groovy.test

class TestedClass {
    void greet() {
        MockedClass mockedClass = new MockedClass()
        print mockedClass.greet("Hi", "John")
    }
}
