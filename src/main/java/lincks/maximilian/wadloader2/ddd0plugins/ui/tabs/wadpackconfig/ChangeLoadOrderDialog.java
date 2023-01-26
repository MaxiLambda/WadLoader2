package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig;

import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd4abstraction.PathUtil;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.CHANGE_LOAD_ORDER_DIALOG_TITLE;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.SAVE_BTN;

public class ChangeLoadOrderDialog extends JDialog {

    @Getter
    private transient CompletableFuture<Map<Integer, String>> loadOrder = new CompletableFuture<>();

    public ChangeLoadOrderDialog(List<Wad> wads) {
        setTitle(CHANGE_LOAD_ORDER_DIALOG_TITLE);
        setLayout(new BorderLayout());
        setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                loadOrder.cancel(true);
            }
        });

        JPanel spinners = new JPanel(new GridLayout(0,1));
        JButton saveLoadOrderBtn = new JButton(SAVE_BTN);

        Map<Wad, JSpinner> wadLoadOrderSpinners = wads.stream().collect(
                Collectors.toMap(
                        Function.identity(),
                        ignore -> new JSpinner(new SpinnerNumberModel(0,0,wads.size() * 2,1))));
        wadLoadOrderSpinners.forEach((wad, spinner)-> {
            spinners.add(new JLabel(PathUtil.getFileName(wad.getPath())));
            spinners.add(spinner);
        });

        saveLoadOrderBtn.addActionListener(e -> {
            HashMap<Integer, Wad> accOrder = new HashMap<>();
            wadLoadOrderSpinners.forEach((key, value) -> {
                //multiply with size to avoid collisions of up to wads.size() items
                int pos = ((int) value.getValue()) * wads.size();
                while (accOrder.containsKey(pos)) {
                    pos++;
                }
                accOrder.put(pos,key);
            });
            AtomicInteger counter = new AtomicInteger(0);
            HashMap<Integer, String> returnMap = new HashMap<>();
            accOrder.entrySet()
                    .stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getKey))
                    .forEachOrdered(entry -> returnMap.put(counter.getAndIncrement(),entry.getValue().getPath()));
            loadOrder.complete(returnMap);
            //close the window
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });


        add(spinners, BorderLayout.CENTER);
        add(saveLoadOrderBtn, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,100 + 30 * wads.size());
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
