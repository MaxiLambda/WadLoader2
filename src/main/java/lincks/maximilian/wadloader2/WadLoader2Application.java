package lincks.maximilian.wadloader2;

import lincks.maximilian.wadloader2.domain3.WadFileFinder;
import lincks.maximilian.wadloader2.domain3.repos.IWadRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadPackRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadPackTagRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.Tag;
import lincks.maximilian.wadloader2.domain3.wads.IWad;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.plugins0.ui.UIBase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.nio.file.Path;

@SpringBootApplication
@RequiredArgsConstructor
public class WadLoader2Application{

    //TODO: build fitting ui

    public static void main(String[] args) {
        new SpringApplicationBuilder(WadLoader2Application.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    final WadFileFinder wadFinder;
    final WadRepo wadService;
    final IWadRepo iWadService;

    final WadPackRepo wadPackService;

    final WadPackTagRepo wadPackTagService;

    @EventListener(ApplicationReadyEvent.class)
    public void appStartup(){

        EventQueue.invokeLater(() -> {
            UIBase ui = new UIBase();
            ui.initUI();
            ui.setVisible(true);
        });

		wadFinder.findWads(Path.of("D:\\GZDoom\\wads"))
				.stream()
				.map(wadService::save)
				.map(Wad::getWadTag)
				.map(Tag::tagName)
				.forEach(System.out::println);

		wadFinder.findIWads(Path.of("D:\\GZDoom\\wads\\iwads"))
				.stream()
				.map(iWadService::save)
				.map(IWad::getWadTag)
				.map(Tag::tagName)
				.forEach(System.out::println);
    }
}
