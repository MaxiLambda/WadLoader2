package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ExclusiveTagRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ExclusiveTagRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveTagRule;
import org.springframework.stereotype.Repository;

@Repository
public class ExclusiveTagRuleBridge extends AbstractBridge<ExclusiveTagRule, Integer> implements ExclusiveTagRuleRepo {
    public ExclusiveTagRuleBridge(ExclusiveTagRuleSpringRepo repo) {
        super(repo);
    }
}
