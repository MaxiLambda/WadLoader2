package lincks.maximilian.wadloader2.domain3.rules;

import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

import javax.persistence.*;
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
    public boolean test(WadPack wadPack) {
        Optional<Predicate<String>> forbiddenPredicate = forbiddenTagIds.stream()
                .<Predicate<String>>map(tagId -> tagId::equals)
                .reduce(Predicate::or);

        //returns true if the predicate is empty
        return forbiddenPredicate.isEmpty() ||
                //if the WadPack does not contain a Wad with the given Id
                !wadPack.allWadIds().contains(wadId) ||
                //if the WadPack contains none of the forbidden Tags
                wadPack.tags()
                        .stream()
                        .map(Tag::tagId)
                        .noneMatch(forbiddenPredicate.get());
    }
}
