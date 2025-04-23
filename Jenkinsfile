
pipeline {
   agent any 

  
    stages {
        stage('Deploy to Docker Hub') {
            steps {
                echo 'Building image'
                sh './gradlew clean bootBuildImage'
                script {
                    def version = sh(script: "./gradlew -q printVersion", returnStdout: true).trim()
                    docker.withRegistry('https://registry.hub.docker.com', 'dthibau_docker') {
                        dockerImage.push "$version"
                    }
                 }                
                
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
                failure {
                    mail bcc: '', body: 'Docker build failed', cc: '', from: '', replyTo: '', subject: 'Build failed', to: 'david.thibau@gmail.com'
                }
            }
        }
        stage('Démarre le service en recette') {
            agent any
            environment {
                OPENAI_API_KEY = 'openai_api_key'
            }
            steps {
                echo 'Restarting plbsi-ai'
                script {
                   sh "docker compose -f docker-compose-chroma.yml down"
                   sh "docker compose -f docker-compose-chroma.yml up -d"
                }              
             } 	
        }
    }
           
}