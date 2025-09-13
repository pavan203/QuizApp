pipeline {
    agent none  // No global agent, define per stage

    stages {

        stage('Build') {
            agent { label 'built-in' }  // Master node (Windows)
            tools { maven 'MAVEN' }
            steps {
                // Clean build, skip tests
                bat 'mvn clean install -DskipTests'

                // Stash the JAR for slave
                stash includes: 'target/QuizApp-0.0.1-SNAPSHOT.jar', name: 'app-jar', useDefaultExcludes: false
            }
        }

        stage('Run on Slave') {
            agent { label 'docker' }  // Slave node label
            steps {
                // Clean workspace on slave before unstashing
                sh 'rm -rf *'

                // Retrieve the JAR
                unstash 'app-jar'
                sh 'rm -f HELP.md QuizApp.iml mvnw mvnw.cmd Jenkinsfile src/*'
                // Run the JAR
                 sh 'java -jar QuizApp-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
