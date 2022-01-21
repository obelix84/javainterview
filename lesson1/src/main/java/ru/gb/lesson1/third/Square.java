package ru.gb.lesson1.third;

public class Square extends Figure {

    private final int height;

    public Square(int x1, int y1, int height) {
        super(x1, y1);
        this.height = height;
    }

    @Override
    public void drawInt() {
        System.out.printf("Square [(%d, %d), (%d, %d)]", x1, y1,
                x1 + height, y1 + height);
    }

    @Override
    public double area() {
        return height^2;
    }
}
