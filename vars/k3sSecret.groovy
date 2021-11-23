def call(Map config) { 
    String ksecrets = sh(returnStdout: true, script: "kubectl get secrets").trim()
    if(ksecrets.count(config.name) == 0){
        sh "kubectl create secret generic ${config.name} --from-literal=password=${config.credPw}"
    }
}
