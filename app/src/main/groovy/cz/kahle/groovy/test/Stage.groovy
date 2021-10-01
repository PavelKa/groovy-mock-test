package cz.kahle.groovy.test

import com.sun.org.slf4j.internal.LoggerFactory

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



class Stage {
    Step step

    private static Logger logger= LoggerFactory.getLogger(Stage.class);



    Stage(String name) {
        if(name == "Error" ) {throw new RuntimeException("Bad name")}
        this.step = new Step(name)

    }

    String run() {
        logger.info("test")
        return step.getName()

    }
}