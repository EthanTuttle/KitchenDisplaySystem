Developed with JDK-16
Windows 10 and MacOS 10.15

-------------------------------------------

        Ninja Fast Kitchen System

-------------------------------------------

## Description
Restaurant menu creation, order scheduling, and customer order placement GUIs.
Designed to streamline the restaurant ticketing and ordering System.

## Running
To run, nagivate to the bin folder and double click a jar file or run from the command line
    java -jar restaurant.jar 
or 
    java -jar customer.jar
correspondong to which application you would like to run

## Scripts
Navigate to the scripts folder. Windows users should run:
    .\script.cmd
Mac and Linux should run:
    ./script.sh
Where script is the name of the script you would like to run.
Available scripts are:
    createRestaurantJAR - compile and create the JAR file for the restaurant application
    createCustomerJAR - compile and create the JAR file for the customer application
    runJavadoc - create the javadoc for the project in the docs folder

## Connecting
Users running the restaurant GUI have their IP on the title screen. 
Customers trying to connect to that restaurant instance should put that IP address into their input form on startup
A machine cannot have multiple restaurant instances running, a second instance will not open due to the sockets

## Settings files
The restaurant application will create a settings file .menu in the directory the JAR file is run in.
Please do not edit, delete, or move this file or you will lose your settings
