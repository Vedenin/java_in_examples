package github.vedenin.url_parser.rus;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * ???????????? ????????????? ?????? ? Java
 *
 * Created by vvedenin on 2/6/2016.
 */
public class URLDownloadWithProxyTests {

    private static void testSystemPropertyToSetProxy() throws Exception {
        //Set the http proxy to webcache.mydomain.com:8080

        System.setProperty("http.proxyHost", "104.236.222.191"); // ????? ??????
        System.setProperty("http.proxyPort", "3128"); // ???? ??????

        // Next connection will be through proxy.
        URL url = new URL("http://www.oracle.com/technetwork/java/index.html");
        InputStream in = url.openStream();

        String s = IOUtils.toString(in, StandardCharsets.UTF_8);
        System.out.println(s.length());
        System.out.println(StringUtils.countMatches(s, "java"));
        // Now, let's 'unset' the proxy.
        System.setProperty("http.proxyHost", "");

        // From now on http connections will be done directly.
    }

    public static void main(String[] args) throws Exception {
        testSystemPropertyToSetProxy();
    }
}
