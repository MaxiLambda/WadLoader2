package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMinTagRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainsMinTagRuleSpringRepo extends JpaRepository<ContainsMinTagRule, Integer> {
}
