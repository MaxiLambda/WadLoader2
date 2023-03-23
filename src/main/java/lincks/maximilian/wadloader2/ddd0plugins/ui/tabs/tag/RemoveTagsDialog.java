package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.tag;

import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadConfigDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadConfigMapper;
import lincks.maximilian.wadloader2.ddd2application.tags.CustomTagMarker;
import lincks.maximilian.wadloader2.ddd2application.tags.DoesNotHaveTagException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.REMOVE_TAGS;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.REMOVE_TAGS_INSTRUCTION;

public class RemoveTagsDialog extends JDialog {
    private final Map<ImmutableTag, List<WadConfigDto>> tags;
    private final CustomTagMarker marker;
    public RemoveTagsDialog(Map<ImmutableTag, List<WadConfigDto>> tags, CustomTagMarker marker, WadConfigMapper wadConfigMapper) {
        this.tags = tags;
        CheckboxList<ImmutableTag> tagCheckboxList = new CheckboxList<>(
                tags.keySet().stream().toList(),
                REMOVE_TAGS_INSTRUCTION,
                Map.of(REMOVE_TAGS, remove(wadConfigMapper))
                , true);
        this.marker = marker;

        setTitle(REMOVE_TAGS);
        setLayout(new BorderLayout());
        setModal(true);

        add(tagCheckboxList);

        setSize(250,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Consumer<List<ImmutableTag>> remove(WadConfigMapper wadConfigMapper) {
        List<String> exceptions = new ArrayList<>();
        return selectedTags -> {
            selectedTags.forEach(tag -> tags
                    .get(tag)
                    .forEach(wadConfig -> {
                        try {
                            marker.removeCustomTag(wadConfigMapper.fromDto(wadConfig), tag.tagName());
                        } catch (InvalidWadPackConfigurationException | DoesNotHaveTagException e) {
                            exceptions.add(e.getMessage());
                        }
                    }));
            if (!exceptions.isEmpty()) {
                JOptionPane.showMessageDialog(null, exceptions);
            }
            dispose();
        };
    }
}
