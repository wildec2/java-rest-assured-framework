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
 	    step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
//             allure([
//                 includeProperties: false,
//                 jdk              : '',
//                 properties       : [],
//                 reportBuildPolicy: 'ALWAYS',
//                 results          : [[path: 'build/allure-results']]
//             ])
 	    }
 	}
}