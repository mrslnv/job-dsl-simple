import groovy.json.JsonSlurper
import com.dslexample.SimpleMavenJobBuilder

def repo = "TA-demo/simple-demo-app"
URL branchUrl = "https://api.github.com/repos/$repo/branches".toURL()
List branches = new JsonSlurper().parse(branchUrl.newReader())

branches.each { branch ->
    new SimpleMavenJobBuilder("by-builder", repo, branch.name).build(this)
}



