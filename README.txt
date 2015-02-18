Guide d’utilisateur

How to use this project in Eclipse:

1. Import this folder in eclipse : For Eclipse Luna Service Release 1 (4.4.1), choose File > Import > choose “Existing Projects into Workspace” > choose “Select root directory” and click “Browse” > choose the project directory

2. Assure you have the JavaFx, JUnit, JRE installed and imported. If the eclipse said it can’t find JavaFx library, please update your JDK to at least JDK 7u6. You can find the download link in Oracle’s website : http://www.oracle.com/technetwork/java/javase/downloads/index.html

3. Run the Main.java in oxz.application package

4. If you want to pass the tests, right click the oxz.application.test package, then choose “Run As” -> “JUnit test”.

How to use this application:

1. This application lets you execute commands on a file of folder, the default file is the folder of this project.

2. The left side of the application allows you to choose a file. Click the folder image to open/close a folder. Click its name button to set it as the target of commands.

3. At the left bottom of the view, you can select a folder of a file as the root path in the above left view by using “Select a folder” or “Select a file” button. Please notice that it is also set as the new default file of commands.

4. Once the target file is selected, the executable commands can be executed at the right side of this application. Click the command and the result will be shown in the right side of the command button

5. At the right bottom of the windows, the “Clear” button let you clear the results.

6. A checkbox “AutoRun” in the right bottom of the windows. Once it is selected, all executable commands will execute automatically on a new selected elements.

7. The commands can be loaded dynamically. You just have to add or remove their ".class" files in '\bin\oxz\application\command\imp' and the commands' list will be automatically updated (even while the application is running).


if you have any problems, feel free to contact :

olivier.pinon@polymtl.ca
yan.xu@polymtl.ca