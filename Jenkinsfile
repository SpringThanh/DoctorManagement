pipeline {
    agent any
    environment {
        JAVA_HOME = "/opt/java/jdk-17"
        PATH = "$JAVA_HOME/bin:/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.9.9/bin:$PATH"
    }
    stages {
        stage('Clone Source Code') {
            steps {
                git branch: 'main', url: 'https://github.com/SpringThanh/DoctorManagement.git'
            }
        }
        stage('Build Project') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }
}