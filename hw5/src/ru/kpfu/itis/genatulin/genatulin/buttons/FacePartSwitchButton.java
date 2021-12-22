package ru.kpfu.itis.genatulin.genatulin.buttons;

import javax.swing.*;

public class FacePartSwitchButton extends JButton {
    private final int direction;
    private final FacePart facePart;

    public FacePartSwitchButton(int direction, FacePart facePart, String name) {
        super(name);
        this.direction = direction;
        this.facePart = facePart;
    }
}
