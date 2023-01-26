package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.ruleconfig;

import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;

import java.util.Optional;

public interface RulePanel {
    Optional<WadPackRule> getRule();
}
