package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMaxTagRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainsMaxTagRuleSpringRepo extends JpaRepository<ContainsMaxTagRule, Integer> {
}
