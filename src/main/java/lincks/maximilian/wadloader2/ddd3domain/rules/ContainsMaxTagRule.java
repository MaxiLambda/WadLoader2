package lincks.maximilian.wadloader2.ddd3domain.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.List;
import java.util.function.Predicate;

/** If a Wadpack contains a certain Tag, it has to contain at max <maxCount> Wads with <filterTagId> */

@Table(name = "Contains_Max_Tag_Rule")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ContainsMaxTagRule implements WadPackRule {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "max_count")
    private long maxCount;

    @Column(name="rule_trigger_tag_id")
    private String ruleTriggerTagId;

    @Column(name = "filter_tag_id")
    private String filterTagId;

    public ContainsMaxTagRule(long maxCount, Tag filterTag, Tag ruleTriggerTag) {
        this.maxCount = maxCount;
        this.filterTagId = filterTag.tagId();
        this.ruleTriggerTagId = ruleTriggerTag.tagId();
    }

    protected ContainsMaxTagRule() {

    }

    @Override
    public Predicate<WadPack> getPredicate(WadReadWriteRepo wadRepo) {
        return (WadPack wadPack) -> {
            List<String> repoTags = TagRuleDomainService.getWadTagIds(wadPack,wadRepo);
            if(!repoTags.contains(ruleTriggerTagId)) return true;
            long countFilteredWads = repoTags
                    .stream()
                    .filter(filterTagId::equals)
                    .count();
            return countFilteredWads <= maxCount;
        };
    }

    @Override
    public String toString() {
        return "ContainsMaxTagRule{%n filterTagId = \"%s\"%n maxCount = \"%s\"}".formatted(filterTagId,maxCount);
    }
}
