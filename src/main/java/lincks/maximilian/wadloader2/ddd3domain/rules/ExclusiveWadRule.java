package lincks.maximilian.wadloader2.ddd3domain.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Table
@Entity
public class ExclusiveWadRule implements WadPackRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "wad_id")
    private String wadId;

    @ElementCollection
    private Set<String> forbiddenTagIds;

    public ExclusiveWadRule(String wadId, Set<? extends Tag> forbiddenTags) {
        this.wadId = wadId;
        this.forbiddenTagIds = forbiddenTags.stream().map(Tag::tagId).collect(Collectors.toSet());
    }

    protected ExclusiveWadRule(){}
    @Override
    public String toString(){
        return "ExclusiveWadRule{%n wadId = \"%s\"%n forbiddenTagIds = %s}".formatted(wadId,forbiddenTagIds);
    }

    @Override
    public Predicate<WadPack> getPredicate(WadReadWriteRepo wadRepo) {
        return (WadPack wadPack) -> {
            Optional<Predicate<String>> forbiddenPredicate = forbiddenTagIds.stream()
                    .<Predicate<String>>map(tagId -> tagId::equals)
                    .reduce(Predicate::or);

            //returns true if the predicate is empty
            return forbiddenPredicate.isEmpty() ||
                    //if the WadPack does not contain a Wad with the given Id
                    !wadPack.allWadIds().contains(wadId) ||
                    //if the WadPack contains none of the forbidden Tags
                    TagRuleDomainService.getWadTagIds(wadPack,wadRepo)
                            .stream()
                            .noneMatch(forbiddenPredicate.get());
        };
    }
}
