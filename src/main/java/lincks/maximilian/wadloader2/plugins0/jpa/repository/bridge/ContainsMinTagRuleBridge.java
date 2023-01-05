package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.rules.ContainsMinTagRule;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.ContainsMinTagRuleSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class ContainsMinTagRuleBridge extends AbstractBridge<ContainsMinTagRule, Integer> {

    public ContainsMinTagRuleBridge(ContainsMinTagRuleSpringRepo repo) {
        super(repo);
    }
}
