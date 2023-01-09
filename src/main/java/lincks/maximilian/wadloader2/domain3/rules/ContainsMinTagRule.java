package lincks.maximilian.wadloader2.domain3.rules;

import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

import javax.persistence.*;
import java.util.function.Predicate;

@Table(name = "Contains_Min_Tag_Rule")
@Entity
public class ContainsMinTagRule implements WadPackRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "min_count")
    private long minCount;

    @Column(name = "filter_tag_id")
    private String filterTagId;

    public ContainsMinTagRule(long minCount, Tag filterTag) {
        this.minCount = minCount;
        this.filterTagId = filterTag.tagId();
    }

    protected ContainsMinTagRule(){}

    @Override
    public Predicate<WadPack> getPredicate(WadRepo wadRepo) {
        return (WadPack wadPack) -> {
            long countFilteredWads = TagRuleDomainService.getWadTagIds(wadPack,wadRepo)
                    .stream()
                    .filter(filterTagId::equals)
                    .count();
            return countFilteredWads >= minCount;
        };
    }
    @Override
    public String toString() {
        return "ContainsMaxTagRule{\n filterTagId = \"%s\"\n minCount = \"%s\"}".formatted(filterTagId,minCount);
    }
}
