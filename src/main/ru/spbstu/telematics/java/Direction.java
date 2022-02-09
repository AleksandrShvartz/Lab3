package main.ru.spbstu.telematics.java;

public class Direction
{
    Directions start;
    Directions finish;

    public Direction(Directions start, Directions finish) {
        this.start=start;
        this.finish=finish;
    }

    public Directions getStart() {
        return start;
    }

    public void setStart(Directions start) {
        this.start = start;
    }

    public Directions getFinish() {
        return finish;
    }

    public void setFinish(Directions finish) {
        this.finish = finish;
    }
}
