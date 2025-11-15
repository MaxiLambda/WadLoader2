package lincks.maximilian.wadloader2.ddd0plugins.scraper;

import lincks.maximilian.wadloader2.ddd0plugins.ui.plugin.Plugin;
import lincks.maximilian.wadloader2.ddd0plugins.ui.tabs.WadLoader2Tab;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ScraperPlugin implements Plugin<ScraperTab> {

    @Override
    public String getTitle() {
        return "ModDB Search";
    }

    @Override
    public String getToolTip() {
        return "Search for mods on ModDB";
    }

    @Override
    public ScraperTab getWadLoader2Tab() {
        return new ScraperTab(new ModDbScraper());
    }
}
