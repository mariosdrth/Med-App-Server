pipeline {
    agent none
    environment {
        PATHDOCKER='/usr/bin/docker'
    }
    stages {
        stage('Preparation') {
            agent any
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'git-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                  sh './prepare.sh $USERNAME $PASSWORD'
                }
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'maven:alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
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
            agent {
                docker {
                    image 'maven:alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
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
            agent any
            steps {
                sh 'cp /var/jenkins_home/workspace/pipeline-med-app/target/*.jar /var/jenkins_home/med_app/server/gdpr.jar'
                sh 'cd ./med_app/ && docker-compose -f docker-compose.yml build --no-cache'
                sh 'cd ./med_app/ && docker-compose up -d'
            }
        }
    }
}