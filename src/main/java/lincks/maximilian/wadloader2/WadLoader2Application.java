package lincks.maximilian.wadloader2;

import lincks.maximilian.wadloader2.application2.wadsearch.WadLoader;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.IWad;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.plugins0.ui.UIBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


import java.awt.*;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Log
public class WadLoader2Application{

    //TODO: build fitting ui

    public static void main(String[] args) {
        new SpringApplicationBuilder(WadLoader2Application.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    final WadLoader wadLoader;

    @EventListener(ApplicationReadyEvent.class)
    public void appStartup(){

        EventQueue.invokeLater(() -> {
            UIBase ui = new UIBase();
            ui.initUI();
            ui.setVisible(true);
        });

		wadLoader.loadWads(List.of(Path.of("D:\\GZDoom\\wads")))
                .stream()
				.map(Wad::getWadTag)
				.map(Tag::tagName)
				.forEach(log::info);

		wadLoader.loadIWads(List.of(Path.of("D:\\GZDoom\\wads\\iwads")))
				.stream()
				.map(IWad::getWadTag)
				.map(Tag::tagName)
				.forEach(log::info);
    }
}
