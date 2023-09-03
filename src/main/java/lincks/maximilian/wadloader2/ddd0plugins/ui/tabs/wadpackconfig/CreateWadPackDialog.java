package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.exceptions.NoIwadExistsException;
import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.CREATE_NEW_WAD_PACK;
import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.SAVE_BTN;

public class CreateWadPackDialog extends JDialog {

    private final transient CompletableFuture<WadPackBase> wadPackRuleFuture;
    private CreateWadPackDialog(List<IWadDto> iWadList) {
        if(iWadList.isEmpty()) throw new NoIwadExistsException();

        setTitle(CREATE_NEW_WAD_PACK);
        setLayout(new GridLayout(0,1));
        setModal(true);

        wadPackRuleFuture = new CompletableFuture<>();
        JTextField wadNameField = new JTextField();
        JComboBox<IWadDto> iWadJComboBox = new JComboBox<>(iWadList.toArray(new IWadDto[0]));
        JButton saveBtn = new JButton(SAVE_BTN);

        iWadJComboBox.setSelectedIndex(0);

        saveBtn.addActionListener(e -> {
            String name = wadNameField.getText();
            if( name != null && !name.isEmpty()){
                wadPackRuleFuture.complete(new WadPackBase(name, (IWadDto) iWadJComboBox.getSelectedItem()));
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                wadPackRuleFuture.cancel(true);
            }
        });

        add(wadNameField);
        add(iWadJComboBox);
        add(saveBtn);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static CompletableFuture<WadPackBase> of(List<IWadDto> iWadList){
        return new CreateWadPackDialog(iWadList).wadPackRuleFuture;
    }
}
