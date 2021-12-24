package ru.kpfu.itis.genatulin.genatulin.menuItems;

import javax.swing.*;

public class ExitItem extends JMenuItem {

    private JFrame frame;

    private static final String name = "Exit";

    public ExitItem(JFrame frame) {
        super(name);
        this.frame = frame;
        addExitActionListener(this);
    }

    private void addExitActionListener(ExitItem item) {
        item.addActionListener(actionEvent -> frame.dispose());
    }
}
