package lincks.maximilian.wadloader2.ddd0plugins.ui.tabs;

import lincks.maximilian.wadloader2.ddd2application.wadsearch.WadLoader;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import static lincks.maximilian.wadloader2.ddd0plugins.ui.UIConstants.*;
import static lincks.maximilian.wadloader2.ddd2application.game.Game.GZDOOM_HOME;

@Component
public class SettingConfigTab extends JPanel implements WadLoader2Tab {
    private final String gzdoomDir = Optional.ofNullable(System.getenv(GZDOOM_HOME)).orElse("");
    public SettingConfigTab(WadLoader wadLoader) {
        setLayout(new GridLayout(0,2));

        JLabel wadDir = new JLabel(WAD_DIR);
        JLabel iWadDir = new JLabel(I_WAD_DIR);
        JButton setWadDir = new JButton(SET_WAD_DIR);
        JButton setIWadDir = new JButton(SET_I_WAD_DIR);

        setWadDir.addActionListener(e -> {
            JFileChooser fileChooser = createJFileChooser(SET_WAD_DIR, gzdoomDir);
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                var paths = Arrays.stream(fileChooser.getSelectedFiles())
                        .map(File::getPath)
                        .map(Path::of)
                        .toList();
                wadLoader.loadWads(paths);
            }
        });

        setIWadDir.addActionListener(e -> {
            JFileChooser fileChooser = createJFileChooser(SET_I_WAD_DIR,gzdoomDir);
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                var paths = Arrays.stream(fileChooser.getSelectedFiles())
                        .map(File::getPath)
                        .map(Path::of)
                        .toList();
                wadLoader.loadIWads(paths);
            }
        });

        add(wadDir);
        add(setWadDir);
        add(iWadDir);
        add(setIWadDir);
    }

    private JFileChooser createJFileChooser(String dialogTitle, String path) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(path));
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }


    @Override
    public void updateData() {
        //do nothing because the UI doesn't depend on data mutated by other tabs
    }
    //TODO UI
    // * Load IWads from Directory
    // * Load Wads from Directory
}
