package github.vedenin.url_parser.eng;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Testing using proxy in Java
 *
 * Created by vvedenin on 2/6/2016.
 */
public class URLDownloadWithProxyTests {

    private static void testSystemPropertyToSetProxy(String urlString, String proxyUrl, String proxyPort) throws Exception {
        //Set the http proxy to webcache.mydomain.com:8080

        System.setProperty("http.proxyHost", proxyUrl); // proxy url
        System.setProperty("http.proxyPort", proxyPort); // proxy port

        // Next connection will be through proxy.
        URL url = new URL(urlString);
        InputStream in = url.openStream();

        String s = IOUtils.toString(in, StandardCharsets.UTF_8);
        System.out.println(s.length());
        System.out.println(StringUtils.countMatches(s, "java"));
        // Now, let's 'unset' the proxy.
        System.setProperty("http.proxyHost", "");

        // From now on http connections will be done directly.
    }

    private static void testProxyClass(String urlString, String proxyUrl, int proxyPort) throws Exception {
        SocketAddress addr = new InetSocketAddress(proxyUrl, proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection(proxy);
        InputStream in = conn.getInputStream();
        String s = IOUtils.toString(in, StandardCharsets.UTF_8);
        System.out.println(s.length());
        System.out.println(StringUtils.countMatches(s, "java"));

    }

    public static void main(String[] args) throws Exception {
        testSystemPropertyToSetProxy("http://www.oracle.com/technetwork/java/index.html", "104.236.222.191", "3128");
        testProxyClass("http://www.oracle.com/technetwork/java/index.html", "104.236.222.191", 3128);
    }
}
