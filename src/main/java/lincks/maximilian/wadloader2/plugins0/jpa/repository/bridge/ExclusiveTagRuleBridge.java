package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.ExclusiveTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.rules.ExclusiveTagRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ExclusiveTagRuleSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class ExclusiveTagRuleBridge extends AbstractBridge<ExclusiveTagRule, Integer> implements ExclusiveTagRuleRepo {
    public ExclusiveTagRuleBridge(ExclusiveTagRuleSpringRepo repo) {
        super(repo);
    }
}
