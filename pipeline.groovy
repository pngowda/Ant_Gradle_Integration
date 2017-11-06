def buildPipeLine() {	
	timestamps {
		def pythonExecutable  =  "py -3"
		def workSpace         =  "${env.WORKSPACE}"
		def proxyServer       =  "http://con-ffm-asg-01.conti.de"
		def proxyPort         =  "8080"
		def fullPipeline      =  true								
   		def jobNamePath       =  "${env.JOB_NAME}".split( "/" )
		def jobName           =  jobNamePath[1]
		

		def isWindowsBaseLibsPipe      =  ~/Windows_Base_Python_Libs.*/
		def isLinuxBaseLibsPipe        =  ~/Linux_Base_Python_Libs.*/
		def isCleintConfigGuiPipe      =  ~/Client_ConfigGUI.*/
		def isCleintHWDesignToolPipe   =  ~/Client_HWDesignTool.*/
		def isCleintQltechHostPipe     =  ~/Client_QLTECHostService.*/
		def isCleintStdExecGuiPipe     =  ~/Client_StdExecGUI.*/
		def isEmbeddedDSPBasepipe      =  ~/Embedded_DSP_base.*/
		def isEmbeddedDSPRBpipe        =  ~/Embedded_DSP_RB.*/
		def isEmbeddedOSAutomationpipe =  ~/Embedded_OSbasedAutomation.*/
		def isProjectTemplatePipe      =  ~/Project_Template.*/
		def isServerConfigServicePipe  =  ~/Server_ConfigService.*/
		def isServerRBBuildPipe        =  ~/Server_RB_build.*/


		switch(jobName) {
		case isWindowsBaseLibsPipe :
			node('windows'){
			  	unitTest()
				integrationTest()
				prepareLinuxLibs()
			}
		break;
			
		case isLinuxBaseLibsPipe :
			node('linux'){
			  	unitTest()
				integrationTest()
			}
		break;
			
		case isCleintConfigGuiPipe :
			node('windows'){
			  	unitTest()
				integrationTest()
				prePareExe()
				testExe()
				preparePackage()
				testPackage()
			}
		break;
		
		case isCleintHWDesignToolPipe :
			node('windows'){
			  	unitTest()
				integrationTest()
				prePareExe()
				testExe()
				preparePackage()
				testPackage()
			}
		break;
			
		default:
		    echo "Running 'Default' pipeline..."
			break;
		}
	}
}

def notifyStarted() {
	//step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'prajwal.gowda-ext@continental-corporation.com', sendToIndividuals: true])
	emailext (
     subject: "STARTED: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
     body: """STARTED: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]: Status --> ${currentBuild.result}
              Check console output at ${env.BUILD_URL}""",
     recipientProviders: [[$class: 'DevelopersRecipientProvider']]
   )
}

def checkoutSourceBaseLibs() {
	checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', 
	excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, 
	includedRegions: '', locations: [[credentialsId: '5ddf9e38-5993-41d5-97bf-2076d9b85261', depthOption: 'infinity', 
	ignoreExternalsOption: true, local: 'BaseLibs', remote: 'http://frvs542a.cw01.contiwan.com/svn/XI002_QLTec_SW/Base_Python_Libs/branches/work']], workspaceUpdater: [$class: 'UpdateUpdater']])
}

def checkoutSourceConfigGui() {
	checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', 
	excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, 
	includedRegions: '', locations: [[credentialsId: '5ddf9e38-5993-41d5-97bf-2076d9b85261', depthOption: 'infinity', 
	ignoreExternalsOption: true, local: 'ConfigGui', remote: 'http://frvs542a.cw01.contiwan.com/svn/XI002_QLTec_SW/Client_ConfigGUI/branches/work']], workspaceUpdater: [$class: 'UpdateUpdater']])
}

def prepareLinuxLibs(){
	stage ('Prapare-Linux-Libs'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}

def unitTest(){
	stage ('Unit-Test'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}

def integrationTest(){
	stage ('Integration-Test'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}

def prePareExe(){
	stage ('Build-Exe'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}


def testExe(){
	stage ('Exe-Test'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}

def preparePackage(){
	stage ('Prepare-Package'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}

def testPackage(){
	stage ('Test-Package'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}

def distribuitePackage(){
	stage ('Distribuite-Package'){
		try {
			currentBuild.result = 'SUCCESS'
		}catch(Exception codeException) {
			echo "### There were Unit Test stage failures:\n" 
			currentBuild.result = 'FAILURE'
			throw codeException
	    }
		finally {
			//Send an email notification for build failure
			if(("${currentBuild.result}")=='FAILURE'){
				notifyStarted()
			}
		}					
	}
}
return this;


