def call(Map config) { 
    String ksecrets = sh(returnStdout: true, script: "kubectl get secrets").trim()
    if(ksecrets.count(config.secretName) == 0){
        sh "kubectl create secret generic ${config.secretName} --from-literal=password=${config.credPw}"
    }
}
