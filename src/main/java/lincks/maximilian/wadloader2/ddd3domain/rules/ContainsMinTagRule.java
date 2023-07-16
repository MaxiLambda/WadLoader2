package lincks.maximilian.wadloader2.ddd3domain.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.List;
import java.util.function.Predicate;

/** If a Wadpack contains a certain Tag, it has to contain at least <minCount> Wads with <filterTagId> */
@Table(name = "Contains_Min_Tag_Rule")
@Entity
public class ContainsMinTagRule implements WadPackRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "min_count")
    private long minCount;

    @Column(name="rule_trigger_tag_id")
    private String ruleTriggerTagId;

    @Column(name = "filter_tag_id")
    private String filterTagId;

    public ContainsMinTagRule(long minCount, Tag filterTag, Tag ruleTriggerTag) {
        this.minCount = minCount;
        this.filterTagId = filterTag.tagId();
        this.ruleTriggerTagId = ruleTriggerTag.tagId();
    }

    protected ContainsMinTagRule(){}

    @Override
    public Predicate<WadPack> getPredicate(WadReadWriteRepo wadRepo) {
        return (WadPack wadPack) -> {
            List<String> repoTags = TagRuleDomainService.getWadTagIds(wadPack,wadRepo);
            if(!repoTags.contains(ruleTriggerTagId)) return true;
            long countFilteredWads = repoTags
                    .stream()
                    .filter(filterTagId::equals)
                    .count();
            return countFilteredWads >= minCount;
        };
    }

    @Override
    public String toString() {
        return "ContainsMinTagRule{%n filterTagId = \"%s\"%n minCount = \"%s\"}".formatted(filterTagId,minCount);
    }
}
