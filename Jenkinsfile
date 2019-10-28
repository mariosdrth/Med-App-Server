pipeline {
    agent {
        docker {
            image 'maven:alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
            post {
                success {
                   archiveArtifacts artifacts: 'java-app/target/*.jar', fingerprint: true
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
            sh 'cp /var/jenkins_home/workspace/pipeline-med-app/target/*.jar /var/jenkins_home/med_app/server/gdpr.jar'
            sh 'cd /var/jenkins_home/med_app/'
            sh 'docker-compose up -d'
        }
    }
}