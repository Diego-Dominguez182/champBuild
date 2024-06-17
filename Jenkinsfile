pipeline {
    agent any



    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Diego-Dominguez182/champBuild.git'  
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
                sh 'java -jar target/ChampBuildApplication.jar &'  
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
