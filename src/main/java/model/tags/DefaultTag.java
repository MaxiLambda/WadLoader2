package model.tags;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Tag created based on the Filepath
 */
@AllArgsConstructor
@NoArgsConstructor
public class DefaultTag implements Tag {

    private final static TagType tagType = TagType.DEFAULT_TAG;

    //Todo: maybe change constructor to only accept file paths/ urls
    private String name;

    @Override
    public String tagName() {
        return null;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }
}
