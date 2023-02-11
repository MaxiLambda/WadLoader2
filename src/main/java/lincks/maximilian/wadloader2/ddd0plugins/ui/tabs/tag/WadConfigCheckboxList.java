package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.tag;

import lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.tags.CustomTagMarker;
import lincks.maximilian.wadloader2.ddd2application.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.TagType;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadConfig;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.ADD_TAGS;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.REMOVE_TAGS;


public class WadConfigCheckboxList {
    private WadConfigCheckboxList(){}
    public static  <T extends WadConfig> CheckboxList<T> of(String name,CustomTagMarker marker){
        return WadConfigFilterCheckBoxList.of(List.of(), name, Map.of(REMOVE_TAGS, remove(marker), ADD_TAGS, add(marker)),true);
    }

    /** offers the opportunity to remove all kinds of Tags contained in the elements */
    private static <T  extends WadConfig> Consumer<List<T>> remove(CustomTagMarker marker){

        new HashSet<>(List.of());
        return list -> {
            if (list.isEmpty()) return;
            Map<ImmutableTag, List<WadConfig>> tags = list.stream()
                    .flatMap(wadConfig -> wadConfig.tags()
                            .stream()
                            .filter(tag -> tag.tagType().equals(TagType.CUSTOM_TAG))
                            .map(tag -> Map.entry(tag, (WadConfig) wadConfig)))
                    .collect(Collectors.groupingBy(
                            Map.Entry::getKey,
                            Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
            //show a jdialog with a checkboxlist of all distinct tags
            new RemoveTagsDialog(tags, marker);


        };
    }

    private static <T  extends WadConfig> Consumer<List<T>> add(CustomTagMarker marker){
        return list -> {
            if (list.isEmpty()) return;
            List<String> exceptions = new LinkedList<>();
            Optional.ofNullable(JOptionPane.showInputDialog(UIConstants.NEW_CUSTOM_TAG))
                    .filter(Predicate.not(String::isBlank))
                    .ifPresent(tagName ->
                            list.forEach(wadConfig -> {
                                try {
                                    marker.addCustomTag(wadConfig, tagName);
                                } catch (InvalidWadPackConfigurationException e) {
                                    exceptions.add(e.getMessage());
                                }
                            }

                    ));
            if (!exceptions.isEmpty()) {
                JOptionPane.showMessageDialog(null, exceptions);
            }
        };
    }
}
