package model.tags;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.nio.file.Path;

/**
 * Tag created based on the Filepath
 */
@Entity
@Table(name = "DefaultTags")
public class DefaultTag implements Tag {

    private final static TagType tagType = TagType.DEFAULT_TAG;

    private DefaultTag(){}

    public DefaultTag(Path wadPath){
        name = wadPath.toAbsolutePath().getParent().toString();
    }

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
