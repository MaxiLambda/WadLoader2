package lincks.maximilian.wadloader2;

import lincks.maximilian.wadloader2.domain.WadFileFinder;
import lincks.maximilian.wadloader2.repos.services.WadService;
import lincks.maximilian.wadloader2.ui.UIBase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

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

		UIBase ui = new UIBase();


		EventQueue.invokeLater(() -> {
			ui.initUI();
			ui.setVisible(true);
		});
	}

	final WadFileFinder wadFinder;
	final WadService wadService;


//	@EventListener(ApplicationReadyEvent.class)
//	public void appStartup(){
//		wadFinder.findWads(Path.of("D:\\GZDoom\\wads"))
//				.stream()
//				.map(wadService::save)
//				.map(Wad::getWadTag).map(WadTag::tagName)
//				.forEach(System.out::println);
//	}
}
