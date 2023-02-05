package lincks.maximilian.wadloader2.ddd0plugins.ui;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

@Component
@RequiredArgsConstructor
public class UIBase extends JFrame {

    private final JTabbedPane pane = new JTabbedPane();
    private final StartWadsTab startTab;
    private final WadPackConfigTab wadPackTab;
    private final RuleConfigTab ruleTab;
    private final SettingConfigTab settingsTab;

    private final TagTab tagTab;

    public void initUI() {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pane.addTab(START_GAME_TAB, null, startTab, START_GAME_TAB_TIP);
        pane.addTab(WAD_PACKS_TAB, null, wadPackTab, WAD_PACKS_TAB_TIP);
        pane.addTab(TAG_TAB,null,tagTab,TAG_TAB_TIP);
        pane.addTab(RULE_TAB, null, ruleTab, RULE_TAB_TIPS);
        pane.addTab(SETTINGS_TAB, null, settingsTab, SETTINGS_TAB_TIP);

        pane.addChangeListener(e -> {
            WadLoader2Tab tab = (WadLoader2Tab) pane.getSelectedComponent();
            tab.updateData();
        });

        add(pane);
        startTab.updateData();
        setSize(600,600);
        //put the panel in the center of the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
