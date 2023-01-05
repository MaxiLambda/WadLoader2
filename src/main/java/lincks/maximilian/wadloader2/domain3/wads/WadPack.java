package lincks.maximilian.wadloader2.domain3.wads;

import lincks.maximilian.wadloader2.domain3.repos.WadPackRepo;
import lincks.maximilian.wadloader2.domain3.tags.ImmutableTag;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.tags.exception.TagException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
@Getter
public class WadPack implements WadConfig {

    protected WadPack(){}

    public WadPack(String name, IWad iwad, WadPackRepo wadPackService) throws TagException {
        this.name = name;
        this.iwad = iwad.getPath();
        wadPackTag = Tag.wadPackTag(name);
        customTags = new HashSet<>();
        wads = new HashMap<>();

        //validate
        boolean isValidName = wadPackService.findAll()
                .stream()
                .map(WadPack::getWadPackTag)
                .map(Tag::getName)
                .anyMatch(tagName -> tagName.equals(name));
        if (isValidName)
            throw new TagException("A WadPack with the name %s already exists!".formatted(name));
    }

    @Id
    private String name;

    @Column(name = "i_wad")
    private String iwad;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Custom_Tags",
            joinColumns = {@JoinColumn(name = "pack_Name")},
            inverseJoinColumns = {@JoinColumn(name = "tag_Name")}
    )
    private Set<Tag> customTags;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Wad_Pack_Tag", referencedColumnName = "name")
    private Tag wadPackTag;

    @Setter
    @ElementCollection
    @CollectionTable(name = "Loadorder_Wad_Id_Mapping",
            joinColumns = {@JoinColumn(name = "Wad_Pack_Name", referencedColumnName = "Name")})
    @MapKeyColumn(name = "load_order")
    @Column(name = "Wad_Id")
    private Map<Integer,String> wads;

    public void addWad(Wad wad){
        int maxPosition = wads.keySet()
                .stream()
                .mapToInt(i -> i)
                //if the map is empty return -1 so when 1 is added we input at 0
                        .max().orElse(-1);
        if(maxPosition == Integer.MAX_VALUE) throw new WadPackAddException();
        //increase to write to new max position
        maxPosition++;
        Map<Integer, String> newWads = new HashMap<>(wads);
        newWads.put(maxPosition,wad.getPath());
        wads = newWads;
    }

    @Override
    public List<String> allWadIds() {
        return Stream.concat(
                wads.values()
                        .stream(),
                Stream.of(iwad)
        ).toList();
    }

    @Override
    public List<ImmutableTag> tags() {
        return Stream.of(
                List.of(wadPackTag),
                customTags
        )
                .flatMap(Collection::stream)
                .map(ImmutableTag::new)
                .toList();
    }

    @Override
    public boolean addCustomTag(String name) {
        return customTags.add(Tag.customTag(name));
    }

    @Override
    public boolean removeCustomTag(String name) {
        return customTags.removeIf(tag -> tag.getName().equals(name));
    }

    @Override
    public boolean equals(Object o){
        if (Objects.isNull(o) || !(o instanceof WadPack)) return false;
        else return name.equals(((WadPack) o).name);
    }
}
