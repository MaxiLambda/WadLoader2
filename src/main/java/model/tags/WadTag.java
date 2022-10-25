package model.tags;

import utils.PathUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.nio.file.Path;

@Entity
@Table(name = "WadTags")
public class WadTag implements Tag{

    private WadTag(){}

    public WadTag(Path wadPath){
        name = PathUtil.fileNameWithoutExtension(wadPath);
    }

    private final static TagType tagType = TagType.WAD_TAG;

    @Column
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
