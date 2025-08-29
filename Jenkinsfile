pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'java'
    }
    environment {
        ARGOCD = credentials('argocd')   // ArgoCD token from Jenkins credentials
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
                withSonarQubeEnv('SonarQubeServer') {   // must match Jenkins SonarQube server name
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh '''
                          mvn sonar:sonar \
                            -f java-app/pom.xml \
                            -Dsonar.projectKey=my-springboot-app \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_TOKEN
                        '''
                    }
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
                sh 'argocd app sync springboot-app --auth-token $ARGOCD'
            }
        }
    }
}
