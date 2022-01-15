pipeline{

    agent any

    options{
        buildDiscarder(logRotator(numToKeepStr: '8'))
    }

    parameters {
        string(defaultValue: "https://automationintesting.online/", description: 'environment to run tests', name: 'baseUrl')
    }

    stages{
        stage ('Clone project'){
            steps{
                script{
                    currentBuild.displayName = "#${BUILD_NUMBER} [${GIT_BRANCH}]"
                }
                cleanWs()
                checkout scm
            }
        }
        stage ('Run Tests'){
            steps{
                sh "./gradlew test -DbaseUrl=${baseUrl}"
            }
        }
 	}

 	post{
 	    always{
            allure([
                includeProperties: false,
                properties       : [],
                reportBuildPolicy: 'ALWAYS',
                results          : [[path: 'build/allure-results']]
            ])
 	    }
 	}
}