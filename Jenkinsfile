pipeline {
    agent none
    environment {
        PATHDOCKER='/usr/bin/docker'
        USERNAME='mariosdrth'
        PASSWORD='13041981ThPa'
    }
    stages {
        stage('Preparation') {
            agent any
            steps {
                sh 'rm -rf med_app'
                sh 'git clone https://${USERNAME}:${PASSWORD}@github.com/mariosdrth/Med_Docker.git med_app'
            }
        }
        stage('Build - Backend') {
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
        stage('Build - Front End') {
            agent {
                docker {
                    image 'alexsuch/angular-cli:6.0'
                    args '--privileged'
                }
            }
            steps {
                sh 'git clone https://${USERNAME}:${PASSWORD}@github.com/mariosdrth/Med-App-Client.git ./med_app/client/clone'
                sh 'cd ./med_app/client/clone && npm install'
                sh 'cd ./med_app/client/clone && ng build --prod'
                sh 'rm -rf ./med_app/client/dist/'
                sh 'mkdir ./med_app/client/dist/'
                sh 'cp -r ./med_app/client/clone/dist/* ./med_app/client/dist/.'
            }
        }
        stage('Deploy') {
            agent any
            steps {
                sh 'cd ./med_app/ && docker-compose down'
                sh 'cp /var/jenkins_home/workspace/pipeline-med-app/target/*.jar /var/jenkins_home/med_app/server/gdpr.jar'
                sh 'cd ./med_app/ && docker-compose -f docker-compose.yml build --no-cache'
                sh 'cd ./med_app/ && docker-compose up -d'
            }
        }
    }
}