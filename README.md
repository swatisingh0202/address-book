#REQUIREMENT SPECIFICATION

# Gumtree coding challenge

## The task

Your task is to develop a small java application. We need you to build your application in your own GitHub repository.  Please do not fork our repository to create your project.  Once you are done, send us a link to your repository.

Please allow yourself at least 1 hour of uninterrupted time for this task, but feel free to spend as much time on the task as you like and make the solution and the code as perfect as you like.

## The application

Your application needs to read the attached AddressBook file and answer the following questions:

1. How many males are in the address book?
2. Who is the oldest person in the address book?
3. How many days older is Bill than Paul?

Some insights into what we'll be looking for (and what we will not):

- Feel free to use any dependency management and build tools eg maven or gradle
- Please do not use database, we are more interested in your Java skills than your SQL skills
- Feel free to commit as often as you'd like. The more commits the better! Please commit at least once within the first hour
- It's better to complete 1 task than to start 3
- Feel free to use any java libraries you feel appropriate
- We will be looking at how you approach the task (e.g. how you break it into sub-tasks) and how you structure your code to answer the questions


#SOLUTION

#How to run the application 

About the application 
1. The application can be executed from the main method of the class AddressBookApplication , which is springboot main method
2. Once the application is started the rest resources can be accessed with the resource link as mentioned below 
3. The application contains some unit and intergration test 


#To build the application
./gradlew clean build

#To run the test 
./gradlew clean test

#To start the application
./gradlew bootRun


#Read the complete list of person from address book 
http://localhost:8585/person/

#Answering the questions asked in the test
1. How many males are in the address book?
http://localhost:8585/person/personByGender

2. Who is the oldest person in the address book?
http://localhost:8585/person/oldest

3. How many days older is Bill than Paul?
http://localhost:8585/person/ageDifference




