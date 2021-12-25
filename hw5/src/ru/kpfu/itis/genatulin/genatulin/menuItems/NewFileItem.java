package ru.kpfu.itis.genatulin.genatulin.menuItems;

import ru.kpfu.itis.genatulin.genatulin.panels.FacePanel;

import javax.swing.*;

public class NewFileItem extends JMenuItem {

    private final FacePanel panel;

    private static final String name = "New File...";

    public NewFileItem(FacePanel panel) {
        super(name);
        this.panel = panel;
        addResetImageToDefaultOnClickListener(this);
    }

    private void addResetImageToDefaultOnClickListener(NewFileItem item) {
        item.addActionListener(actionEvent -> panel.resetImage());
    }

}
