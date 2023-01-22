package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.wadpackconfig.CreateWadPackDialog;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.CheckboxList;
import lincks.maximilian.wadloader2.ddd1adapter.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadPackQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadQuery;
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
import java.util.function.Consumer;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;

@Component
public class WadPackConfigTab extends JPanel {
    private final transient WadPackFactory wadPackFactory;
    private final transient WadPackQuery wadPackQuery;
    private final transient WadQuery wadQuery;
    private final transient IWadQuery iWadQuery;

    @Getter(value = AccessLevel.PRIVATE)
    private Optional<WadPack> currentPack = Optional.empty();

    public WadPackConfigTab(WadPackFactory wadPackFactory, WadPackQuery wadPackQuery, WadQuery wadQuery, IWadQuery iWadQuery) {
        this.wadPackFactory = wadPackFactory;
        this.wadPackQuery = wadPackQuery;
        this.wadQuery = wadQuery;
        this.iWadQuery = iWadQuery;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,3));
        JButton createNewWadPackBtn = new JButton(CREATE_NEW_WAD_PACK);

        //TODO add ActionListener/Button to Mutate currentWads according to the selected WadPack

        CheckboxList<Wad> allWads = new CheckboxList<>(wadQuery.getAll(), WADS, Map.of(ADD_WAD, addWad()),true);
        CheckboxList<Wad> currentWads = new CheckboxList<>(List.of(), WADS_IN_PACK, Map.of(),true);
        CheckboxList<WadPack> wadPacks = new CheckboxList<>(wadPackQuery.getAll(), WAD_PACKS, Map.of());

        createNewWadPackBtn.addActionListener(e -> CreateWadPackDialog
                .of(iWadQuery.getAll())
                .thenApply(base -> {
                    currentWads.setListName(base.name());
                    return wadPackFactory.newPack(base);})
                .thenApply(WadPack::allWadIds)
                .thenApply(wadQuery::getById)
                .thenAccept(wads -> {
                    currentWads.clear();
                    currentWads.addAll(wads);}));

        panel.add(allWads);
        panel.add(currentWads);
        panel.add(wadPacks);

        add(panel,BorderLayout.CENTER);
        add(createNewWadPackBtn, BorderLayout.NORTH);
    }


    private Consumer<List<Wad>> addWad(){
        return wads -> getCurrentPack().ifPresent(pack -> wads.forEach(pack::addWad));
    }
}
