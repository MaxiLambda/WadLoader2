package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheckboxList<T> extends JPanel {

    private final transient Map<T, JCheckBox> itemsToCheckbox;
    private final JLabel nameLbl = new JLabel();
    private final JPanel checkBoxPanel;
    private final boolean allowMultiSelection;

    public CheckboxList(List<T> items, String name, Map<String,Consumer<List<T>>> callbacks, boolean allowMultiSelection){
        this.allowMultiSelection = allowMultiSelection;
        nameLbl.setText(name);

        checkBoxPanel = new JPanel(new GridLayout(0,1));
        List<JButton> btns = callbacks.entrySet()
                .stream()
                .map(entry -> {
                    JButton btn = new JButton(entry.getKey());
                    btn.addActionListener(e -> entry.getValue().accept(getSelected()));
                    return btn;
                }).toList();
        JPanel btnPanel = new JPanel(new GridLayout( callbacks.size(),0));
        btns.forEach(btnPanel::add);

        itemsToCheckbox = items.stream().collect(Collectors.toMap(
            Function.identity(),
            item -> new JCheckBox(item.toString())));

        itemsToCheckbox.values().forEach(checkBoxPanel::add);

        if (!allowMultiSelection)
            itemsToCheckbox.values()
                    .forEach(item -> item.addActionListener(e ->
                            itemsToCheckbox.values()
                                    .stream()
                                    .filter(JCheckBox::isSelected)
                                    .filter(jCheckBox -> !jCheckBox.equals(item))
                                    .forEach(jCheckBox -> jCheckBox.setSelected(false))));


        setLayout(new BorderLayout());
        add(nameLbl,BorderLayout.NORTH);
        add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public CheckboxList(List<T> items, String name, Map<String,Consumer<List<T>>> callbacks){
       this(items,name,callbacks,false);
    }

    public List<T> getAll(){
        return itemsToCheckbox.keySet().stream().toList();
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

    /** Returns the item */
    public T put(T item){
        putInternal(item);
        checkBoxPanel.repaint();
        return item;
    }

    private void putInternal(T item){
        itemsToCheckbox.computeIfAbsent(item, i ->{
            JCheckBox checkBox = new JCheckBox(item.toString());
            if(!allowMultiSelection) checkBox.addActionListener(e ->
                    itemsToCheckbox.values()
                            .stream()
                            .filter(JCheckBox::isSelected)
                            .filter(jCheckBox -> !jCheckBox.equals(item))
                            .forEach(jCheckBox -> jCheckBox.setSelected(false)));
            checkBoxPanel.add(checkBox);
            return checkBox;
        });
    }

    public void remove(T key){
        checkBoxPanel.remove(itemsToCheckbox.get(key));
        itemsToCheckbox.remove(key);
        checkBoxPanel.repaint();
    }



    public void clear(){
        itemsToCheckbox.values().forEach(checkBoxPanel::remove);
        itemsToCheckbox.clear();
        checkBoxPanel.repaint();
    }

    public void addAll(Collection<T> items){
        items.forEach(this::putInternal);
        checkBoxPanel.repaint();
    }

    public void setListName(String name){
        nameLbl.setText(name);
    }
}
