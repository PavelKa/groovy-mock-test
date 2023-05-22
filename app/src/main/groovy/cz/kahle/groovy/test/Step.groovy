package cz.kahle.groovy.test;

class Step {
    private String name

    Step(String name) {
        this.name = name
    }

    String getName() {
        return this.name
    }

    static String staticMethod() {
        return "original"
    }

    static String staticMethod2(param) {
        return param
    }
}