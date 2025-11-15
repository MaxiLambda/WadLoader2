package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterCheckBoxList<T> extends CheckboxList<T>{

    public FilterCheckBoxList(List<T> items, String name, Map<String, Consumer<List<T>>> callbacks,
                               boolean allowMultiSelection, JTextField textField, Function<String, Predicate<T>> textToFilter) {
        super(items,name,callbacks,allowMultiSelection,textField);
        //the DocumentListener fires on each input change on textField
        textField.getDocument().addDocumentListener(createListener(textField, itemsToCheckbox, textToFilter));
    }

    private DocumentListener createListener(JTextField textField, Map<T,JCheckBox> itemsToCheckBox, Function<String, Predicate<T>> textToFilter){
        return new OneActionDocumentListener() {
            @Override
            void action(DocumentEvent e){
                String search = textField.getText();

                Map<Boolean, List<Map.Entry<T, JCheckBox>>> itemsHitBySearch = itemsToCheckBox
                        .entrySet()
                        .stream()
                        .collect(Collectors.partitioningBy(StreamUtil.filter(
                        Map.Entry::getKey,
                        textToFilter.apply(search))));

                //show matching items
                itemsHitBySearch.get(true)
                        .stream()
                        .map(Map.Entry::getValue)
                        .forEach(checkBox -> checkBox.setVisible(true));

                //hide and uncheck items which are not matched
                itemsHitBySearch.get(false)
                        .stream()
                        .map(Map.Entry::getValue)
                        .forEach(checkBox -> {
                            checkBox.setVisible(false);
                            checkBox.setSelected(false);
                        });
                revalidate();
                repaint();
            }
        };
    }
}
