package lincks.maximilian.wadloader2.ddd0plugins.ui.utility;

import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CheckboxList<T> extends JPanel {

    protected final transient Map<T, JCheckBox> itemsToCheckbox = new HashMap<>();
    private final JLabel nameLbl = new JLabel();
    private final JPanel checkBoxPanel;
    private final boolean allowMultiSelection;

    public CheckboxList(List<T> items, String name, Map<String,Consumer<List<T>>> callbacks, boolean allowMultiSelection,JComponent customComponent){
        this.allowMultiSelection = allowMultiSelection;
        nameLbl.setText(name);

        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel,BoxLayout.Y_AXIS));
        List<JButton> btns = callbacks.entrySet()
                .stream()
                .map(entry -> {
                    JButton btn = new JButton(entry.getKey());
                    btn.addActionListener(e -> entry.getValue().accept(getSelected()));
                    return btn;
                }).toList();
        JPanel btnPanel = new JPanel(new GridLayout( Math.max(callbacks.size(),1) ,0));
        btns.forEach(btnPanel::add);

        addAll(items);

        var sc = new JScrollPane(checkBoxPanel);
        sc.getViewport().setPreferredSize(new Dimension(300,300));

        JPanel headerPanel = new JPanel(new GridLayout(0,1));
        headerPanel.add(nameLbl);
        headerPanel.add(customComponent);
        setLayout(new BorderLayout());
        add(headerPanel,BorderLayout.NORTH);
        add(sc, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public CheckboxList(List<T> items, String name, Map<String,Consumer<List<T>>> callbacks, boolean allowMultiSelection){
       this(items,name,callbacks,allowMultiSelection,new JPanel());
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
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
        return item;
    }

    private void putInternal(T item){
        itemsToCheckbox.computeIfAbsent(item, i ->{
            JCheckBox checkBox = new JCheckBox(item.toString());
            if(!allowMultiSelection) checkBox.addActionListener(e -> {
                if(checkBox.isSelected()) {
                    itemsToCheckbox.entrySet()
                            .stream()
                            .filter(StreamUtil.filter(
                                    Map.Entry::getValue,
                                    JCheckBox::isSelected))
                            .filter(StreamUtil.filter(
                                    Map.Entry::getKey,
                                    key -> !item.equals(key)))
                            .map(Map.Entry::getValue)
                            .forEach(jCheckBox -> jCheckBox.setSelected(false));
                }
            });
            checkBoxPanel.add(checkBox);
            return checkBox;
        });
    }

    public void remove(T key){
        checkBoxPanel.remove(itemsToCheckbox.get(key));
        itemsToCheckbox.remove(key);
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }



    public void clear(){
        itemsToCheckbox.values().forEach(checkBoxPanel::remove);
        itemsToCheckbox.clear();
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }

    public void addAll(Collection<T> items){
        items.forEach(this::putInternal);
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }

    public void setListName(String name){
        nameLbl.setText(name);
    }

    public void unselectAll(){
        itemsToCheckbox.values().forEach(checkBox -> checkBox.setSelected(false));
    }
}
