package ru.gb.lesson1.third;

public class Circle extends Figure{

    private int radius;

    public Circle(int x1, int y1, int radius) {
        super(x1, y1);
        this.radius = radius;
    }

    @Override
    public void drawInt() {
        System.out.printf("Circle at point (%d, %d) with radius %d", x1, y1, radius);
    }

    @Override
    public double area() {
        return Math.PI * (radius^2);
    }
}
