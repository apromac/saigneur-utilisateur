pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = "saigneur-utilisateur"
        BUILD_TAG = "v1.0.${BUILD_NUMBER}"
        CONTAINER_NAME = "msaigneur-utilisateur"
        CONTAINER_PORT = 9002
        IMAGE_DEFAULT_DIR = "/usr/local/microservice/msaigneur"
        VOLUME_NAME = "postgres_data"
    }
    stages {
        stage('Build') {
            steps {
                echo 'Compilation du code source ...'
                sh '/usr/local/maven386/bin/mvn -version'
                sh '/usr/local/maven386/bin/mvn -B -DskipTests clean package'
            }
            post {
                success {
                    // we only worry about archiving the jar file if the build steps are successful
                    archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
                }
            }
        }
    }
}
