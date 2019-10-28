pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'id'
                sh 'which docker-compose'
                sh 'docker-compose'
                sh 'pwd'
                sh 'cd /var/jenkins_home/med_app/'
                sh 'pwd'
                sh 'docker-compose up -d'
            }
        }
    }
}