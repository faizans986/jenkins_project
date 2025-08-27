pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'Java11'
    }
    environment {
        SONARQUBE = credentials('sonar-token')
        ARGOCD = credentials('argocd-token')
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-username/java-maven-cicd.git', branch: 'main'
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
