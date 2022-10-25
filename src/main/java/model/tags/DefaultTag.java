package model.tags;

import java.nio.file.Path;

/**
 * Tag created based on the Filepath
 */

public class DefaultTag implements Tag {

    private final static TagType tagType = TagType.DEFAULT_TAG;

    private DefaultTag(){}

    public DefaultTag(Path wadPath){
        name = wadPath.toAbsolutePath().getParent().toString();
    }

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
