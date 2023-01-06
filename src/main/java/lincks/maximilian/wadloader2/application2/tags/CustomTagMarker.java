package lincks.maximilian.wadloader2.application2.tags;

import lincks.maximilian.wadloader2.abstraction4.StreamUtil;
import lincks.maximilian.wadloader2.application2.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.application2.wadpack.WadPackFactory;
import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.CustomTag;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.domain3.wads.WadConfig;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomTagMarker {
    private final WadRepo wadRepo;
    private final WadPackFactory wadPackFactory;

    public boolean removeAllCustomTags(WadConfig wadConfig) throws InvalidWadPackConfigurationException {
        List<CustomTag> customTags = wadConfig.customTags();
        boolean anyFailed = customTags.stream()
                .map(wadConfig::removeCustomTag)
                .reduce(Boolean::logicalAnd)
                .orElse(true);

        persistCustomTagRemoval(wadConfig, customTags);
        return anyFailed;
    }

    public boolean removeCustomTag(WadConfig wadConfig, String name) throws InvalidWadPackConfigurationException {
        //there should be only one Tag (max) left
        List<CustomTag> customTag = wadConfig
                .customTags()
                .stream()
                .filter(StreamUtil.filter(Tag::tagName,name::equals))
                .toList();
        Optional<Boolean> failed = customTag.stream()
                .map(wadConfig::removeCustomTag)
                .reduce(Boolean::logicalAnd);

        persistCustomTagRemoval(wadConfig, customTag);

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
