package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import javax.swing.*;
import java.util.Optional;

public class NewAmountTagRulePanel extends RulePanel {

    public NewAmountTagRulePanel(Type type) {
        add(new JLabel("Wadfsdfsdfdsf"));
    }

    @Override
    public Optional<WadPackRule> getRule() {
        return Optional.empty();
    }

    public enum Type {
        maxTag,
        minTag
    }


}
