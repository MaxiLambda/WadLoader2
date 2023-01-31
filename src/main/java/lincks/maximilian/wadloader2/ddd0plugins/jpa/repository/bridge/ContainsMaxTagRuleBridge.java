package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ContainsMaxTagRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMaxTagRuleReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMaxTagRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContainsMaxTagRuleBridge extends AbstractBridge<ContainsMaxTagRule, Integer, JpaRepository<ContainsMaxTagRule, Integer>> implements ContainsMaxTagRuleReadWriteRepo {
    public ContainsMaxTagRuleBridge(ContainsMaxTagRuleSpringRepo repo) {
        super(repo);
    }
}
