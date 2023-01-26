package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import javax.swing.*;
import java.util.Optional;

public class NewAmountTagRule extends JPanel implements RulePanel {

    public NewAmountTagRule(Type type) {

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
