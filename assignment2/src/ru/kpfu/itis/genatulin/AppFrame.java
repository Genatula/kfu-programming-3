package ru.kpfu.itis.genatulin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame {
    private static final String title = "App";
    private final Container container;
    private final JLabel label;
    private final JPanel rightPanel;
    private final JPanel statusBar;

    public AppFrame() {
        container = this.getContentPane();
        label = new JLabel();
        rightPanel = new JPanel();
        statusBar = new JPanel();
    }

    private void initComponents() {
        createMenuBar();
        initLabel();
        initRightPanel();
        initStatusBar();
    }

    private void initStatusBar() {
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(255, 255, 255));
        statusBar.setPreferredSize(new Dimension(this.getWidth(), 20));
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));

        JLabel label = new JLabel("Status: ");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        statusBar.add(label);

        this.add(statusBar, BorderLayout.SOUTH);
    }

    private void initRightPanel() {
        rightPanel.setOpaque(true);
        rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setPreferredSize(new Dimension(300, 980));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        initRightPanelButtons();
        this.add(rightPanel, BorderLayout.EAST);
    }

    private void initRightPanelButtons() {
        JButton redButton = new JButton("Red");
        redButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        redButton.addActionListener(actionEvent -> changeLabelColor(this.label, new Color(255, 0, 0)));

        JButton cageButton = new JButton("Nicolas Cage");
        cageButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton rotateButton = new JButton("Rotate");
        rotateButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(redButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(cageButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(rotateButton);
    }

    public void showWindow() {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.pack();
        this.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(255, 255, 255));
        menuBar.setPreferredSize(new Dimension(1000, 20));

        JMenu menu = new JMenu("File");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem exitItem = new JMenuItem("Exit");

        JFrame thisFrame = this;
        exitItem.addActionListener(actionEvent -> thisFrame.dispose());

        menu.add(aboutItem);
        menu.add(exitItem);
        menuBar.add(menu);

        this.setJMenuBar(menuBar);
    }

    private void initLabel() {
        this.label.setOpaque(true);
        this.label.setMinimumSize(new Dimension(700, 980));
        this.label
        this.container.add(label, BorderLayout.WEST);
    }

    private void changeLabelColor(JLabel label, Color color) {
        label.setBackground(color);
    }
}
