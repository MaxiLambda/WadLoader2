package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.*;
import lincks.maximilian.wadloader2.repos.services.CustomTagService;
import lincks.maximilian.wadloader2.repos.services.WadPackTagService;
import lincks.maximilian.wadloader2.utils.CustomTagUtil;
import lombok.Getter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
@Getter
public class WadPack implements WadElement {
    //Todo: make sure each WadPack contains an IWad

    protected WadPack(){}

    public WadPack(String name, WadPackTagService wadPackTagService) throws TagException {
        this.name = name;
        wadPackTag = new WadPackTag(name);
        customTags = new HashSet<>();
        wads = new HashSet<>();

        //validate
        if (wadPackTagService.exists(TagType.WAD_PACK_TAG.getIdForName(name)))
            throw new TagException("A WadPack with the name %s already exists!".formatted(name));
    }

    @Id
    private String name;

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
    public List<Wad> wads() {
        return wads.stream().toList();
    }

    @Override
    public List<? extends Tag> tags() {
        return Stream.of(
                List.of(wadPackTag),
                customTags
        ).flatMap(Collection::stream).toList();
    }

    public boolean addCustomTag(String name, CustomTagService customTagService) {
        CustomTag customTag = CustomTagUtil.getCustomTagForName(name,customTagService);
        return customTags.add(customTag);
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
