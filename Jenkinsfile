node {
	def jobName = env.JOB_NAME
	def buildNumber = env.BUILD_NUMBER
	def branchName = env.BRANCH_NAME
	echo "Job: " + jobName
	echo "Number: " + buildNumber
	echo "Branch: " + branchName

	checkout scm
	def pythonBuildPipe = load 'code/pipeline.groovy'
	if(pythonBuildPipe == null) {
		echo "Unable to load file 'pipeline.groovy'!"
	} else {
		pythonBuildPipe.buildPipeLine()
	}
}  

