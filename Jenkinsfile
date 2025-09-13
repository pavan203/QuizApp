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
                stash includes: 'target/my-app-1.0-SNAPSHOT.jar', name: 'app-jar', useDefaultExcludes: false
            }
        }

        stage('Run on Slave') {
            agent { label 'docker' }  // Slave node label
            steps {
                // Clean workspace on slave before unstashing
                sh 'rm -rf *'

                // Retrieve the JAR
                unstash 'app-jar'

                // Run the JAR
                sh 'java -jar target/my-app-1.0-SNAPSHOT.jar'
            }
        }
    }
}
