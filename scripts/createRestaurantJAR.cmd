cd ..
javac src\main\java\restaurantGUI\RestaurantGUI.java -d bin\Restaurant
cd bin\Restaurant
jar cfm restaurant.jar MANIFEST.MF .