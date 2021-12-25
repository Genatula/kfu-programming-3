package ru.kpfu.itis.genatulin.genatulin.buttons;

import java.awt.*;

public enum FacePart {
    EYES (new Point(183, 155), 6),
    NOSE(new Point(280, 200), 9),
    LIPS(new Point(245, 330), 7),
    HEAD(new Point(160, 30), 7);

    private final Point point;
    private final int number;

    FacePart(Point point, int number) {
        this.point = point;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public Point getPoint() {
        return point;
    }
}
