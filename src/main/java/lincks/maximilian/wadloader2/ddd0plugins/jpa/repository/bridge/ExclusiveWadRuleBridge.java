package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.ExclusiveWadRuleSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ExclusiveWadRuleReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveWadRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ExclusiveWadRuleBridge extends AbstractBridge<ExclusiveWadRule, Integer, JpaRepository<ExclusiveWadRule, Integer>> implements ExclusiveWadRuleReadWriteRepo {
    public ExclusiveWadRuleBridge(ExclusiveWadRuleSpringRepo repo) {
        super(repo);
    }
}
