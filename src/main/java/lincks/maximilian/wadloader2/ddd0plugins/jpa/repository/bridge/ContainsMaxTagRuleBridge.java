package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ContainsMaxTagRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMaxTagRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMaxTagRule;
import org.springframework.stereotype.Repository;

@Repository
public class ContainsMaxTagRuleBridge extends AbstractBridge<ContainsMaxTagRule, Integer> implements ContainsMaxTagRuleRepo {
    public ContainsMaxTagRuleBridge(ContainsMaxTagRuleSpringRepo repo) {
        super(repo);
    }
}
