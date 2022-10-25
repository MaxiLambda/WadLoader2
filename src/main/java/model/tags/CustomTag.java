package model.tags;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



/**
 * User Created Tag
*/
@AllArgsConstructor
@NoArgsConstructor
public class CustomTag implements Tag{

    private static final TagType tagType = TagType.CUSTOM_TAG;
    private String tag;

    @Override
    public String tagName() {
        return tag;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }
}
