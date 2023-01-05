package lincks.maximilian.wadloader2.application2.wadpack;

import lincks.maximilian.wadloader2.abstraction4.StreamUtil;
import lincks.maximilian.wadloader2.domain3.repos.*;
import lincks.maximilian.wadloader2.domain3.rules.WadPackRule;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WadPackFactory {

    private final ContainsMinTagRuleRepo minTagRuleRepo;
    private final ContainsMaxTagRuleRepo maxTagRuleRepo;
    private final ExclusiveTagRuleRepo exclusiveTagRuleRepo;
    private final ExclusiveWadRuleRepo exclusiveWadRuleRepo;

    private final WadPackRepo wadPackRepo;

    public void persistWadPack(WadPack wadPack) throws InvalidWadPackConfigurationException {
        List<WadPackRule> brokenRules = Stream.of(
                minTagRuleRepo,
                maxTagRuleRepo,
                exclusiveTagRuleRepo,
                exclusiveWadRuleRepo)
                .map(AbstractRepo::findAll)
                .<WadPackRule>flatMap(List::stream)
                .filter(StreamUtil.filter(
                        Predicate::not,
                        rule -> rule.test(wadPack)))
                .toList();

        if(brokenRules.isEmpty())
            wadPackRepo.save(wadPack);
        else
            throw InvalidWadPackConfigurationException.withBrokenRules(brokenRules);
    }
   // TODO add method to create and verify Validity according to the rules of a wadpack
   // TODO if a wadpack satisfies the rules, it should be persisted
   // TODO create DTO of the WadPack
}
