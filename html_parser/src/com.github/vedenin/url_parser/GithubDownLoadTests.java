package com.github.vedenin.url_parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.Charset;

/**
 * Created by vedenin on 07.04.16.
 */
public class GithubDownLoadTests {
    private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    private static void initHTTPSDownload() throws Exception {
        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private static void testHtmlParser(String url) throws Exception {
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).cookie("auth", "token")
                .timeout(30000).get();
        Elements div = doc.select("#readme").select("ol").select("li");

        printElements(div);
    }

    private static void printElements(Elements children) {
        for(Element child: children) {
            if(!child.text().isEmpty()) {
                System.out.print(child.tag().getName() + " : ");
                System.out.println(child.text());
            }
            printElements(child.children());
        }
    }

    public static void main(String[] s) throws Exception {
        initHTTPSDownload();
        testHtmlParser("https://github.com/Vedenin/useful-java-links/blob/master/readme.md");
    }
}
