package main.ru.spbstu.telematics.java;


import java.util.logging.Level;

import static main.ru.spbstu.telematics.java.MyLogger.LOGGER;

public class Car extends Thread
{


    private CarController carController;
    private final Direction direction;

    int number;
    public boolean isAsleep;
    public Car(Directions from, Directions to, CarController tl, int n)
    {
        direction = new Direction(from, to);
        carController = tl;
        number = n;
        isAsleep = true;
        LOGGER.log(Level.INFO,"Created car number " + n + " with controller " + tl);
    }

    public void SetTrafficLight(CarController controller)
    {
        carController = controller;
    }

    public void SetNumber(int n)
    {
        number = n;
        LOGGER.log(Level.INFO,"Number changed to " + number );
    }

    public void run()
    {
        boolean isFree = false;
        Direction[] curDir;

        while (!isFree)
        {
            if(!isAsleep)
            {
                curDir = carController.GetState();

                if(curDir == null)
                    continue;

               LOGGER.info("Car number " + number + " successfully got state");

                for (Direction value : curDir)
                    if ((value.getStart() == direction.getStart()) && (value.getFinish() == direction.getFinish())) {
                        isFree = true;
                        LOGGER.info("Car number " + number + " released");
                        break;
                    }

                if(!isFree)
                {
                    LOGGER.info("Car number " + number + " staying on crossroad");
                }

                isAsleep = true;
                carController.GiveCallback(number, isFree);
            }

            try {
                Thread.sleep(250);
            }
            catch (InterruptedException exc) {
                LOGGER.log(Level.WARNING,"",exc);
            }
        }
    }

}
