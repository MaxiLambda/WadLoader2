package lincks.maximilian.wadloader2.model.tags;

import lincks.maximilian.wadloader2.utils.PathUtil;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.file.Path;
import java.util.Objects;

@Entity
@Table(name = "I_Wad_Tags")
public class IWadTag implements Tag{

    protected IWadTag(){}

    public IWadTag(Path wadPath){
        name = PathUtil.fileNameWithoutExtension(wadPath);
    }

    private static final TagType tagType = TagType.I_WAD_TAG;

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
        IWadTag wadTag = (IWadTag) o;
        return name != null && Objects.equals(name, wadTag.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
