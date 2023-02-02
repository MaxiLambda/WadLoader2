package lincks.maximilian.wadloader2.ddd3domain.rules;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 * Checks if a WadPack does not contain a Tag from a set if it contains tags from another set.
 * The only exception is when a tag is part of both sets and the WadPack contains only this Tag from each Set.
 * This is so that you can use only one rule to express that A,B and C are mutually exclusive with:
 * firstSet = {A,B,C}; secondSet = {A,B,C} else you would have to write two Rules ({A,B},{C}) and ({A},{B})
 **/
@Table(name = "Exclusive_Tag_Rule")
@Entity
public class ExclusiveTagRule implements WadPackRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //swapping firstSet and secondSet does not change the result

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> firstSet;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> secondSet;

    public ExclusiveTagRule(List<Tag> firstSet, List<Tag> secondSet) {
        this.firstSet = firstSet.stream().map(Tag::tagId).collect(Collectors.toSet());
        this.secondSet = secondSet.stream().map(Tag::tagId).collect(Collectors.toSet());
    }

    protected ExclusiveTagRule() {}

    @Override
    public String toString() {
        return "ExclusiveTagRule{%n firstSet = %s%n secondSet = %s}".formatted(firstSet,secondSet);
    }

    @Override
    public Predicate<WadPack> getPredicate(WadReadWriteRepo wadRepo) {
        return (WadPack wadPack) -> {
            Optional<Predicate<String>> containsTagPredicate = firstSet.stream()
                    .<Predicate<String>>map(tagId -> tagId::equals)
                    .reduce(Predicate::or);

            Optional<Predicate<String>> forbiddenTagsPredicate = secondSet.stream()
                    .<Predicate<String>>map(tagId -> tagId::equals)
                    .reduce(Predicate::or);
            List<String> contained = TagRuleDomainService.getWadTagIds(wadPack,wadRepo);
        return containsTagPredicate.isEmpty() ||
                //returns true if either one of the Sets is empty which implies that the optional is empty
                forbiddenTagsPredicate.isEmpty() ||
                //returns true if the WadPack does not contain tags from the firstSet
                //returns true if the WadPack does not contain tags from the secondSet
                //returns true if from both sets only one tag is contained, and it is the same
                test(containsTagPredicate.get(), forbiddenTagsPredicate.get(), contained);

        //can only return false if:
        // neither one of the Optionals is empty
        // the WadPack contains Tags from firstSet
        // the WadPack contains Tags from secondSet, and both Sets do not consist of the same tag
        };
    }

    private boolean test(Predicate<String> firstPred, Predicate<String> secondPred, Collection<String> list){
        List<String> matchFirst = list.stream().filter(firstPred).toList();
        List<String> matchSecond = list.stream().filter(secondPred).toList();
        return  matchFirst.isEmpty() ||
                matchSecond.isEmpty() ||
                matchFirst.size() == 1 && matchFirst.containsAll(matchSecond);

    }
}
