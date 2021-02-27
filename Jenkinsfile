pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "cd docker"
                sh "docker-compose build"
                sh "docker-compose up -d"
            }
        }
    }
}
