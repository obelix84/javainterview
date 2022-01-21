package ru.gb.lesson1.third;

public abstract class Figure {

    protected int x1;
    protected int y1;

    private boolean show = true;

    public Figure(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void draw() {
        if(show) drawInt();
    }

    public abstract void drawInt();

    public abstract double area();

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
