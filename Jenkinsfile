pipeline {
    agent any
    tools {
        maven 'Maven1'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn install'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo "Testing...."
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploying...."
            }
        }
    }
}
