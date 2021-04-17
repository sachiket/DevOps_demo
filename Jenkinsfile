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