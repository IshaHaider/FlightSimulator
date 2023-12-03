# FlightSimulator
# Group Members: Abdelrahman Bekhit(30150636), Isha Haider (30140419), Eric Mei (30142893), Akshpreet Singh(30129354)

in main folder, run the following commands to run DB controller main:
javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Controllers/MainController.java
java -cp .:lib/mysql-connector-j-8.2.0.jar src.Controllers.MainController 

in main controller, run the following commands to run the GUI:
javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Controllers/*.java 
javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Presentation/*.java
javac -cp .:lib/mysql-connector-j-8.2.0.jar src/Domain/*.java 


javac -d bin -cp lib/mysql-connector-j-8.2.0.jar src/Controllers/.java src/Doman/.java src/Presentation/*.java
jar cvfm FlightWebApp.jar Manifest.txt -C bin/ . 

COMMAND TO RUN PROGRAM: java -jar FlightWebApp.jar