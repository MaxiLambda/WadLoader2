package lincks.maximilian.wadloader2.ddd3domain.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Immutable;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Tag created based on the Filepath
 */
@Entity
@Table(name = "Default_Tags")
@Immutable
public class DefaultTag implements Tag {

    private static final TagType tagType = TagType.DEFAULT_TAG;

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
        if (o == null) return false;
        Tag that = (Tag) o;
        return Objects.equals(tagId(), that.tagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,tagType());
    }

    @Override
    public String toString() {
        return "%s (%s)".formatted(name, tagType);
    }
}
