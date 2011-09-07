Start a new ModelibraWicket project

+ export the ModelibraWicketSkeleton project without 
	the css, template, classes, lib and logs directories 
	to a new location outside the Modelibra workspace: 
	File/Export.../General/File System 
	(use the Create directory structure for files option)
+ rename the project directory to reflect your own project
+ rename the Eclipse project to your own project using an ordinary text editor
+ import the project into Eclipse
+ select the project and use the pop-up menu: Team/Share Project...
	(use the SVN repository; share the project under the trunk directory)
+ do not share the empty classes directory
+ define by Team/Set Property... the svn:ignore property at the WEB-INF directory for 
	the classes and logs directories
+ define the svn:externals property at the WEB-INF directory for the lib directory:
	lib http://svn.javaforge.com/svn/Modelibra/trunk/ModelibraWicketSkeleton/WEB-INF/lib
+ commit by Team/Commit... local changes to the repository
+ update by Team/Update changes from the repository to the local version
+ define the svn:externals property at the project directory for 
	the css and template directories:
	css http://svn.javaforge.com/svn/Modelibra/trunk/ModelibraWicketCss/css
	template http://svn.javaforge.com/svn/Modelibra/trunk/ModelibraWicketSkeleton/template	
+ commit by Team/Commit... local changes to the repository
+ update by Team/Update changes from the repository to the local version
+ design a domain model in ModelibraModeler and save it in the mm directory
+ generate the reusable-model-config.xml file 
	(use the minimal configuration) 
	from ModelibraModeler into the WEB-INF/config directory
+ refresh the project to examine the reusable-model-config.xml file
	(improve the format if necessary)
+ check the dm.gen.dmConfig.properties file for code, data and web directories
	(accept the the default values)
+ in the dm.gen.DmGenerator class change the DOMAIN_CODE constant to your domain code
	(if not sure, check the reusable-model-config.xml file)
+ run the main method of the DmGenerator class as Java application
+ refresh the project to see the generated code, data and web xml configuration
+ in the generated Java classes
	use Ctrl-Shift O F S to clean imports, format the code and save it
+ if a concept was referenced as if it was an entry point,
	add the specific get method to the specific model class
+ commit by Team/Commit... local changes to the repository
	(Select all unversioned files)
+ update by Team/Update changes from the repository to the local version
+ run the main method of the Start class to start the Jetty server
+ check the WEB-INF/logs/info.html for info and error messages
+ use the Modelibra Wicket application in your browser: http://localhost:8081/
+ use the doc/spiral.txt to add your project notes



