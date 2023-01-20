package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheckboxList<T> extends JPanel {

    private final transient Map<T, JCheckBox> itemsToCheckbox;

    public CheckboxList(List<T> items, String name, String btnText, Consumer<List<T>> callback, boolean allowMultiselection){

        JButton btn = new JButton(btnText);
        JPanel checkBoxPanel = new JPanel(new GridLayout(0,1));
        btn.addActionListener(e -> callback.accept(getSelected()));

        itemsToCheckbox = items.stream().collect(Collectors.toMap(
            Function.identity(),
            item -> new JCheckBox(item.toString())));

        itemsToCheckbox.values().forEach(checkBoxPanel::add);

        if (!allowMultiselection)
            itemsToCheckbox.values()
                    .forEach(item -> item.addActionListener(e ->
                            itemsToCheckbox.values()
                                    .stream()
                                    .filter(JCheckBox::isSelected)
                                    .filter(jCheckBox -> !jCheckBox.equals(item))
                                    .forEach(jCheckBox -> jCheckBox.setSelected(false))));


        setLayout(new BorderLayout());
        add(new JLabel(name),BorderLayout.NORTH);
        add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
    }

    public CheckboxList(List<T> items, String name, String btnText, Consumer<List<T>> callback){
       this(items,name,btnText,callback,false);
    }

    public List<T> getSelected(){
        return itemsToCheckbox.entrySet()
                .stream()
                .filter(StreamUtil.filter(
                        Map.Entry::getValue,
                        JCheckBox::isSelected
                ))
                .map(Map.Entry::getKey)
                .toList();
    }
}
