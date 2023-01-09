package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.ExclusiveWadRuleRepo;
import lincks.maximilian.wadloader2.domain3.rules.ExclusiveWadRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ExclusiveWadRuleSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class ExclusiveWadRuleBridge extends AbstractBridge<ExclusiveWadRule, Integer> implements ExclusiveWadRuleRepo {
    public ExclusiveWadRuleBridge(ExclusiveWadRuleSpringRepo repo) {
        super(repo);
    }
}
