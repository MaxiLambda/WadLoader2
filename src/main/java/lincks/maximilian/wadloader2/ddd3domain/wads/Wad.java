package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.WadTag;
import lincks.maximilian.wadloader2.ddd4abstraction.PathUtil;
import lombok.Getter;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Entity
@Table(name = "Wads")
@Getter
public non-sealed class Wad implements SingleWad {
    protected Wad(){}
    public Wad(Path wadPath) {
        path = wadPath.toAbsolutePath().toString();
        wadTag = new WadTag(wadPath);
        defaultTag = new DefaultTag(wadPath);
        customTags = new HashSet<>();
    }

    @Id
    private String path;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Wad_Tag", referencedColumnName = "name")
    private WadTag wadTag;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Default_Tag_Name", nullable = false)
    private DefaultTag defaultTag;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Custom_Tags",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<CustomTag> customTags;

    @Override
    public List<String> allWadIds() {
        return List.of(path);
    }

    @Override
    public List<ImmutableTag> tags() {
        return Stream.of(
                List.of(wadTag,defaultTag),
                customTags
        )
                .flatMap(Collection::stream)
                .map(ImmutableTag::new)
                .toList();
    }

    @Override
    public boolean addCustomTag(String name) {
        return customTags.add(new CustomTag(name));
    }

    @Override
    public boolean removeCustomTag(String name) {
        return customTags.removeIf(tag -> tag.tagName().equals(name));
    }

    //TODO maybe add HashCode
    @Override
    public boolean equals(Object obj) {
        if(Objects.isNull(obj) || !(obj instanceof Wad)) return false;
        else return path.equals(((Wad) obj).path);
    }

    @Override
    public String toString() {
        return PathUtil.getFileName(path);
    }
}
