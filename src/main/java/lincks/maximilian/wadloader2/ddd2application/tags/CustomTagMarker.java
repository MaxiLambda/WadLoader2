package lincks.maximilian.wadloader2.ddd2application.tags;

import lincks.maximilian.wadloader2.ddd2application.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackFactory;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadConfig;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomTagMarker {
    private final WadReadWriteRepo wadRepo;
    private final WadPackFactory wadPackFactory;

    public boolean removeAllCustomTags(WadConfig wadConfig) throws InvalidWadPackConfigurationException {
        List<ImmutableTag> customTags = wadConfig.customTags();
        boolean anyFailed = customTags.stream()
                .map(Tag::tagName)
                .map(wadConfig::removeCustomTag)
                .reduce(Boolean::logicalAnd)
                .orElse(true);

        persistCustomTagRemoval(wadConfig, customTags.stream()
                .map(ImmutableTag::tagName)
                .map(CustomTag::new)
                .toList());
        return anyFailed;
    }

    public boolean removeCustomTag(WadConfig wadConfig, String name) throws InvalidWadPackConfigurationException {
        //there should be only one Tag (max) left
        List<ImmutableTag> customTag = wadConfig
                .customTags()
                .stream()
                .filter(StreamUtil.filter(Tag::tagName,name::equals))
                .toList();
        Optional<Boolean> failed = customTag.stream()
                .map(Tag::tagName)
                .map(wadConfig::removeCustomTag)
                .reduce(Boolean::logicalAnd);

        persistCustomTagRemoval(wadConfig, customTag.stream()
                .map(ImmutableTag::tagName)
                .map(CustomTag::new).toList());

        return failed.orElseThrow(
                () -> new DoesNotHaveTagException("%s does not have a customTag called %s".formatted(wadConfig,name)));
    }

    private void persistCustomTagRemoval(WadConfig wadConfig, List<CustomTag> customTag) throws InvalidWadPackConfigurationException {
        if(wadConfig instanceof Wad wad){
            wadRepo.save(wad);
        }
        else if(wadConfig instanceof WadPack wadPack){
            try {
                wadPackFactory.persistWadPack(wadPack);
            } catch (InvalidWadPackConfigurationException e){
                //if the pack can't be persisted, undo all changes
                customTag.stream()
                        .map(Tag::tagName)
                        .forEach(wadPack::addCustomTag);
                throw e;
            }
        }
    }

    public void addCustomTag(WadConfig wadConfig, String name) throws InvalidWadPackConfigurationException {
        wadConfig.addCustomTag(name);

        if(wadConfig instanceof Wad wad){
            wadRepo.save(wad);
        }
        else if(wadConfig instanceof WadPack wadPack){
            try {
                wadPackFactory.persistWadPack(wadPack);
            } catch (InvalidWadPackConfigurationException e){
                //if the pack can't be persisted, undo all changes
                wadConfig.removeCustomTag(name);
                throw e;
            }
        }
    }

}
