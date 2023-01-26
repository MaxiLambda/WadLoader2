package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.CREATE_NEW_RULE;

public class NewRuleDialog extends JDialog {

    private final CompletableFuture<WadPackRule> ruleFuture;
    private NewRuleDialog() {
        setTitle(CREATE_NEW_RULE);
        setLayout(new GridLayout(0,1));
        setModal(true);
        ruleFuture = new CompletableFuture<>();

        JComboBox<RuleType> ruleType = new JComboBox<>(RuleType.values());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static CompletableFuture<WadPackRule> of(){
        return new NewRuleDialog().ruleFuture;
    }

    private enum RuleType{
        minTagRule,
        maxTagRule,
        exclusiveTagRule,
        exclusiveWadRule
    }
}
