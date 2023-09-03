package lincks.maximilian.wadloader2.ddd2application.wadpack.rules;

import lincks.maximilian.wadloader2.ddd3domain.repos.AbstractReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMaxTagRuleReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ContainsMinTagRuleReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.ExclusiveTagRuleReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMaxTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMinTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log
public class RuleFactory {
    private final ContainsMinTagRuleReadWriteRepo minTagRuleRepo;
    private final ContainsMaxTagRuleReadWriteRepo maxTagRuleRepo;
    private final ExclusiveTagRuleReadWriteRepo exclusiveTagRuleRepo;

    public void deleteRule(WadPackRule rule){
        if (rule instanceof ContainsMinTagRule minRule) {
            minTagRuleRepo.delete(minRule);
        } else if (rule instanceof ContainsMaxTagRule maxRule) {
            maxTagRuleRepo.delete(maxRule);
        } else if (rule instanceof ExclusiveTagRule exclusiveTagRule) {
            exclusiveTagRuleRepo.delete(exclusiveTagRule);
        } else {
            log.warning("Implement Handling for %s".formatted(rule.getClass().getName()));
        }
    }

    public void persistRule(WadPackRule rule){
        if (rule instanceof ContainsMinTagRule minRule) {
            minTagRuleRepo.save(minRule);
        } else if (rule instanceof ContainsMaxTagRule maxRule) {
            maxTagRuleRepo.save(maxRule);
        } else if (rule instanceof ExclusiveTagRule exclusiveTagRule) {
            exclusiveTagRuleRepo.save(exclusiveTagRule);
        } else {
            log.warning("Implement Handling for %s".formatted(rule.getClass().getName()));
        }
    }

    public List<WadPackRule> allRules(){
        return Stream.of(
                minTagRuleRepo,
                maxTagRuleRepo,
                exclusiveTagRuleRepo)
                .map(AbstractReadWriteRepo::findAll)
                .flatMap(List::stream)
                .map(WadPackRule.class::cast)
                .toList();
    }
}
