package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.CustomTag;
import lincks.maximilian.wadloader2.model.tags.Tag;
import lincks.maximilian.wadloader2.model.tags.TagException;
import lincks.maximilian.wadloader2.model.tags.WadPackTag;
import lincks.maximilian.wadloader2.repos.services.WadPackTagService;
import lombok.Getter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
@Getter
public class WadPack implements WadConfig {
    //Todo: make sure each WadPack contains an IWad

    protected WadPack(){}

    public WadPack(String name, IWad iwad,WadPackTagService wadPackTagService) throws TagException {
        this.name = name;
        this.iwad = iwad;
        wadPackTag = new WadPackTag(name);
        customTags = new HashSet<>();
        wads = new HashSet<>();

        //validate
        if (wadPackTagService.exists(name))
            throw new TagException("A WadPack with the name %s already exists!".formatted(name));
    }

    @Id
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "i_wad")
    private IWad iwad;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Custom_Tags",
            joinColumns = {@JoinColumn(name = "pack_Name")},
            inverseJoinColumns = {@JoinColumn(name = "tag_Name")}
    )
    private Set<CustomTag> customTags;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Wad_Pack_Tag", referencedColumnName = "name")
    private WadPackTag wadPackTag;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Wad",
            joinColumns = {@JoinColumn(name = "name")},
            inverseJoinColumns = {@JoinColumn(name = "path")}
    )
    private Set<Wad> wads;

    @Override
    public List<? extends SingleWad> allWads() {
        return Stream.concat(wads.stream(),Stream.of(iwad)).toList();
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadPackTag),
                customTags
        ).flatMap(Collection::stream).toList();
    }

    public boolean addCustomTag(String name) {
        return customTags.add(new CustomTag(name));
    }

    @Override
    public boolean removeCustomTag(String name) {
        return customTags.removeIf(tag -> tag.tagName().equals(name));
    }

    public boolean addWad(Wad wad){
        return wads.add(wad);
    }

    @Override
    public boolean equals(Object o){
        if (Objects.isNull(o) || !(o instanceof WadPack)) return false;
        else return name.equals(((WadPack) o).name);
    }
}
