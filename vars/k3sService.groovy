def call(Map config) { 
    String ksvc = sh(returnStdout: true, script: "kubectl get svc").trim()
    if(ksvc.count(config.svcName) > 0){
        sh "kubectl delete svc ${config.svcName}"
    }
    sh "kubectl apply -f k3s/service.yml"
}
