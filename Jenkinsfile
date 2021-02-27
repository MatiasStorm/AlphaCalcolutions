pipeline {
    agent any
    tools {
        maven 'Maven1'
    }
    stages {
        stage('Build') {
            steps {
                sh "docker-compose build"
                sh "docker-compose up -d"
            }
        }
    }
}
