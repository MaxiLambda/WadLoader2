package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ExclusiveWadRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ExclusiveWadRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveWadRule;
import org.springframework.stereotype.Repository;

@Repository
public class ExclusiveWadRuleBridge extends AbstractBridge<ExclusiveWadRule, Integer> implements ExclusiveWadRuleRepo {
    public ExclusiveWadRuleBridge(ExclusiveWadRuleSpringRepo repo) {
        super(repo);
    }
}
