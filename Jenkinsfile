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
                    sh 'mvn -B -DskipTests release:perform'
                }
            }
            post {
                success {
                   archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

    }
}