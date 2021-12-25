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
    private static final String samplePath = "/home/ilnas/Nextcloud/Studying/Programming/kfu-programming-3/hw5/resources/";

    public FacePanel() {
        setPreferredSize(new Dimension(650, 500));
        counters = new HashMap<>();
        for (FacePart facePart: faceParts) {
            counters.put(facePart, 1);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
//        FacePart facePart = faceParts[0];
//        path = facePart.toString().toLowerCase(Locale.ROOT) + "/" + counters.get(facePart).toString() + ".png";
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new File(samplePath + path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            graphics.drawImage(image, facePart.getX(), facePart.getY(), null);
//        }
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
