package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.*;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.WadPackTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.exception.WadPackTagException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Table(name = "Wad_Packs")
@Entity
@Getter
public final class WadPack implements WadConfig {

    @EmbeddedId
    WadPackName wadPackName;

    @Column(name = "i_wad")
    private IWadPath iWad;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "Wad_Pack_Custom_Tags",
            joinColumns = {@JoinColumn(name = "pack_Name")},
            inverseJoinColumns = {@JoinColumn(name = "tag_Name")}
    )
    private Set<CustomTag> customTags;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "Wad_Pack_Tag", referencedColumnName = "name")
    private WadPackTag wadPackTag;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL},orphanRemoval = true)
    @Setter(AccessLevel.PRIVATE)
    private List<WadLoadOrder> loadOrder;

    protected WadPack(){}

    public WadPack(WadPackName wadPackName, IWadPath iwad, WadPackReadWriteRepo wadPackRepo) throws WadPackTagException {
        this.wadPackName = wadPackName;
        this.iWad = iwad;
        wadPackTag = new WadPackTag(wadPackName.name);
        customTags = new HashSet<>();
        loadOrder = new ArrayList<>();

        //validate
        boolean isValidName = wadPackRepo.findAll()
                .stream()
                .map(WadPack::getWadPackTag)
                .map(WadPackTag::tagName)
                .anyMatch(tagName -> tagName.equals(wadPackName.name));
        if (isValidName)
            throw new WadPackTagException("A WadPack with the wadPackName %s already exists!".formatted(wadPackName));
    }

    public Map<Integer,WadPath> getWads(){
        return loadOrder.stream().map(WadLoadOrder::getId).collect(Collectors.toMap(
                WadLoadOrderId::getLoadOrder,
                WadLoadOrderId::getWadPath
        ));
    }

    public void setWads(Map<Integer,WadPath> wads){
        setLoadOrder(new ArrayList<>(wads.entrySet()
                .stream()
                .map(entry -> new WadLoadOrder(new WadLoadOrderId(getWadPackName(), entry.getKey(),entry.getValue())))
                .toList()));
    }

    public void addWad(Wad wad){
        int maxPosition = getWads().keySet()
                .stream()
                .mapToInt(i -> i)
                //if the map is empty return -1 so when 1 is added we input at 0
                        .max().orElse(-1);
        if(maxPosition == Integer.MAX_VALUE) throw new WadPackAddException();
        //increase to write to new max position
        maxPosition++;
        loadOrder.add(new WadLoadOrder(new WadLoadOrderId(wadPackName,maxPosition,wad.getPath())));
    }

    @Override
    public List<String> allWadIds() {
        return Stream.concat(
                getWads().values()
                        .stream()
                        .map(WadPath::getPath),
                Stream.of(iWad)
                        .map(IWadPath::getPath)
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
        return customTags.add(new CustomTag(name));
    }

    @Override
    public boolean removeCustomTag(String name) {
        return customTags.removeIf(tag -> tag.tagName().equals(name));
    }

    @Override
    public boolean equals(Object o){
        if (Objects.isNull(o) || !(o instanceof WadPack)) return false;
        else return wadPackName.equals(((WadPack) o).wadPackName);
    }

    @Override
    public String toString() {
        return wadPackName.name;
    }
}
