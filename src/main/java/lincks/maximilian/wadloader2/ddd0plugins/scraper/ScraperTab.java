package lincks.maximilian.wadloader2.ddd0plugins.scraper;

import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.WadLoader2Tab;
import lincks.maximilian.wadloader2.ddd0plugins.ui.utility.OneActionDocumentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ScraperTab extends JPanel implements WadLoader2Tab {

    private CompletableFuture<List<ModDbScraper.WadFragmentData>> scrapingFuture;

    public ScraperTab(ModDbScraper modDbScraper) {
        //initialize with empty list to avoid null checks
        this.scrapingFuture = CompletableFuture.completedFuture(List.of());

        setLayout(new BorderLayout());

        //the textfield used for searching
        var textField = new JTextField();

        //panel used to display the scraped information
        JPanel modPanel = new JPanel();
        modPanel.setLayout(new GridLayout(0, 1));
        modPanel.add(new JLabel("Search for something on ModDB"));

        textField.getDocument().addDocumentListener(OneActionDocumentListener.of(
                (_ -> {
                    scrapingFuture.cancel(true);
                    scrapingFuture = CompletableFuture.supplyAsync(() -> modDbScraper.scrape(textField.getText()));
                    scrapingFuture.thenAccept(data -> {
                        modPanel.removeAll();
                        if(data.isEmpty()) {
                            JTextArea emptyText = new JTextArea("No results found for:\n\t" + textField.getText());
                            emptyText.setEditable(false);
                            emptyText.setLineWrap(true);
                            modPanel.add(emptyText);
                        } else {
                            JTextArea resultsInfo = new JTextArea("Showing "+data.size()+" results for:\n\t" + textField.getText());
                            resultsInfo.setEditable(false);
                            resultsInfo.setLineWrap(true);
                            modPanel.add(resultsInfo);
                            data.forEach(mod -> modPanel.add(scrapeHit(mod)));
                        }
                        modPanel.revalidate();
                        modPanel.repaint();
                    });
                })));

        add(textField, BorderLayout.NORTH);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(0,1,0,0));

        JScrollPane scrollPane = new JScrollPane(modPanel);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        wrapper.add(scrollPane);

        add(wrapper, BorderLayout.CENTER);
    }

    public JPanel scrapeHit(ModDbScraper.WadFragmentData data) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        URI uri = URI.create(data.url());

        JLabel title = new JLabel("<HTML><u>" + data.title() + "</u></HTML>");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        title.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                   Runtime.getRuntime().exec("cmd /C start " + uri);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(title, BorderLayout.NORTH);

        JTextArea description = new JTextArea(data.descriptionSnipped());
        description.setEditable(false);
        description.setLineWrap(true);
        panel.add(description, BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void updateData() {

    }
}
