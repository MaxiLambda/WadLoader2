package lincks.maximilian.wadloader2.domain3.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

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

    @Override
    public String toString(){
        return "ExclusiveWadRule{\n wadId = \"%s\"\n forbiddenTagIds = %s}".formatted(wadId,forbiddenTagIds);
    }

    @Override
    public Predicate<WadPack> getPredicate(WadRepo wadRepo) {
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
