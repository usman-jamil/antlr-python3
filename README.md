# antlr-python3

## Requirements
 - Download and install Java Development kit 8
 - Make sure you set JAVA_HOME as well as JRE_HOME
 - Download and unzip: apache-maven-3.6.1-bin.zip to: C:\Program Files\apache-maven-3.6.1
  - Set System Variable MAVEN_HOME to C:\Program Files\apache-maven-3.6.1
  - Make sure you add C:\Program Files\Java\jdk1.8.0_211\bin;C:\Program Files\apache-maven-3.6.1\bin the Path System variable
  - Download and install Visual Studio Code
  - Add the "Java Extension Pack" to the Visual Studio Code using the extension manager
  - Add the "Language Support for Java(TM) by Red Hat" extension also
  - Go to File -> Preferences -> Settings and search for the settings starting with "Maven"
  - Set Maven > Executable: Path to: C:\Program Files\apache-maven-3.6.1\bin
  - Uncheck Maven > Executable: Prefer Maven Wrapper

## Running
 - Open the Project main folder in Visual Studio Code
 - Open the Terminal
 - Type: mvn compile
 - Type: mvn package
 - Open a seperate cmd window and navigate to the target folder
 - execute command: java -jar python3.parser-1.0-jar-with-dependencies.jar
 - You should be able to see the output
