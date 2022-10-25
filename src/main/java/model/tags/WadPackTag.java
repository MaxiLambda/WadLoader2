package model.tags;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * Tag which groups Elements of a Wad-Pack
*/
@AllArgsConstructor
@NoArgsConstructor
public class WadPackTag implements Tag{

    private static final TagType tagType = TagType.WAD_PACK_TAG;
    private String name;

    @Override
    public String tagName() {
        return name;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }
}
