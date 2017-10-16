def buildOnWindows() {
	timestamps {
		node('') {
			def pythonUnitTest_1        =  ''
			def pythonFunctionalTest_1  =  ''
			def pythonFunctionalTest_2  =  ''
			def pythonFunctionalTest_3  =  ''
			def pythonMainBuild         =  ''
			def pythonDeploy            =  ''
											
			setupEnvironment()
			//printParams()
			
			withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'test_id',
		            usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']])
			{
				stage ('Source Code Checkout'){ 
					//checkoutSource()
				}
				stage ('Execute Unit-Test'){
					try {
						echo "Building on branch: ${branchName}"
						echo "Building Job : ${jobName}"
							bat "${pythonUnitTest_1} <arguments>"
					}catch(err) {
						echo "### There were Unit test failures:\n${err}"
					}
				}
				stage ('Execute Main-Build'){
					try {
						echo "Building on branch: ${branchName}"
						echo "Building Job : ${jobName}"
							bat "${pythonMainBuild} <arguments>"
					}catch(err) {
						echo "### There were Main Build execution failures:\n${err}"
					}
				}
				
				stage ('Execute Functional-Tests'){
					try {
						echo "Building on branch: ${branchName}"
						echo "Building Job : ${jobName}"
							bat "${pythonFunctionalTest_1} <arguments>"
							bat "${pythonFunctionalTest_1} <arguments>"
							bat "${pythonFunctionalTest_1} <arguments>"
					}catch(err) {
						echo "### There were Functional test failures:\n${err}"
					}
				}

				stage ('Execute Deployment'){
					try {
						echo "Building on branch: ${branchName}"
						echo "Building Job : ${jobName}"
							bat "${pythonDeploy} <arguments>"
					}catch(err) {
						echo "### There were Deployment failures:\n${err}"
					}
				}
			}
		}
	}
}

def setupEnvironment() {
	def jobName      =  "${env.JOB_NAME}"
	def branchName   =  "${env.BRANCH_NAME}"
}

def printParams() {
    println "XXXXXX"
}

@NonCPS
def checkoutSource() {
	checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', 
	excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, 
	includedRegions: '', locations: [[credentialsId: '5ddf9e38-5993-41d5-97bf-2076d9b85261', depthOption: 'infinity', 
	ignoreExternalsOption: true, local: '.', remote: 'http://frvs542a.cw01.contiwan.com/svn/XI002_QLTec_SW/Base_Python_Libs/branches/work']], workspaceUpdater: [$class: 'UpdateUpdater']])
}

return this;

