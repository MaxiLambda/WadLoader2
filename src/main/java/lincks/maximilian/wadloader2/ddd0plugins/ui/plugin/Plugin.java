package lincks.maximilian.wadloader2.ddd0plugins.ui.plugin;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.WadLoader2Tab;

import javax.swing.*;


public interface Plugin<T extends JPanel & WadLoader2Tab> {

    String getTitle();
    String getToolTip();
    default Icon getIcon() {
        return null;
    }
    T getWadLoader2Tab();
}
