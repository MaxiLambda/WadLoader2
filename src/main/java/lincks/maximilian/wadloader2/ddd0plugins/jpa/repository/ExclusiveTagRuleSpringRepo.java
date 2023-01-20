package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveTagRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExclusiveTagRuleSpringRepo extends JpaRepository<ExclusiveTagRule, Integer> {
}
