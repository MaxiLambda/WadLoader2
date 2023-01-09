package lincks.maximilian.wadloader2.domain3.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

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
    public boolean test(WadPack wadPack) {
        long countFilteredWads = wadPack.tags()
                .stream()
                .map(Tag::tagId)
                .filter(filterTagId::equals)
                .count();
        return countFilteredWads >= minCount;
    }

    @Override
    public String toString() {
        return "ContainsMaxTagRule{\n filterTagId = \"%s\"\n minCount = \"%s\"}".formatted(filterTagId,minCount);
    }
}
