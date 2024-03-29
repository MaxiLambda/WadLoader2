package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.ChangeLoadOrderDialog;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.CreateWadPackDialog;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.exceptions.NoPackSelectedException;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.WadConfigFilterCheckBoxList;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadPackMapper;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.exceptions.WadPackNotFoundException;
import lincks.maximilian.wadloader2.ddd2application.search.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd2application.search.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd2application.search.query.WadQuery;
import lincks.maximilian.wadloader2.ddd2application.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackFactory;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;
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
public class WadPackConfigTab extends JPanel implements WadLoader2Tab{
    private final transient WadPackFactory wadPackFactory;
    private final transient WadPackQuery wadPackQuery;
    private final transient WadQuery wadQuery;
    private final transient WadPackMapper wadPackMapper;
    private final CheckboxList<WadDto> currentWads;
    private final CheckboxList<WadDto> allWads;
    private final CheckboxList<WadPackDto> wadPacks;
    private final transient WadPackReadWriteRepo wadPackRepo;

    @Getter(value = AccessLevel.PRIVATE)
    private transient Optional<WadPackDto> currentPack = Optional.empty();


    public WadPackConfigTab(WadPackFactory wadPackFactory, WadPackQuery wadPackQuery, WadQuery wadQuery, IWadQuery iWadQuery, WadPackMapper wadPackMapper, WadPackReadWriteRepo wadPackRepo) {
        this.wadPackFactory = wadPackFactory;
        this.wadPackQuery = wadPackQuery;
        this.wadQuery = wadQuery;
        this.wadPackMapper = wadPackMapper;
        this.wadPackRepo = wadPackRepo;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,3));
        JButton createNewWadPackBtn = new JButton(CREATE_NEW_WAD_PACK);

        allWads = WadConfigFilterCheckBoxList.of(List.of(), WADS, Map.of(ADD_WAD, addWad()),true);
        currentWads = WadConfigFilterCheckBoxList.of(List.of(), WADS_IN_PACK, Map.of( REMOVE_WAD, removeWad(), REMOVE_ALL_WADS, removeALlWads(),PERSIST_WAD_PACK, persistWadPack()),true);
        wadPacks = WadConfigFilterCheckBoxList.of(List.of(), WAD_PACKS, Map.of(EDIT, editWadPack(),DELETE_WAD_PACK, deleteWadPack()));

        createNewWadPackBtn.addActionListener(e -> CreateWadPackDialog
                .of(iWadQuery.getAll())
                .thenApply(base -> {
                    currentWads.setListName(base.name());
                    currentWads.clear();
                    return wadPackFactory.newPack(base);})
                .thenAccept(pack -> currentPack = Optional.of(pack))
        );

        panel.add(allWads);
        panel.add(currentWads);
        panel.add(wadPacks);

        add(panel,BorderLayout.CENTER);
        add(createNewWadPackBtn, BorderLayout.NORTH);
    }


    private Consumer<List<WadDto>> addWad(){
        return wads -> {
            allWads.unselectAll();
            if(currentPack.isEmpty()) return;
            currentWads.addAll(wads);
        };
    }

    private Consumer<List<WadDto>> removeWad(){
        //should always be present, otherwise there should be no currentWads to select from
        return wads -> wads.forEach(currentWads::remove);
    }

    private Consumer<List<WadDto>> removeALlWads(){
        return ignore -> currentWads.clear();
    }

    private Consumer<List<WadDto>> persistWadPack() {
        return wads -> {
            if(currentWads.getAll().isEmpty()) return;
            try {
                if(currentPack.isEmpty()) throw new NoPackSelectedException();
                Map<Integer, WadPath> order = new ChangeLoadOrderDialog(currentWads.getAll())
                        .getLoadOrder()
                        .get();

                WadPackDto currentDto = currentPack.get();
                WadPackDto changedDto = new WadPackDto(
                        currentDto.wadPackName(),
                        currentDto.iWad(),
                        currentDto.customTags(),
                        currentDto.wadPackTag(),
                        order);

                WadPack pack = getCurrentWadPack(changedDto);
                //set order again, because if the pack was already persisted the changes were not applied
                pack.setWads(order);

                wadPackFactory.persistWadPack(pack);
                currentPack = Optional.empty();
                updateData();
            } catch (InvalidWadPackConfigurationException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (ExecutionException | CancellationException e ) {
                JOptionPane.showMessageDialog(null, ABORTED_SAVE_WAD_PACK);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                JOptionPane.showMessageDialog(null, ABORTED_SAVE_WAD_PACK);
            } catch (NoPackSelectedException e) {
                JOptionPane.showMessageDialog(null, NO_PACK_SELECTED_ERROR);
            }
        };
    }

    private WadPack getCurrentWadPack(WadPackDto packDto){
        try{
            //fails if the pack does not exist
            return wadPackMapper.fromDto(packDto);
        }catch (WadPackNotFoundException e){
            //create the pack directly
            return new WadPack(packDto.wadPackName(),packDto.iWad(),wadPackRepo);
        }
    }

    private Consumer<List<WadPackDto>> deleteWadPack() {
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

    private Consumer<List<WadPackDto>> editWadPack(){
        //should only be called with a list containing exactly one parameter due to wadPacks being of type single selection
        return selectedWadPacks -> {
            assert selectedWadPacks.size() < 2;
            if(selectedWadPacks.isEmpty()) return;
            currentPack = Optional.of(selectedWadPacks.get(0));
            wadPacks.unselectAll();
            currentWads.clear();
            currentWads.addAll(currentPack.get()
                    .wads()
                    .values()
                    .stream()
                    .map(WadPath::getPath)
                    .map(wadQuery::getById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList());
            currentWads.setListName(currentPack.get().wadPackName().name);
            currentWads.revalidate();
            currentWads.repaint();
        };
    }

    @Override
    public void updateData() {
        allWads.clear();
        allWads.addAll(wadQuery.getAll());
        wadPacks.clear();
        wadPacks.addAll(wadPackQuery.getAll());
        currentWads.clear();
    }
}
