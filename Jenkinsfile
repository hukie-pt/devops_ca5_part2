pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out ...'
                git 'https://bitbucket.org/tomas_osswald/devops-20-21-1201784/src/master/ca2/part2/'
            }
        }
        stage('Assemble') {
            steps {
                echo 'Assembling...'
		        dir('ca2/part2/') {
		        	sh 'chmod +x gradlew'
                    		sh './gradlew assemble'
		        }
            }
        }
	    stage('Test') {
	        steps {
		        echo 'Testing...'
		        dir('ca2/part2/') {
		            sh './gradlew test'
		        }
	        }
	    }
	    stage('Javadoc') {
	        steps {
	            echo 'Generating Javadoc...'
	            dir('ca2/part2/') {
	                sh './gradlew javadoc'
	            }
                javadoc javadocDir: 'ca2/part2/build/docs/javadoc', keepAll: true
	            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'ca2/part2/build/docs/javadoc', reportFiles: 'index.html', reportName: 'Javadoc HTML Report', reportTitles: 'Javadoc'])
	        }
	    }
	    stage ('Archiving') {
           	steps {
                    echo 'Archiving...'
                    archiveArtifacts 'ca2/part2/build/libs/*'
            	}
            }
        stage("Publish Docker Image") {
            steps{
                echo 'Publishing Image...'
                dir('ca2/part2/'){
                    script{
                        docker.withRegistry('', 'dockerHub') {
                            def dockerImage = docker.build("tomasmco/devops_ca5_part2:${BUILD_NUMBER}")
                            dockerImage.push()
                        }
                    }
                }
            }
        }
    }
}
