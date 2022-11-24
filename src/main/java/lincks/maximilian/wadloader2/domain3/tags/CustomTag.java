package lincks.maximilian.wadloader2.domain3.tags;

import org.hibernate.Hibernate;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomTag customTag = (CustomTag) o;
        return name != null && Objects.equals(name, customTag.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
