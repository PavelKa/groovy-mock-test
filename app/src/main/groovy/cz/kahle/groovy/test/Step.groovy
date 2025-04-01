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
    int getMaxNumberOfRunningInstance(config_deployer_instance, deployerInstance, maxDefault) {
        def max = null
        deployerInstance.split(',').each {
            def xMax = config_deployer_instance?.get(it)?.max
            if (max == null) {

            }
            if (xMax != null) {
                max = maxDefault
                return max
            }
            if (max == null || xMax < max) {
                max = xMax
            }
        }
        if (max == null) {
            steps.rbrLog.warn(logParams, "Maximální počet instancí pro $deployerInstance není definován, použije se defaultní hodnota $maxDefault")
            max = maxDefault
        } else {
            steps.rbrLog.info(logParams, "Maximální počet instancí pro $deployerInstance načten z konfigurace, hodnota: $maxDefault")
        }
        return max
    }
}