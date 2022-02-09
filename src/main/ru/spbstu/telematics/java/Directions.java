package main.ru.spbstu.telematics.java;

public enum Directions{
    NORTH,SOUTH,EAST,WEST,ERROR;
    public static Directions charToDirection(char c){
        return switch (c) {
            case 'n' -> NORTH;
            case 's' -> SOUTH;
            case 'w' -> WEST;
            case 'e' -> EAST;
            default -> ERROR;
        };
    }
    }