package lincks.maximilian.wadloader2.ddd0plugins.config;

import lincks.maximilian.wadloader2.ddd2application.wadsearch.AllowedWadExtensions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@ConfigurationProperties("wads.extensions")
public class WadExtensionConfiguration implements AllowedWadExtensions {

    private List<String> allowed;
}
