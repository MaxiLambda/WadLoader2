package lincks.maximilian.wadloader2.application2.wadpack.rules;

import lincks.maximilian.wadloader2.domain3.repos.ContainsMaxTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.repos.ContainsMinTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.repos.ExclusiveTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.repos.ExclusiveWadRuleRepo;
import lincks.maximilian.wadloader2.domain3.rules.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RuleFactory {

    private final ContainsMinTagRuleRepo minTagRuleRepo;
    private final ContainsMaxTagRuleRepo maxTagRuleRepo;
    private final ExclusiveTagRuleRepo exclusiveTagRuleRepo;
    private final ExclusiveWadRuleRepo exclusiveWadRuleRepo;

    public void deleteRule(WadPackRule rule){
        if (rule instanceof ContainsMinTagRule minRule)
            minTagRuleRepo.delete(minRule);
        else if (rule instanceof ContainsMaxTagRule maxRule)
            maxTagRuleRepo.delete(maxRule);
        else if (rule instanceof ExclusiveTagRule exclusiveTagRule)
            exclusiveTagRuleRepo.delete(exclusiveTagRule);
        else if (rule instanceof ExclusiveWadRule exclusiveWadRule)
            exclusiveWadRuleRepo.delete(exclusiveWadRule);
    }

    public void persistRule(WadPackRule rule){
        if (rule instanceof ContainsMinTagRule minRule)
            minTagRuleRepo.save(minRule);
        else if (rule instanceof ContainsMaxTagRule maxRule)
            maxTagRuleRepo.save(maxRule);
        else if (rule instanceof ExclusiveTagRule exclusiveTagRule)
            exclusiveTagRuleRepo.save(exclusiveTagRule);
        else if (rule instanceof ExclusiveWadRule exclusiveWadRule)
            exclusiveWadRuleRepo.save(exclusiveWadRule);
    }
}
