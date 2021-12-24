package ru.kpfu.itis.genatulin.genatulin.menuItems;

import javax.swing.*;

public class AboutItem extends JMenuItem {
    private static final String name = "About";

    private JFrame frame;

    public AboutItem(JFrame frame) {
        super(name);
        this.frame = frame;
        addListenerToShowDialog(this, frame);
    }

    private void addListenerToShowDialog(AboutItem item, JFrame frame) {
        item.addActionListener(actionListener -> JOptionPane.showMessageDialog(frame, "This application has been developed by Genatulin Ilnas", "About", JOptionPane.INFORMATION_MESSAGE));
    }
}
