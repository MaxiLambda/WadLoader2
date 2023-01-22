package lincks.maximilian.wadloader2.ddd2application.wadpack;

import lincks.maximilian.wadloader2.ddd3domain.repos.*;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WadPackFactory {

    //Todo write Test for WadPack-creation with rules
    //Positive and negative case, test where Pack is created, deleted, a rule is added and the building process fails
    private final ContainsMinTagRuleRepo minTagRuleRepo;
    private final ContainsMaxTagRuleRepo maxTagRuleRepo;
    private final ExclusiveTagRuleRepo exclusiveTagRuleRepo;
    private final ExclusiveWadRuleRepo exclusiveWadRuleRepo;

    private final WadPackRepo wadPackRepo;
    private final WadRepo wadRepo;
    public void persistWadPack(WadPack wadPack) throws InvalidWadPackConfigurationException {
        List<WadPackRule> brokenRules = Stream.of(
                minTagRuleRepo,
                maxTagRuleRepo,
                exclusiveTagRuleRepo,
                exclusiveWadRuleRepo)
                .map(AbstractRepo::findAll)
                .<WadPackRule>flatMap(List::stream)
                .filter(StreamUtil.filter(
                        tagRule -> tagRule.getPredicate(wadRepo),
                        Predicate::not,
                        rulePredicate -> rulePredicate.test(wadPack)
                ))
                .toList();

        if(brokenRules.isEmpty())
            wadPackRepo.save(wadPack);
        else
            throw InvalidWadPackConfigurationException.withBrokenRules(brokenRules);
    }

    public void deleteWadPack(WadPack wadPack) {
        wadPackRepo.delete(wadPack);
    }
}