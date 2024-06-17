pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package' 
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'  
            }
        }

        stage('Deploy') {
            steps {
                sh './mvnw install -DskipTests'  
                sh 'java -jar target/champBuild-0.0.1-SNAPSHOT.jar &'  
            }
        }
    }

    post {
        success {
            echo '¡Despliegue exitoso!'
        }
        failure {
            echo 'El despliegue ha fallado. Revisar logs para más detalles.'
        }
    }
}
