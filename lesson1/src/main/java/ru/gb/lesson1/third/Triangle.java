package ru.gb.lesson1.third;

public class Triangle extends Figure{

    private int x2, y2;
    private int x3, y3;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        super(x1, y1);
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void drawInt() {
        System.out.printf("Triangle [(%d, %d), (%d, %d), (%d, %d)]", x1, y1,
                x2, y2, x3, y3);
    }

    @Override
    public double area() {
        return ((x2-x1)*(y3-y1) - (x3-x1)*(y2-y1)) / 2;
    }
}
