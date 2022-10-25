package model.tags;

import jakarta.persistence.Id;
import utils.PathUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.nio.file.Path;

@Entity
@Table(name = "WadTags")
public class WadTag implements Tag{

    protected WadTag(){}

    public WadTag(Path wadPath){
        name = PathUtil.fileNameWithoutExtension(wadPath);
    }

    private final static TagType tagType = TagType.WAD_TAG;

    @Column
    @Id
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
