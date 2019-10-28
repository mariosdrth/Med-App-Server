pipeline {
    agent {
        docker {
            image 'maven:alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
        PATHDOCKERCOMP = "/usr/local/bin"
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
            post {
                success {
                   archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'pwd'
                sh 'whereis docker-compose'
            }
        }
    }
}