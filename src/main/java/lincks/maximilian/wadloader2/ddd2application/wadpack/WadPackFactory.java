package lincks.maximilian.wadloader2.ddd2application.wadpack;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadPackMapper;
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

    //Positive and negative case, test where Pack is created, deleted, a rule is added and the building process fails
    private final ContainsMinTagRuleReadWriteRepo minTagRuleRepo;
    private final ContainsMaxTagRuleReadWriteRepo maxTagRuleRepo;
    private final ExclusiveTagRuleReadWriteRepo exclusiveTagRuleRepo;

    private final IWadReadWriteRepo iWadRepo;
    private final WadPackReadWriteRepo wadPackRepo;
    private final WadReadWriteRepo wadRepo;
    public void persistWadPack(WadPack wadPack) throws InvalidWadPackConfigurationException {
        List<WadPackRule> brokenRules = Stream.of(
                minTagRuleRepo,
                maxTagRuleRepo,
                exclusiveTagRuleRepo)
                .map(AbstractReadWriteRepo::findAll)
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

    public WadPackDto newPack(WadPackBase base){
        return WadPackMapper.toDto(new WadPack(base.name(),iWadRepo.findById(base.iWad().path()).get(),wadPackRepo));
    }
}
