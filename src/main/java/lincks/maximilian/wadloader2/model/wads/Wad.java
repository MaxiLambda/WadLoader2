package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.CustomTag;
import lincks.maximilian.wadloader2.model.tags.DefaultTag;
import lincks.maximilian.wadloader2.model.tags.Tag;
import lincks.maximilian.wadloader2.model.tags.WadTag;
import lincks.maximilian.wadloader2.repos.services.CustomTagService;
import lincks.maximilian.wadloader2.utils.CustomTagUtil;
import lombok.Getter;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Entity
@Table(name = "Wads")
@Getter
public class Wad implements WadElement {
    //Todo: implement boolean isIWad and do a check (in the constructor based on the path)
    //Todo: if the wad id an IWad or not
    protected Wad(){}
    //TODO evaluate if an Exception should be raised in case of a path not Ending with an allowed Extension
    public Wad(Path wadPath) {
        path = wadPath.toAbsolutePath().toString();
        wadTag = new WadTag(wadPath);
        defaultTag = new DefaultTag(wadPath);
        customTags = new HashSet<>();
    }

    @Column
    @Id
    private String path;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Wad_Tag", referencedColumnName = "name")
    private WadTag wadTag;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Default_Tag_Name", nullable = false)
    private DefaultTag defaultTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Custom_Tags",
            joinColumns = {@JoinColumn(name = "path")},
            inverseJoinColumns = {@JoinColumn(name = "name")}
    )
    private Set<CustomTag> customTags;

    @Override
    public List<Wad> wads() {
        return List.of(this);
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadTag,defaultTag),
                customTags
        ).flatMap(Collection::stream).toList();
    }

    @Override
    public boolean addCustomTag(String name, CustomTagService customTagService) {
        CustomTag customTag = CustomTagUtil.getCustomTagForName(name,customTagService);
        return customTags.add(customTag);
    }

    //TODO maybe add HashCode
    @Override
    public boolean equals(Object obj) {
        if(Objects.isNull(obj) || !(obj instanceof Wad)) return false;
        else return path.equals(((Wad) obj).path);
    }
}
