package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.rules.ExclusiveWadRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ExclusiveWadRuleSpringRepo;

public class ExclusiveWadRuleBridge extends AbstractBridge<ExclusiveWadRule, Integer>{
    public ExclusiveWadRuleBridge(ExclusiveWadRuleSpringRepo repo) {
        super(repo);
    }
}
