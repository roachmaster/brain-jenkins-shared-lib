def call(Map config) { 
    return sh(returnStdout: true, script: "gradle properties -q --no-daemon --console=plain -q | grep '^version:' | awk '{print \$2}'").trim()
}
