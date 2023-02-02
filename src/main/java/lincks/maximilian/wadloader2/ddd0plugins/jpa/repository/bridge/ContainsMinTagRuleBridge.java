package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ContainsMinTagRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMinTagRuleReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMinTagRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContainsMinTagRuleBridge extends AbstractBridge<ContainsMinTagRule, Integer, JpaRepository<ContainsMinTagRule, Integer>> implements ContainsMinTagRuleReadWriteRepo {

    public ContainsMinTagRuleBridge(ContainsMinTagRuleSpringRepo repo) {
        super(repo);
    }
}
