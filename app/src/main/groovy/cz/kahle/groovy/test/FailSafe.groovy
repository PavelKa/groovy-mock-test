package cz.kahle.groovy.test

class FailSafe {
    void log(message, e) {
        println(message)
    }

def  failSafe(int attempts = 5, Closure errorHandler = { e -> log("WARN $e.message", e) }, Closure body, String closureName) {
        int retries = 0
        def exceptions = []
        while (retries++ < attempts) {
            try {
                return body.call()
            } catch (e) {
                exceptions << e
                errorHandler.call(e)
            }
        }
        throw new RuntimeException("$closureName Failed after $attempts retries, Exception: $exceptions")
    }
}