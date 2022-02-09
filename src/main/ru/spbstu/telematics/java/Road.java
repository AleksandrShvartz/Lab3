package main.ru.spbstu.telematics.java;

import java.util.ArrayList;

import static main.ru.spbstu.telematics.java.MyLogger.LOGGER;

public class Road
{
    private final ArrayList<Car> cars;

    private final CarController controller;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public CarController getController() {
        return controller;
    }

    public Road(){
    cars = new ArrayList<Car>();
    LOGGER.info("Created cars");
    controller = new CarController();
}

    public static void main( String[] args )
    {
    }
}
