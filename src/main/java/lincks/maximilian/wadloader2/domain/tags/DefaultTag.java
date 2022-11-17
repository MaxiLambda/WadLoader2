package lincks.maximilian.wadloader2.domain.tags;

import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.file.Path;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DefaultTag that = (DefaultTag) o;
        return name != null && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
