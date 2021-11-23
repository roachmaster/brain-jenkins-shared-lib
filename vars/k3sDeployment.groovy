def call(Map config) { 
    String kdeployments = sh(returnStdout: true, script: "kubectl get deployments").trim()
    if(kdeployments.count(config.name) > 0){
        sh "kubectl delete deployment ${config.name}"
    }
    sh "kubectl create -f k3s/deployment.yml"
}
