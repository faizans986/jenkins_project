pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'java'
    }
    environment {
        SONARQUBE = credentials('asdASD@02520252')
        ARGOCD = credentials('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhcmdvY2QiLCJzdWIiOiJhZG1pbjphcGlLZXkiLCJuYmYiOjE3NTYzNDIxODQsImlhdCI6MTc1NjM0MjE4NCwianRpIjoiMjM2N2ViYWEtNTQyMS00MTY5LWE5ZmQtOGUyZWJhODYzMDNjIn0.LVVl8Uou1rTtONXwJl93PwPCV51FUtp5RoAHFcpBIqw')
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/faizans986/jenkins_project.git', branch: 'main'
            }
        }
        stage('Build with Maven') {
            steps {
                sh 'mvn clean install -f java-app/pom.xml'
            }
        }
        stage('Unit Tests') {
            steps {
                sh 'mvn test -f java-app/pom.xml'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh "mvn sonar:sonar -Dsonar.login=${SONARQUBE}"
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t my-springboot-app:latest java-app/'
            }
        }
        stage('Helm Deploy to Test') {
            steps {
                sh 'helm upgrade --install springboot ./spring-boot-app-helm -n test --create-namespace'
            }
        }
        stage('User Acceptance Tests') {
            steps {
                sh 'echo "Running UAT tests..."'
            }
        }
        stage('Promote to Prod via ArgoCD') {
            steps {
                sh 'argocd app sync springboot-app --auth-token ${ARGOCD}'
            }
        }
    }
}
