package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.rules.ExclusiveTagRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ExclusiveTagRuleSpringRepo;

public class ExclusiveTagRuleBridge extends AbstractBridge<ExclusiveTagRule, Integer>{
    public ExclusiveTagRuleBridge(ExclusiveTagRuleSpringRepo repo) {
        super(repo);
    }
}
