package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.exceptions.NoIwadExistsException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackBase;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.CREATE_NEW_WAD_PACK;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.SAVE_BTN;

public class CreateWadPackDialog extends JDialog {

    private final transient CompletableFuture<WadPackBase> wadPackFuture;
    private CreateWadPackDialog(List<IWad> iWadList) {
        if(iWadList.isEmpty()) throw new NoIwadExistsException();

        setTitle(CREATE_NEW_WAD_PACK);
        setLayout(new GridLayout(0,1));
        setModal(true);

        wadPackFuture = new CompletableFuture<>();
        JTextField wadNameFieled = new JTextField();
        JComboBox<IWad> iWadJComboBox = new JComboBox<>(iWadList.toArray(new IWad[0]));
        JButton saveBtn = new JButton(SAVE_BTN);

        iWadJComboBox.setSelectedIndex(0);

        saveBtn.addActionListener(e -> {
            String name = wadNameFieled.getText();
            if( name != null && !name.isEmpty()){
                wadPackFuture.complete(new WadPackBase(name, (IWad) iWadJComboBox.getSelectedItem()));
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                wadPackFuture.cancel(true);
            }
        });

        add(wadNameFieled);
        add(iWadJComboBox);
        add(saveBtn);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static CompletableFuture<WadPackBase> of(List<IWad> iWadList){
        return new CreateWadPackDialog(iWadList).wadPackFuture;
    }
}
