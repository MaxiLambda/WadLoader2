package lincks.maximilian.wadloader2.ddd3domain.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Immutable;

import java.util.Objects;


/**
 * User Created Tag
*/
@Entity
@Table(name = "Custom_Tags")
@Immutable
public class CustomTag implements Tag{

    protected CustomTag(){}

    public CustomTag(String name){
        this.name = name;
    }

    private static final TagType tagType = TagType.CUSTOM_TAG;

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
