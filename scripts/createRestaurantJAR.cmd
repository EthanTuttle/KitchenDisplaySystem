@ECHO off
cd ..
javac src\main\java\restaurantGUI\RestaurantGUI.java -d bin\Restaurant
cd bin
jar cfe restaurant.jar src.main.java.restaurantGUI.RestaurantGUI -C Restaurant . 