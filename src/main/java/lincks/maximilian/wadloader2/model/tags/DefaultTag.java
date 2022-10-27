package lincks.maximilian.wadloader2.model.tags;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.file.Path;

/**
 * Tag created based on the Filepath
 */
@Entity
@Table(name = "Default_Tags")
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
