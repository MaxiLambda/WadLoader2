package lincks.maximilian.wadloader2.ddd3domain.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lincks.maximilian.wadloader2.ddd4abstraction.PathUtil;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Immutable;

import java.nio.file.Path;
import java.util.Objects;

@Entity
@Table(name = "Wad_Tags")
@Immutable
public class WadTag implements Tag{

    protected WadTag(){}

    public WadTag(Path wadPath){
        name = PathUtil.fileNameWithoutExtension(wadPath);
    }

    private static final TagType tagType = TagType.WAD_TAG;

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
        WadTag wadTag = (WadTag) o;
        return name != null && Objects.equals(name, wadTag.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
