package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DefaultScraper implements Scraper {
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";

    @Override @SneakyThrows
    public Home scrape(String url) {
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc);
        int price = getPrice(doc);
        return new Home(price, 0, 0, 0);
    }

    private int getPrice(Document doc) {
        String price = doc.selectFirst(PRICE_SELECTOR).text().replaceAll("[^0-9]", "");
        return Integer.parseInt(price);
    }
}