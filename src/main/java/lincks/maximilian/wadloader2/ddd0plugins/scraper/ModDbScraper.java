package lincks.maximilian.wadloader2.ddd0plugins.scraper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil.falseOnError;
import static lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil.filterMap;

@RequiredArgsConstructor
public class ModDbScraper {

    @Getter
    private final String baseUrl = "https://www.moddb.com";
    private final String queryPathAndQuery = "/games/doom-ii/mods?filter=t&kw=%s&released=&genre=&theme=&players=1&timeframe=&rtx=";


    public List<WadFragmentData> scrape(String queryText) {
        try {
            String secureText = URLEncoder.encode(queryText, StandardCharsets.UTF_8);
            Document doc = Jsoup.connect(baseUrl + queryPathAndQuery.formatted(secureText)).get();

            /* The following shows how moddb search hits look like (09.11.2025):
             *
             * <div class="content">
             *  <h4><a href="/mods/ozonia-boom-megawad">Ozonia Boom Megawad</a></h4>
             *  <span class="date"> <time datetime="2021-12-23T09:57:59+00:00">Dec 23 2021</time> </span> <span class="subheading"> <time datetime="2021-12-23">Released 2021</time> First Person Shooter </span>
             *  <p>Ozonia is the third mapset of the Moonblood series, containing 32 carefully designed short maps with compatibility level 9. It's my most ambitious project...</p>
             * </div>
             *
             */

            //get all fragments <dic class="content">...</div>
            var contentUnfiltered = doc.select("div.content");

            //keep all fragments where the first child is a h4 tag
            var wadFragments = contentUnfiltered.stream()
                    .filter(falseOnError(e -> e.children().getFirst().is("h4")));

            return wadFragments.gather(filterMap(frag -> WadFragmentData.fromFragment(frag, baseUrl)))
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public record WadFragmentData(String title, String url, String descriptionSnipped) {

        static Optional<WadFragmentData> fromFragment(Element fragment, String baseUrl) {
            /* The following shows how moddb search hits look like (09.11.2025):
             *
             * <div class="content">
             *  <h4><a href="/mods/ozonia-boom-megawad">Ozonia Boom Megawad</a></h4>
             *  <span class="date"> <time datetime="2021-12-23T09:57:59+00:00">Dec 23 2021</time> </span> <span class="subheading"> <time datetime="2021-12-23">Released 2021</time> First Person Shooter </span>
             *  <p>Ozonia is the third mapset of the Moonblood series, containing 32 carefully designed short maps with compatibility level 9. It's my most ambitious project...</p>
             * </div>
             *
             */
            var href = fragment.selectFirst("a");
            if (href == null) return Optional.empty();
            var title = href.text();
            var url = baseUrl + href.attr("href");

            var descriptionParagraph = fragment.selectFirst("p");
            if (descriptionParagraph == null) return Optional.empty();
            var descriptionSnipped = descriptionParagraph.text();

            return Optional.of(new WadFragmentData(title, url, descriptionSnipped));
        }
    }

}


