@ECHO off
cd ..
javac src\main\java\customerGUI\CustomerGUI.java -d bin\Customer
cd bin
jar cfe customer.jar src.main.java.customerGUI.CustomerGUI -C Customer . 