package ru.kpfu.itis.genatulin.genatulin;

import ru.kpfu.itis.genatulin.genatulin.buttons.FacePart;
import ru.kpfu.itis.genatulin.genatulin.buttons.FacePartSwitchButton;
import ru.kpfu.itis.genatulin.genatulin.menuItems.AboutItem;
import ru.kpfu.itis.genatulin.genatulin.menuItems.ExitItem;
import ru.kpfu.itis.genatulin.genatulin.menuItems.NewFileItem;
import ru.kpfu.itis.genatulin.genatulin.panels.FacePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class AppFrame extends JFrame {

    private boolean initialized;

    private final Container container;
    private final JMenuBar menuBar;
    private final FacePanel facePanel;
    private final JPanel rightPanel;

    private static final String title = "JPhotoRobot";
    private static final FacePart[] faceParts = FacePart.values();

    public AppFrame() {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        menuBar = new JMenuBar();
        facePanel = new FacePanel();
        rightPanel = new JPanel();
        initialized = false;
    }

    public void createGUI() {
        createMenuBar();
        createFacePanel();
        createRightPanel();
    }

    private void createRightPanel() {
        rightPanel.setOpaque(true);
        rightPanel.setBackground(new Color(243, 243, 243));
        rightPanel.setPreferredSize(new Dimension(400, 500));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(194, 194, 194)));
        createFacePartButtons(rightPanel);

        container.add(rightPanel, BorderLayout.EAST);
    }

    private void createFacePartButtons(Container container) {
        for (FacePart facePart : faceParts) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

            JLabel label = new JLabel("Change " + facePart.toString().toLowerCase(Locale.ROOT) + ":");
            FacePartSwitchButton backButton = new FacePartSwitchButton(-1, facePart, "Back");
            FacePartSwitchButton forwardButton = new FacePartSwitchButton(1, facePart, "Next");
            label.setBackground(new Color(243, 243, 243));

            JPanel labelPanel = new JPanel();
            labelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            labelPanel.setPreferredSize(new Dimension(20, 3));
            labelPanel.setBackground(new Color(243, 243, 243));

            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            buttonsPanel.setMinimumSize(new Dimension(15, 3));
            buttonsPanel.setBackground(new Color(243, 243, 243));

            labelPanel.add(label);
            buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonsPanel.add(backButton);
            buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonsPanel.add(forwardButton);

            panel.add(labelPanel);
            panel.add(buttonsPanel);

            container.add(Box.createRigidArea(new Dimension(0, 20)));
            container.add(panel);
        }
    }

    private void createFacePanel() {
        facePanel.setOpaque(true);
        facePanel.setBackground(new Color(243, 243, 243));
        facePanel.setMinimumSize(new Dimension(350, 500));
        container.add(facePanel, BorderLayout.WEST);
    }

    private void createMenuBar() {
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(255, 255, 255));

        JMenu fileMenu = new JMenu("File");
        NewFileItem newPhotoItem = new NewFileItem(facePanel, this);
        ExitItem exitItem = new ExitItem(this);
        fileMenu.add(newPhotoItem);
        fileMenu.add(exitItem);

        JMenu aboutMenu = new JMenu("About");
        AboutItem aboutItem = new AboutItem(this);
        aboutMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        menuBar.setPreferredSize(new Dimension(1000, 20));
        this.setJMenuBar(menuBar);
    }

    public void start() {
        createGUI();
        this.pack();
        this.setVisible(true);
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}
