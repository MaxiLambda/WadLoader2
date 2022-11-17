package lincks.maximilian.wadloader2.domain.tags;

import lincks.maximilian.wadloader2.utils.PathUtil;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.file.Path;
import java.util.Objects;

@Entity
@Table(name = "Wad_Tags")
public class WadTag implements Tag{

    protected WadTag(){}

    public WadTag(Path wadPath){
        name = PathUtil.fileNameWithoutExtension(wadPath);
    }

    private final static TagType tagType = TagType.WAD_TAG;

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
