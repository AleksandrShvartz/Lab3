package main.ru.spbstu.telematics.java;


import java.util.ArrayList;
import java.util.logging.Level;

import static main.ru.spbstu.telematics.java.MyLogger.LOGGER;

public class CarController extends Thread
{
    private final ArrayList<Direction[]> directions;
    private final ArrayList<Car> cars;
    private int state;
    private boolean[]  callbacks;
    private boolean[] callbacksGiven;
    public boolean turnedOn;
    public CarController()
    {
        LOGGER.info("Create CarController");
        turnedOn = false;

        directions = new ArrayList<Direction[]>();
         // setting acceptable directions
        directions.add(new Direction[] {new Direction(Directions.EAST, Directions.SOUTH),
                new Direction(Directions.EAST, Directions.WEST)});
        directions.add(new Direction[] {new Direction(Directions.NORTH, Directions.SOUTH),
                new Direction(Directions.SOUTH, Directions.NORTH)});
        directions.add(new Direction[] {new Direction(Directions.WEST, Directions.EAST)});

        cars = new ArrayList<Car>();

        state = 0;
    }

    public Direction[] GetState()
    {
        return directions.get(state);
    }

    public void createCar(char from, char to, int number)
    {
        cars.add(new Car(Directions.charToDirection(from) ,Directions.charToDirection(to), this, number));
    }

    public void runCars()
    {
        for(Car car : cars)
            car.start();
    }



    private void initCallbacks()
    {
        callbacks = new boolean[cars.size()];
        callbacksGiven = new boolean[cars.size()];

       LOGGER.info("Creating callbacks with size " + cars.size());

        for(int i = 0; i < callbacks.length; i++)
        {
            callbacks[i] = false;
            callbacksGiven[i] = false;
        }
    }

    public synchronized void GiveCallback(int i, boolean state)
    {
        if( (callbacks == null) || (i >= callbacks.length) || (i >= callbacksGiven.length) )
        {
            LOGGER.info("To big i " + i);
            return;
        }

        if(!callbacksGiven[i])
            LOGGER.info("Callback " + i + " received");

        callbacks[i] = state;
        callbacksGiven[i] = true;

        LOGGER.info("Callback received " + i);
    }

    private boolean callbacksWasGiven()
    {
        for (boolean b : callbacksGiven) {

            if (!b)
                return false;
        }

        return true;
    }



    public void turnOffCars()
    {
        for(Car car : cars)
            car.isAsleep = true;

        LOGGER.info("All cars are turned off");
    }

    public  void turnCarsOn()
    {
        for(Car car : cars)
            car.isAsleep = false;

        LOGGER.info("Cars awaken");
    }

    public void run()
    {
        int step;

        for(state = 0; state < 3; state++)
        {
            turnOffCars();
            initCallbacks();
            turnCarsOn();

            while(!callbacksWasGiven())
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException exc) {
                    LOGGER.log(Level.WARNING,"Tread stopped",exc);
                }

            turnOffCars();


            step = 0;

            for(int i = 0; i < cars.size(); i++)
                if(callbacks[i + step])
                {
                    cars.remove(i);

                    for(int j = i; j < cars.size(); j++) {
                        LOGGER.info("Car number was updated from " + (j + 1) + " to " + j);
                        cars.get(j).SetNumber(j);
                    }

                    i--;
                    step++;
                }

            LOGGER.info("State " + state + " ended");
        }


    }
}
