package ru.kpfu.itis.genatulin.genatulin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppFrame frame = new AppFrame();
        SwingUtilities.invokeLater(frame::start);
    }
}
