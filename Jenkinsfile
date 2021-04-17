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
        registry='sachiket/flightapi'
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
                    docker.build registry + "$BUILD_NUMBER"
                }
            }
        }
        stage('Push Image'){
            steps{
                script{
                    docker.withRegistry('', DOCCRED){
                        dockerImage.push()
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