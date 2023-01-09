package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.ContainsMaxTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.rules.ContainsMaxTagRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ContainsMaxTagRuleSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class ContainsMaxTagRuleBridge extends AbstractBridge<ContainsMaxTagRule, Integer> implements ContainsMaxTagRuleRepo {
    public ContainsMaxTagRuleBridge(ContainsMaxTagRuleSpringRepo repo) {
        super(repo);
    }
}
