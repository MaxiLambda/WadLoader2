package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.ChangeLoadOrderDialog;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.CreateWadPackDialog;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.exceptions.NoPackSelectedException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd1adapter.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadQuery;
import lincks.maximilian.wadloader2.ddd2application.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackFactory;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

@Component
public class WadPackConfigTab extends JPanel {
    private final transient WadPackFactory wadPackFactory;
    private final transient WadPackQuery wadPackQuery;
    private final transient WadQuery wadQuery;

    private final CheckboxList<Wad> currentWads;
    private final CheckboxList<Wad> allWads;
    private final CheckboxList<WadPack> wadPacks;

    @Getter(value = AccessLevel.PRIVATE)
    private transient Optional<WadPack> currentPack = Optional.empty();

    public WadPackConfigTab(WadPackFactory wadPackFactory, WadPackQuery wadPackQuery, WadQuery wadQuery, IWadQuery iWadQuery) {
        this.wadPackFactory = wadPackFactory;
        this.wadPackQuery = wadPackQuery;
        this.wadQuery = wadQuery;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,3));
        JButton createNewWadPackBtn = new JButton(CREATE_NEW_WAD_PACK);
        //TODO create extra Class for each wad/iwad/pack CheckboxList
        allWads = new CheckboxList<>(wadQuery.getAll(), WADS, Map.of(ADD_WAD, addWad()),true);
        currentWads = new CheckboxList<>(List.of(), WADS_IN_PACK, Map.of( REMOVE_WAD, removeWad(), REMOVE_ALL_WADS, removeALlWads(),PERSIST_WAD_PACK, persistWadPack()),true);
        wadPacks = new CheckboxList<>(wadPackQuery.getAll(), WAD_PACKS, Map.of(EDIT, editWadPack(),DELETE_WAD_PACK, deleteWadPack()));

        createNewWadPackBtn.addActionListener(e -> CreateWadPackDialog
                .of(iWadQuery.getAll())
                .thenApply(base -> {
                    currentWads.setListName(base.name());
                    return wadPackFactory.newPack(base);})
                .thenAccept(pack -> currentPack = Optional.of(pack))
        );

        panel.add(allWads);
        panel.add(currentWads);
        panel.add(wadPacks);

        add(panel,BorderLayout.CENTER);
        add(createNewWadPackBtn, BorderLayout.NORTH);
    }


    private Consumer<List<Wad>> addWad(){
        return wads -> {
            if(currentPack.isEmpty()) return;
            currentWads.addAll(wads);
        };
    }

    private Consumer<List<Wad>> removeWad(){
        //should always be present, otherwise there should be no currentWads to select from
        return wads -> wads.forEach(currentWads::remove);
    }

    private Consumer<List<Wad>> removeALlWads(){
        return ignore -> currentWads.clear();
    }

    private Consumer<List<Wad>> persistWadPack() {
        return wads -> {
            if(currentWads.getAll().isEmpty()) return;
            try {
                if(currentPack.isEmpty()) throw new NoPackSelectedException();
                Map<Integer, String> order = new ChangeLoadOrderDialog(currentWads.getAll()).getLoadOrder().get();
                WadPack pack = currentPack.get();
                pack.setWads(order);
                wadPackFactory.persistWadPack(pack);
                wadPacks.put(currentPack.get());
            } catch (InvalidWadPackConfigurationException e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
            } catch (ExecutionException | InterruptedException | CancellationException e ) {
                JOptionPane.showConfirmDialog(null, ABORTED_SAVE_WAD_PACK);
            } catch (NoPackSelectedException e) {
                JOptionPane.showConfirmDialog(null, NO_PACK_SELECTED_ERROR);
            }
        };
    }

    private Consumer<List<WadPack>> deleteWadPack() {
        //should only be called with a list containing exactly one parameter due to wadPacks being of type single selection
        return selectedWadPacks -> {
            assert selectedWadPacks.size() < 2;
            if(selectedWadPacks.isEmpty()) return;
            int dialogResult = JOptionPane.showOptionDialog(
                    null,
                    DELETE_WAD_PACK_CONFIRM.formatted(selectedWadPacks.get(0)),
                    DELETE_CONFIRM_TITLE,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,null,null);
            if(dialogResult != JOptionPane.YES_OPTION) return;

            wadPackQuery.delete(selectedWadPacks.get(0));
            wadPacks.remove(selectedWadPacks.get(0));
            currentWads.clear();
        };
    }

    private Consumer<List<WadPack>> editWadPack(){
        //should only be called with a list containing exactly one parameter due to wadPacks being of type single selection
        return selectedWadPacks -> {
            assert selectedWadPacks.size() < 2;
            if(selectedWadPacks.isEmpty()) return;
            currentPack = Optional.of(selectedWadPacks.get(0));
            currentWads.clear();
            currentWads.addAll(currentPack.get()
                    .getWads()
                    .values()
                    .stream()
                    .map(wadQuery::getById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList());
            currentWads.repaint();
        };
    }
}
