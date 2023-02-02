package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import javax.swing.*;
import java.util.Optional;

public abstract class RulePanel extends JPanel {
    abstract Optional<WadPackRule> getRule();
}
