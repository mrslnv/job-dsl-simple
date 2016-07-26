package com.dslexample

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job

class SimpleMavenJobBuilder {
    def jobNameBase
    def repoName
    def branchName

    SimpleMavenJobBuilder(jobNameBase, repoName, branchName) {
        this.jobNameBase = jobNameBase
        this.repoName = repoName
        this.branchName = branchName
    }

    Job build(DslFactory dslFactory) {
        dslFactory.job("$jobNameBase-$branchName") {
            it.description "This is a simple build for branch $branchName"
            scm {
                github repoName, branchName
            }
            steps {
                maven {
                    goals('clean')
                    goals('test')
                    mavenInstallation('Local')
                }
            }
            publishers {
                archiveJunit '**/surefire-reports/*.xml'
            }
        }
    }
}


