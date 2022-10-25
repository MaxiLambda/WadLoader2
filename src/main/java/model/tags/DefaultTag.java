package model.tags;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.nio.file.Path;

/**
 * Tag created based on the Filepath
 */
@Entity
@Table(name = "DefaultTags")
public class DefaultTag implements Tag {

    private final static TagType tagType = TagType.DEFAULT_TAG;

    protected DefaultTag(){}

    public DefaultTag(Path wadPath){
        name = wadPath.toAbsolutePath().getParent().toString();
    }

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
