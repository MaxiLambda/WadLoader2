package lincks.maximilian.wadloader2.ddd2application.wadpack;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadPackMapper;
import lincks.maximilian.wadloader2.ddd3domain.repos.*;
import lincks.maximilian.wadloader2.ddd3domain.rules.WadPackRule;
import lincks.maximilian.wadloader2.ddd3domain.tags.WadPackTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.exception.WadPackTagException;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
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
        //validate
        String name = base.name();
        IWad iWad = iWadRepo.findById(base.iWad().path())
                .orElseThrow(() -> new InvalidIWadException(
                        "IWad %s does not exist.".formatted(base.iWad().path())));

        boolean isInvalidName = wadPackRepo.findAll()
                .stream()
                .map(WadPack::getWadPackTag)
                .map(WadPackTag::tagName)
                .anyMatch(tagName -> tagName.equals(name));
        if (isInvalidName)
            throw new WadPackTagException("A WadPack with the name %s already exists!".formatted(name));

        return WadPackMapper.toDto(new WadPack(name,iWad));
    }
}
