package lincks.maximilian.wadloader2.ddd2application.wadpack.rules;

import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMaxTagRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMinTagRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ExclusiveTagRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ExclusiveWadRuleRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class RuleFactory {
    private final ContainsMinTagRuleRepo minTagRuleRepo;
    private final ContainsMaxTagRuleRepo maxTagRuleRepo;
    private final ExclusiveTagRuleRepo exclusiveTagRuleRepo;
    private final ExclusiveWadRuleRepo exclusiveWadRuleRepo;

    public void deleteRule(WadPackRule rule){
        switch (rule) {
            case ContainsMinTagRule minRule -> minTagRuleRepo.delete(minRule);
            case ContainsMaxTagRule maxRule -> maxTagRuleRepo.delete(maxRule);
            case ExclusiveTagRule exclusiveTagRule -> exclusiveTagRuleRepo.delete(exclusiveTagRule);
            case ExclusiveWadRule exclusiveWadRule -> exclusiveWadRuleRepo.delete(exclusiveWadRule);
            default -> log.warning("Implement Handling for %s".formatted(rule.getClass().getName()));
        }
    }

    public void persistRule(WadPackRule rule){
        switch (rule) {
            case ContainsMinTagRule minRule -> minTagRuleRepo.save(minRule);
            case ContainsMaxTagRule maxRule -> maxTagRuleRepo.save(maxRule);
            case ExclusiveTagRule exclusiveTagRule -> exclusiveTagRuleRepo.save(exclusiveTagRule);
            case ExclusiveWadRule exclusiveWadRule -> exclusiveWadRuleRepo.save(exclusiveWadRule);
            default -> log.warning("Implement Handling for %s".formatted(rule.getClass().getName()));
        }
    }
}
