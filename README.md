# Java Maven CI/CD Pipeline with Jenkins, SonarQube, Helm, ArgoCD, and Kubernetes

This repository demonstrates a **CI/CD pipeline** for a Java Spring Boot application using:
- **Jenkins** – for automation
- **Maven** – for build and dependency management
- **SonarQube** – for static code analysis
- **Helm** – for Kubernetes packaging & deployment
- **ArgoCD** – for GitOps-based continuous delivery
- **Kubernetes (K3s/Minikube)** – for running workloads

---

## 📂 Repository Structure
java-maven-cicd/
│── java-app/ # Spring Boot application source code
│ ├── pom.xml
│ └── src/...
│
│── spring-boot-app-manifests/ # Kubernetes manifests (YAML)
│ ├── deployment.yaml
│ ├── service.yaml
│ └── namespace.yaml
│
│── spring-boot-app-helm/ # Helm chart for deployment
│ ├── Chart.yaml
│ ├── values.yaml
│ └── templates/
│ ├── deployment.yaml
│ ├── service.yaml
│ └── namespace.yaml
│
│── Jenkinsfile # Jenkins pipeline definition
│── README.md # Documentation


---

## 🚀 CI/CD Pipeline Flow

1. **Checkout Code** → Jenkins pulls the code from GitHub  
2. **Build & Test** → Maven compiles code and runs JUnit tests  
3. **Code Quality** → SonarQube analyzes the code  
4. **Package** → Application is packaged as JAR  
5. **Deploy to Test** → Jenkins deploys app to K8s using Helm  
6. **User Acceptance Tests (UAT)** → Selenium/Custom tests run  
7. **Promote to Production** → ArgoCD syncs manifests with cluster  

---

## ⚙️ Prerequisites

- **Jenkins** with plugins:
  - Git
  - Maven Integration
  - Pipeline
  - SonarQube Scanner
  - Kubernetes Continuous Deploy

- **Installed Tools**
  - Java 17+
  - Maven 3+
  - Docker
  - kubectl
  - Helm
  - k3s / Minikube cluster
  - ArgoCD installed in cluster
  - SonarQube server

