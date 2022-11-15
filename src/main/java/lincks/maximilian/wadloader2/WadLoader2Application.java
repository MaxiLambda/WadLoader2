package lincks.maximilian.wadloader2;

import lincks.maximilian.wadloader2.domain.WadFileFinder;
import lincks.maximilian.wadloader2.model.tags.Tag;
import lincks.maximilian.wadloader2.model.wads.IWad;
import lincks.maximilian.wadloader2.model.wads.Wad;
import lincks.maximilian.wadloader2.repos.services.IWadService;
import lincks.maximilian.wadloader2.repos.services.WadService;
import lincks.maximilian.wadloader2.ui.UIBase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

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
	final WadService wadService;
	final IWadService iWadService;

	@EventListener(ApplicationReadyEvent.class)
	public void appStartup(){

		UIBase ui = new UIBase();
		ui.initUI();
		ui.setVisible(true);

//		wadFinder.findWads(Path.of("D:\\GZDoom\\wads"))
//				.stream()
//				.map(wadService::save)
//				.map(Wad::getWadTag)
//				.map(Tag::tagName)
//				.forEach(System.out::println);
//
//		wadFinder.findIWads(Path.of("D:\\GZDoom\\wads\\iwads"))
//				.stream()
//				.map(iWadService::save)
//				.map(IWad::getWadTag)
//				.map(Tag::tagName)
//				.forEach(System.out::println);
	}
}
