package lincks.maximilian.wadloader2;


import lincks.maximilian.wadloader2.ddd0plugins.ui.UIBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;

@SpringBootApplication
@RequiredArgsConstructor
@Log
public class WadLoader2Application{
    public static void main(String[] args) {
        new SpringApplicationBuilder(WadLoader2Application.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
    
    final UIBase uiBase;
    @EventListener(ApplicationReadyEvent.class)
    public void appStartup(){

        EventQueue.invokeLater(uiBase::initUI);
    }
}
