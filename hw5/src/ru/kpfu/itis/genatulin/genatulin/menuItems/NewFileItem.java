package ru.kpfu.itis.genatulin.genatulin.menuItems;

import ru.kpfu.itis.genatulin.genatulin.AppFrame;

import javax.swing.*;
import java.awt.*;

public class NewFileItem extends JMenuItem {

    private final AppFrame appFrame;

    private static final String name = "New File...";

    public NewFileItem(Component component, AppFrame appFrame) {
        super(name);
        this.appFrame = appFrame;
        addInitOnClickListener(this);
    }

    private void addInitOnClickListener(NewFileItem item) {

    }

}
