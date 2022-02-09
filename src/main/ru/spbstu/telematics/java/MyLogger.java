package main.ru.spbstu.telematics.java;

import com.sun.tools.javac.Main;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MyLogger {
   public static Logger LOGGER;
    static {
        try(FileInputStream ins = new FileInputStream("src/main/ru/spbstu/telematics/configs/log.config")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(Main.class.getName());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }
}
