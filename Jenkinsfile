
pipeline {
   agent any 
   tools {
        jdk 'JDK17'
    }

  
    stages {
        stage('Deploy to Docker Hub') {
            steps {
                echo 'Building image'
                sh './gradlew clean check bootBuildImage'
                
                script {
                    def version = sh(script: "./gradlew properties | grep version:", returnStdout: true).substring(9).trim()
                    echo "Version: ${version}"
                    sh "docker tag poc-plb:${version} dthibau/poc-plb:${version}"
                    def dockerImage = docker.image("dthibau/poc-plb:${version}")
                    docker.withRegistry('https://registry.hub.docker.com', 'dthibau_docker') {
                        dockerImage.push "$version"
                    }
                 }                
                
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
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
                   sh "docker rmi dthibau/poc-plb:${version}"
                   sh "docker compose -f docker-compose-chroma.yml up -d"
                }              
             } 	
        }
    }
           
}