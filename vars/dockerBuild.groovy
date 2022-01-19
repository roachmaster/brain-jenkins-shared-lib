def call(Map config) {
    sh "docker build ${config.dockerOpt} -t ${config.dockerName} . --network=host"
    sh "docker login --username ${config.DOCKER_USERNAME} --password ${config.DOCKER_PASSWORD}"
    sh "docker push ${config.dockerName}"
    sh "docker rmi ${config.dockerName}"
}
