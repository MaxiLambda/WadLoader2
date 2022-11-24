package lincks.maximilian.wadloader2.domain3.tags;

/**
* Immutable wrapper around Tags to implement DDD Aggregates
 * (used to return immutable copies from objects inside the aggregate)
*/
public class ImmutableTag implements Tag{

    public ImmutableTag(Tag tag){
        name = tag.tagName();
        tagType = tag.tagType();
    }

    private final String name;

    private final TagType tagType;

    @Override
    public String tagName() {
        return name;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }
}
