package lincks.maximilian.wadloader2.ddd3domain.tags;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Tag that = (Tag) o;
        return Objects.equals(tagId(), that.tagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,tagType());
    }

    @Override
    public String toString() {
        return "%s (%s)".formatted(name, tagType);
    }
}
