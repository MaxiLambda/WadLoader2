package model.tags;

import utils.PathUtil;

import java.nio.file.Path;

public class WadTag implements Tag{

    private WadTag(){}

    public WadTag(Path wadPath){
        name = PathUtil.fileNameWithoutExtension(wadPath);
    }

    private final static TagType tagType = TagType.WAD_TAG;

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
