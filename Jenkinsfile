node() {
	def pythonUnitTest_1        =  ''
	def pythonFunctionalTest_1  =  ''
	def pythonFunctionalTest_2  =  ''
	def pythonFunctionalTest_3  =  ''
	def pythonMainBuild         =  ''
	def pythonDeploy            =  ''
									
	//setupEnvironment()
	//printParams()
	
	withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'XXXXXX',
            usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']])
	{
		stage ('Source Code Checkout'){ 
			checkout scm
		}
		
		stage ('Execute Unit-Test'){
			try {
				echo "Building on branch: ${branchName}"
				echo "Building Job : ${jobName}"
				
			    if(isUnix()) {
					sh "${pythonUnitTest_1} <arguments>"
				}
				else{
					bat "${pythonUnitTest_1} <arguments>"
				}
			}catch(err) {
				echo "### There were Unit test failures:\n${err}"
			}
		}
		stage ('Execute Main-Build'){
			try {
				echo "Building on branch: ${branchName}"
				echo "Building Job : ${jobName}"
				
			    if(isUnix()) {
					sh "${pythonMainBuild} <arguments>"
				}
				else{
					bat "${pythonMainBuild} <arguments>"
				}
			}catch(err) {
				echo "### There were Main Build execution failures:\n${err}"
			}
			
		}
		
		stage ('Execute Functional-Tests'){
			try {
				echo "Building on branch: ${branchName}"
				echo "Building Job : ${jobName}"
				
			    if(isUnix()) {
					sh "${pythonFunctionalTest_1} <arguments>"
					sh "${pythonFunctionalTest_1} <arguments>"
					sh "${pythonFunctionalTest_1} <arguments>"
				}
				else{
					bat "${pythonFunctionalTest_1} <arguments>"
					bat "${pythonFunctionalTest_1} <arguments>"
					bat "${pythonFunctionalTest_1} <arguments>"
				}
			}catch(err) {
				echo "### There were Functional test failures:\n${err}"
			}
		}

		stage ('Execute Deployment'){
			try {
				echo "Building on branch: ${branchName}"
				echo "Building Job : ${jobName}"
				
			    if(isUnix()) {
					sh "${pythonDeploy} <arguments>"
				}
				else{
					bat "${pythonDeploy} <arguments>"
				}
			}catch(err) {
				echo "### There were Deployment failures:\n${err}"
			}
		}
	}
}

/**
	comment section
*/
def setupEnvironment() {
	def jobName      =  "${env.JOB_NAME}"
	def branchName   =  "${env.BRANCH_NAME}"
	script {
        env.CI_jdk_home = "${javaHome}"
		if(jobName){
			env.isCIEnabled= "true"
		}else{
			env.isCIEnabled= ""
		}
    }
}

/**
	comment section
*/
def printParams() {
    println "XXXXXX"
}
