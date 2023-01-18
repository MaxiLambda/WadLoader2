package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveWadRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExclusiveWadRuleSpringRepo extends JpaRepository<ExclusiveWadRule, Integer> {
}
