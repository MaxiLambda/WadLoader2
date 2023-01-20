package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ContainsMinTagRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMinTagRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMinTagRule;
import org.springframework.stereotype.Repository;

@Repository
public class ContainsMinTagRuleBridge extends AbstractBridge<ContainsMinTagRule, Integer> implements ContainsMinTagRuleRepo {

    public ContainsMinTagRuleBridge(ContainsMinTagRuleSpringRepo repo) {
        super(repo);
    }
}
