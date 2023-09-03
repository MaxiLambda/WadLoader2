package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadConfigDto;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.tags.TagType;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class WadConfigFilterCheckBoxList {
    private WadConfigFilterCheckBoxList() {
    }

    public static <T extends WadConfigDto> FilterCheckBoxList<T> of(List<T> items, String name, Map<String, Consumer<List<T>>> callbacks,
                                                                    boolean allowMultiSelection) {
        return new FilterCheckBoxList<>(items, name, callbacks, allowMultiSelection, createTextFieldWithToolTip(), WadConfigFilterCheckBoxList::textToFilter);
    }

    public static <T extends WadConfigDto> FilterCheckBoxList<T> of(List<T> items, String name, Map<String, Consumer<List<T>>> callbacks) {
        return new FilterCheckBoxList<>(items, name, callbacks, false, createTextFieldWithToolTip(), WadConfigFilterCheckBoxList::textToFilter);
    }

    private static JTextField createTextFieldWithToolTip() {
        JTextField textField = new JTextField();
        textField.setToolTipText("Use \"\" to search for exact name, c: for custom tags, d: for default tags.\n" +
                "else all WadConfigs matching one of the searches will be shown");
        return textField;
    }

    private static <T extends WadConfigDto> Predicate<T> textToFilter(String text) {
        final String searchText = text.toLowerCase(Locale.ROOT);
        if (searchText.matches("\".*\""))
            return textToNameFilter(searchText.substring(1, searchText.length() - 1));
        else if (searchText.startsWith("c:"))
            return textToCustomTagFilter(searchText.substring(2));
        else if (searchText.startsWith("d:"))
            return textToDefaultTagFilter(searchText.substring(2));
        else
            //fully qualified name with T is necessary, otherwise a cast is needed
            return WadConfigFilterCheckBoxList
                    .<T>textToNameFilter(searchText)
                    .or(textToCustomTagFilter(searchText))
                    .or(textToDefaultTagFilter(searchText));
    }

    private static <T extends WadConfigDto> Predicate<T> textToCustomTagFilter(String text) {
        final String searchText = text.toLowerCase(Locale.ROOT);
        return wad -> wad.tags()
                .stream().filter(StreamUtil.filter(Tag::tagType, TagType.CUSTOM_TAG::equals))
                .map(Tag::tagName)
                .map(name -> name.toLowerCase(Locale.ROOT))
                .anyMatch(name -> name.contains(searchText));
    }

    private static <T extends WadConfigDto> Predicate<T> textToDefaultTagFilter(String text) {
        final String searchText = text.toLowerCase(Locale.ROOT);
        return wad -> wad.tags()
                .stream().filter(StreamUtil.filter(Tag::tagType, TagType.DEFAULT_TAG::equals))
                .map(Tag::tagName)
                .map(name -> name.toLowerCase(Locale.ROOT))
                .anyMatch(name -> name.contains(searchText));
    }

    private static <T extends WadConfigDto> Predicate<T> textToNameFilter(String text) {
        final String searchText = text.toLowerCase(Locale.ROOT);
        return wad -> wad.toString().toLowerCase(Locale.ROOT).contains(searchText);
    }
}
