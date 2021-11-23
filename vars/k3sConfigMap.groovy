def call(Map config) { 
    String kConfigMap = sh(returnStdout: true, script: 'kubectl get configmap').trim()
    if(kConfigMap.count(config.name) > 0){
        sh "kubectl delete configmap ${config.name}"
    }
    sh "kubectl create -f k3s/configmap.yml"
}
