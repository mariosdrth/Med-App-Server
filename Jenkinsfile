pipeline {
    agent none
    environment {
        PATHDOCKER='/usr/bin/docker'
    }
    stages {
        stage('Preparation') {
            agent any
            steps {
                sh 'rm -rf med_app'
                sh 'mkdir med_app'
                dir('med_app') {
                    git(
                       url: 'https://github.com/mariosdrth/Med_Docker.git',
                       credentialsId: 'git-creds',
                       branch: 'master'
                    )
                }
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
                dir('') {
                    sh 'mvn -B -DskipTests clean package'
                }
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
                dir('med_app/client') {
                    sh 'mkdir clone'
                }
                dir('clone') {
                    git(
                       url: 'https://github.com/mariosdrth/Med-App-Client.git',
                       credentialsId: 'git-creds',
                       branch: 'master'
                    )
                    sh 'cd ./med_app/client/clone && npm install'
                    sh 'cd ./med_app/client/clone && ng build --prod'
                }
                dir('') {
                    sh 'rm -rf ./med_app/client/dist/'
                    sh 'mkdir ./med_app/client/dist/'
                    sh 'cp -r ./med_app/client/clone/dist/* ./med_app/client/dist/.'
                }
            }
        }
        stage('Deploy') {
            agent any
            steps {
                dir('med_app') {
                    sh 'docker-compose down'
                    sh 'cp /var/jenkins_home/workspace/pipeline-med-app/target/*.jar /var/jenkins_home/med_app/server/gdpr.jar'
                    sh 'docker-compose build --no-cache'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}