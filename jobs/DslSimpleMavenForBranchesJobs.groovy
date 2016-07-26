import groovy.json.JsonSlurper

def repo = "TA-demo/simple-demo-app"
URL branchUrl = "https://api.github.com/repos/$repo/branches".toURL()
List branches = new JsonSlurper().parse(branchUrl.newReader())

branches.each { branch ->
    String safeBranchName = branch.name.replaceAll('/', '-')

    job("dsl-simple-maven-$safeBranchName") {
        scm {
            github repo, branch.name
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


