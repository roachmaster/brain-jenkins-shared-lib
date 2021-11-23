def call(Map config) { 
    String kdeployments = sh(returnStdout: true, script: "kubectl get deployments").trim()
    if(kdeployments.count(config.deploymentName) > 0){
        sh "kubectl delete deployment ${config.deploymentName}"
    }
    sh "kubectl create -f k3s/deployment.yml"
}
