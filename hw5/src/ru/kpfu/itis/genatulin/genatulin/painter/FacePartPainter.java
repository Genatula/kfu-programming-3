package ru.kpfu.itis.genatulin.genatulin.painter;

import ru.kpfu.itis.genatulin.genatulin.buttons.FacePart;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class FacePartPainter extends SwingImagePainter {
    private int counter;
    private ImageIcon imageIcon;
    private Component component;
    private final FacePart facePart;
    private final Graphics graphics;

    public FacePartPainter(Component component, FacePart facePart) {
        this.component = component;
        this.facePart = facePart;
        graphics = component.getGraphics();
        counter = 1;
        path = "/home/ilnas/Nextcloud/Studying/Programming/kfu-programming-3/hw5/resources/" + facePart.toString().toLowerCase(Locale.ROOT) + "/" + counter;
        leftTopCorner = facePart.getPoint();
    }

    public void init() {
        imageIcon = new ImageIcon(path);
        imageIcon.paintIcon(component, graphics, leftTopCorner.x, leftTopCorner.y);
    }

    public void paint(int number) {
        if (counter + number > facePart.getNumber()) {
            setCounter(1);
        }
        else if (counter + number < 1) {
            setCounter(facePart.getNumber());
        }
        else {
            setCounter(counter + number);
        }
        path = "/home/ilnas/Nextcloud/Studying/Programming/kfu-programming-3/hw5/resources/" + facePart.toString().toLowerCase(Locale.ROOT) + "/" + counter;
        paint();
    }

    @Override
    public void paint() {
        ImageIcon newImageIcon = new ImageIcon(path);
        imageIcon.setImage(newImageIcon.getImage());
        imageIcon.paintIcon(component, graphics, leftTopCorner.x, leftTopCorner.y);
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
