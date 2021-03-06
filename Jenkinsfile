pipeline {
    agent none
    options {
        quietPeriod(5)
    }
    stages {
        stage ('PR check') {
            agent any
            when {
                branch 'PR-*'
                }
            steps {
                sh '''
                echo "PULL REQUEST CHECK IS DONE HERE"
                '''
            }
        }
        stage('Test - Backend') {
            agent {
                docker {
                    image 'maven:alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            options {
                timeout(time: 2, unit: 'MINUTES')
            }
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    jacoco(
                          execPattern: 'target/*.exec',
                          classPattern: 'target/classes',
                          sourcePattern: 'src/main/java',
                          exclusionPattern: 'src/test*'
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
            options {
                timeout(time: 3, unit: 'MINUTES')
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
        stage('Prepare Docker Deployment') {
            agent any
            options {
                timeout(time: 1, unit: 'MINUTES')
            }
            steps {
                dir('/var/jenkins_home/medapp_server') {
                    sh 'rm -rf med_app'
                    sh 'mkdir med_app'
                }
                dir('/var/jenkins_home/medapp_server/med_app') {
                    git(
                       url: 'https://github.com/mariosdrth/Med_Docker.git',
                       credentialsId: 'git-creds',
                       branch: 'master',
                       changelog: false,
                       poll: false
                    )
                }
            }
        }
        stage('Build - Front End') {
            agent {
                docker {
                    image 'mariosdrth/ubuntu-node-ng-chrome:1.0'
                    args '--privileged'
                }
            }
            options {
                timeout(time: 10, unit: 'MINUTES')
            }
            steps {
                dir('/var/jenkins_home/medapp_server/med_app/client') {
                    sh 'mkdir clone'
                }
                dir('/var/jenkins_home/medapp_server/med_app/client/clone') {
                    git(
                       url: 'https://github.com/mariosdrth/Med-App-Client.git',
                       credentialsId: 'git-creds',
                       branch: 'master',
                       changelog: false,
                       poll: false
                    )
                    sh 'npm install'
                    sh 'ng build --prod'
                }
                dir('/var/jenkins_home/medapp_server') {
                    sh 'rm -rf ./med_app/client/dist/'
                    sh 'mkdir ./med_app/client/dist/'
                    sh 'cp -r ./med_app/client/clone/dist/* ./med_app/client/dist/.'
                }
            }
        }
        stage('Deploy') {
            agent any
            options {
                timeout(time: 5, unit: 'MINUTES')
            }
            steps {
                dir('/var/jenkins_home/medapp_server/med_app') {
                    sh 'docker-compose down'
                    sh 'cp /var/jenkins_home/workspace/pipeline-med-app/target/*.jar /var/jenkins_home/medapp_server/med_app/server/gdpr.jar'
                    sh 'docker-compose build --no-cache'
                    sh 'docker image prune -f'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}