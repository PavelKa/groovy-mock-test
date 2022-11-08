package cz.kahle.groovy.test

import groovy.sql.Sql
import spock.lang.Shared
import spock.lang.Specification


class TestDataTable extends Specification {

    def "data combination"() {
        expect:
        println("$result $issueType $issueState ")

        where:

        row << {
                def testCases = []
                def resultOK= "OK"
                def statusOK = ['s1','s2','s3']
                def issuesOK = ['bug','whatever']
                statusOK.each {s->
                    issuesOK.each { i->
                        testCases<< [resultOK,i,s]
                    }
                }
              testCases << ['ko','bug','s4']
              testCases << ['ok','bug','s4']
              testCases
        }.call()
        (result, issueType, issueState) = row



//        result|issueType|issueState
//        'a'|'b'|'c'

        //


//        bug deployed  ok
//        bug validated ok
//        bug deleted   ok
//        defect deployed  ok
//        defect validated ok
//        defect deleted   ok


    }

}