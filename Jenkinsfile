pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
        'jdk11'
    }
    stages {
        stage('Build') {
            steps {
                mvn 'clean package'
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
