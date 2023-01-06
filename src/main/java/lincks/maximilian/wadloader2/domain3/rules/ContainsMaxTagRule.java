package lincks.maximilian.wadloader2.domain3.rules;

import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

import javax.persistence.*;
import java.util.function.Predicate;

@Table(name = "Contains_Max_Tag_Rule")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ContainsMaxTagRule implements WadPackRule {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "max_count")
    private long maxCount;

    @Column(name = "filter_tag_id")
    private String filterTagId;

    public ContainsMaxTagRule(long maxCount, Tag filterTag) {
        this.maxCount = maxCount;
        this.filterTagId = filterTag.tagId();
    }

    protected ContainsMaxTagRule() {

    }

    @Override
    public Predicate<WadPack> getPredicate(WadRepo wadRepo) {
        return (WadPack wadPack) -> {
            long countFilteredWads = TagRuleDomainService.getWadTagIds(wadPack,wadRepo)
                    .stream()
                    .filter(filterTagId::equals)
                    .count();
            return countFilteredWads <= maxCount;
        };
    }

    @Override
    public String toString() {
        return "ContainsMaxTagRule{\n filterTagId = \"%s\"\n maxCount = \"%s\"}".formatted(filterTagId,maxCount);
    }
}
