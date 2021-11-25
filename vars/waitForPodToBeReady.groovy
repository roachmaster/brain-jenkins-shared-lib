def call(Map config) { 
    boolean maxAttemptsTried = false
    boolean isReady = false;
    def hasFailed = false;
    def podName = "POD NOT FOUND"

    def currentNumOfReadinessChecks = 0;
    while(!isReady && !maxAttemptsTried){
        String[] podInfo = sh(returnStdout: true ,script: "kubectl get pods | grep ^${config.name}").trim().split("\\s+")
        def podInfoList = new ArrayList<String>(Arrays.asList(podInfo))
        podName = podInfoList.get(0)
        String readyStatus = podInfoList.get(1)

        def readyStatusPair = readyStatus.tokenize('/')
        if(readyStatusPair[0] == readyStatusPair[1]){
            isReady = true;
            maxAttemptsTried = true;
            println "podName: ${podName}\nreadyStatus: ${readyStatus}\nisReady: ${isReady}\n${currentNumOfReadinessChecks} out of ${config.maxNumOfAttempts} attempts\nmaxAttemptsTried: ${maxAttemptsTried}"
        }else {
            if(currentNumOfReadinessChecks == config.maxNumOfAttempts){
                isReady = true;
                maxAttemptsTried = true
                hasFailed = true
            }else{
                currentNumOfReadinessChecks++;
                sleep 15
            }
            println "podName: ${podName}\nreadyStatus: ${readyStatus}\nisReady: ${isReady}\n${currentNumOfReadinessChecks} out of ${config.maxNumOfAttempts} attempts\nmaxAttemptsTried: ${maxAttemptsTried}"
        }
    }
    if(hasFailed){
        error "${podName} is has failed to start within time frame"
    }else {
        println("${podName} is ready for testing")
    }
}
