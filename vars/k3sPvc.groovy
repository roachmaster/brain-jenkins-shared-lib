def call(Map config) { 
    String kpvc = sh(returnStdout: true, script: 'kubectl get pvc')
    if(kpvc.count(config.name) > 0){
        sh "kubectl delete pvc ${config.name}"
    }
    sh "kubectl create -f k3s/pvc.yml"
}
