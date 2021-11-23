def call(Map config) { 
    String ksvc = sh(returnStdout: true, script: "kubectl get svc").trim()
    if(ksvc.count(config.name) > 0){
        sh "kubectl delete svc ${config.name}"
    }
    sh "kubectl apply -f k3s/service.yml"
}
