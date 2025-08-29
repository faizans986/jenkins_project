pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'java'
    }
    environment {
        SONARQUBE = credentials('sonar-token')
        ARGOCD = credentials('argocd')
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
        /*
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh "mvn sonar:sonar -f java-app/pom.xml -Dsonar.login=${SONARQUBE}"
                }
            }
        }
        */
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
