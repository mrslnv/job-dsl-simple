job("dsl-simple-maven") {
    scm {
        git "https://github.com/TA-demo/simple-demo-app.git", {
            extensions {
                createTag false
            }
        }

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

