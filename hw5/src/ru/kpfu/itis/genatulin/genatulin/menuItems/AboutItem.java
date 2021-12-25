package ru.kpfu.itis.genatulin.genatulin.menuItems;

import javax.swing.*;

public class AboutItem extends JMenuItem {
    private static final String name = "About";

    public AboutItem(JFrame frame) {
        super(name);
        addListenerToShowDialog(this, frame);
    }

    private void addListenerToShowDialog(AboutItem item, JFrame frame) {
        item.addActionListener(actionListener -> JOptionPane.showMessageDialog(frame, "This application has been developed by Genatulin Ilnas", "About", JOptionPane.INFORMATION_MESSAGE));
    }
}
