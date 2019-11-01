pipeline {
    agent none
    stages {
        stage('Build - Backend') {
            agent {
                docker {
                    image 'maven:alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                dir('') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
    }
}