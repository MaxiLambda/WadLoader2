package lincks.maximilian.wadloader2.domain3.tags;

import lombok.Getter;

/**
* Immutable wrapper around Tags to implement DDD Aggregates
 * (used to return immutable copies from objects inside the aggregate)
*/
@Getter
public class ImmutableTag{

    public ImmutableTag(Tag tag){
        name = tag.getName();
        type = tag.getType();
    }

    private final String name;

    private final TagType type;

    public String tagId(){
        return type.getIdForName(name);
    }
}
