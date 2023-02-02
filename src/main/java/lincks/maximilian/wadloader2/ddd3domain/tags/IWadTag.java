package lincks.maximilian.wadloader2.ddd3domain.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lincks.maximilian.wadloader2.ddd4abstraction.PathUtil;
import org.springframework.data.annotation.Immutable;

import java.nio.file.Path;
import java.util.Objects;

@Entity
@Table(name = "I_Wad_Tags")
@Immutable
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
        if (o == null) return false;
        Tag that = (Tag) o;
        return Objects.equals(tagId(), that.tagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,tagType());
    }
}
