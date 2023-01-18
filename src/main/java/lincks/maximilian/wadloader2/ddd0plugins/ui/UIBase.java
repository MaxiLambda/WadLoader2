package lincks.maximilian.wadloader2.ddd0plugins.ui;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.RuleConfigTab;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.SettingConfigTab;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.StartWadsTab;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.WadPackConfigTab;
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

    public void initUI() {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pane.addTab(START_GAME_TAB, null, startTab, START_GAME_TAB_TIP);
        pane.addTab(WAD_PACKS_TAB, null, wadPackTab, WAD_PACKS_TAB_TIP);
        pane.addTab(RULE_TAB, null, ruleTab, RULE_TAB_TIPS);
        pane.addTab(SETTINGS_TAB, null, settingsTab, SETTINGS_TAB_TIP);

        add(pane);

        //setSize(600,600)
        setVisible(true);
    }
}
