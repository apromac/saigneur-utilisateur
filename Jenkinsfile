pipeline {
    agent any
    tools {
        maven "Maven386"
    }

    stages {
        stage('Build') {
            steps {
                echo '--------------------< Compilation du code source >--------------------'
                sh 'mvn -B -DskipTests clean package'
            }
//             post {
//                 success {
//                     // we only worry about archiving the jar file if the build steps are successful
//                     archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
//                 }
//             }
        }

        stage('Build and start container') {
            steps {
                echo '--------------------< Compilation du docker-compose >--------------------'
                sh 'docker compose build'

                echo '--------------------< Lancement du docker-compose (containers) >--------------------'
                sh 'docker compose up -d'

                echo '--------------------< Affichage des containers actifs >--------------------'
                sh 'docker ps'
            }
        }
    }
}
