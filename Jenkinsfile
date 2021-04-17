pipeline{
    agent any
    tools {
		maven 'maven'
	}
    environment
    {
        VERSION="$BUILD_NUMBER"
        PROJECT='flight-search'
        IMAGE= "$PROJECT:latest"
        ECRURL='https://hub.docker.com/repository/docker/sachiket/flightapi'
        DOCCRED='dockerhub_id'

    }

    stages {
       
        stage ('Compile Stage'){
			steps{
				sh 'mvn clean compile'	
			}	
		}

		stage ('Sonarqube deployment Stage'){
			steps{
				sh 'mvn sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=42c2f6e3649c1a7266c68c8a7ba32f012e27617b -Dsonar.organization=default-organization'	
			}	
		}

        stage ('Build executable jar'){
			steps{
				sh 'mvn install'	
			}	
		}
      
        stage('Image Build'){
            steps{
                script{
                    docker.build('$IMAGE')
                }
            }
        }
        stage('Push Image'){
            steps{
                script{
                    docker.withRegistry(ECRURL, DOCCRED){
                        docker.image(IMAGE).push()
                    }
                }
            }  
        }
    }

    post{
        always{
            sh "docker rmi $IMAGE | true"
        }
    }
}