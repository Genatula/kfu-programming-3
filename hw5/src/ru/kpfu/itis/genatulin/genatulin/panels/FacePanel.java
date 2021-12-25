package ru.kpfu.itis.genatulin.genatulin.panels;

import ru.kpfu.itis.genatulin.genatulin.buttons.FacePart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FacePanel extends JPanel {
    private Map<FacePart, Integer> counters;
    private String path;

    private static final FacePart[] faceParts = FacePart.values();
    private static final String samplePath = "hw5/resources/";

    public FacePanel() {
        setPreferredSize(new Dimension(650, 500));
        counters = new HashMap<>();
        for (FacePart facePart: faceParts) {
            counters.put(facePart, 1);
        }
    }

    public void updateImage(FacePart facePart, int direction) {
        if (counters.get(facePart) + direction > facePart.getNumber()) {
            counters.replace(facePart, 1);
        }
        else if (counters.get(facePart) + direction < 1) {
            counters.replace(facePart, facePart.getNumber());
        }
        else {
            counters.replace(facePart, counters.get(facePart) + direction);
        }
        revalidate();
        repaint();
    }

    public void resetImage() {
        for (FacePart facePart: faceParts) {
            counters.replace(facePart, 1);
        }
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (FacePart facePart: faceParts) {
            path = facePart.toString().toLowerCase(Locale.ROOT) + "/" + counters.get(facePart).toString() + ".png";
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(samplePath + path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                graphics.drawImage(image, facePart.getX(), facePart.getY(), null);
            }
        }
    }
}
