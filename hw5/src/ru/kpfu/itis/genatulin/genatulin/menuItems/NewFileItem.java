package ru.kpfu.itis.genatulin.genatulin.menuItems;

import ru.kpfu.itis.genatulin.genatulin.AppFrame;
import ru.kpfu.itis.genatulin.genatulin.painters.*;

import javax.swing.*;
import java.awt.*;

public class NewFileItem extends JMenuItem {

    private final EyesPainter eyesPainter;
    private final NosePainter nosePainter;
    private final HeadPainter headPainter;
    private final LipsPainter lipsPainter;
    private final AppFrame appFrame;

    private static final String name = "New File...";

    public NewFileItem(Component component, AppFrame appFrame) {
        super(name);
        eyesPainter = new EyesPainter(component);
        nosePainter = new NosePainter(component);
        headPainter = new HeadPainter(component);
        lipsPainter = new LipsPainter(component);
        this.appFrame = appFrame;
        addInitOnClickListener(this);
    }

    private void addInitOnClickListener(NewFileItem item) {
        item.addActionListener(actionEvent -> this.initPainters());
    }

    private void initPainters() {
        appFrame.setInitialized(true);
        eyesPainter.init();
        headPainter.init();
        lipsPainter.init();
        nosePainter.init();
    }
}
