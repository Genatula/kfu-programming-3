package ru.kpfu.itis.genatulin.genatulin.buttons;

import ru.kpfu.itis.genatulin.genatulin.panels.FacePanel;

import javax.swing.*;

public class FacePartSwitchButton extends JButton {
    private final int direction;
    private final FacePart facePart;
    private final FacePanel panel;

    public FacePartSwitchButton(int direction, FacePart facePart, String name, FacePanel panel) {
        super(name);
        this.direction = direction;
        this.facePart = facePart;
        this.panel = panel;
        addChangeImageOnClickListener(this);
    }

    private void addChangeImageOnClickListener(FacePartSwitchButton button) {
        button.addActionListener(actionEvent -> panel.updateImage(facePart, getDirection()));
    }

    public int getDirection() {
        return direction;
    }
}
