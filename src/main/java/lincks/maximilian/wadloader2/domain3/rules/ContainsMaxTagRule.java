package lincks.maximilian.wadloader2.domain3.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

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
    public boolean test(WadPack wadPack) {
        long countFilteredWads = wadPack.tags()
                .stream()
                .map(Tag::tagId)
                .filter(filterTagId::equals)
                .count();
        return countFilteredWads <= maxCount;
    }

    @Override
    public String toString() {
        return "ContainsMaxTagRule{\n filterTagId = \"%s\"\n maxCount = \"%s\"}".formatted(filterTagId,maxCount);
    }
}
