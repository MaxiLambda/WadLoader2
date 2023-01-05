package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.rules.ContainsMaxTagRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ContainsMaxTagRuleSpringRepo;

public class ContainsMaxTagRuleBridge extends AbstractBridge<ContainsMaxTagRule, Integer>{
    public ContainsMaxTagRuleBridge(ContainsMaxTagRuleSpringRepo repo) {
        super(repo);
    }
}
