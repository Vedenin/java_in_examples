package github.vedenin.url_parser.rus;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * Загрузка вебстраниц с использованием ProxySelector
 *
 * Created by vvedenin on 2/6/2016.
 */
public class URLDownloadWithProxySelector {
    private static class TestProxySelector extends ProxySelector {

        @Override
        public List<Proxy> select(URI uri) {
            return Collections.singletonList(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("104.236.222.191", 3128)));
        }

        @Override
        public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
            System.out.println("connectFailed " + uri + ", sa = " + sa + ", ioe = " + ioe);
        }
    }

    public static void main(String[] args) throws Exception {
        TestProxySelector ps = new TestProxySelector();
        ProxySelector.setDefault(ps);
        URL url = new URL("http://www.oracle.com/technetwork/java/index.html");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println(conn.getResponseMessage());
        }
        else{
            InputStream in = conn.getInputStream();
            String s = IOUtils.toString(in, StandardCharsets.UTF_8);
            System.out.println(s.length());
            System.out.println(StringUtils.countMatches(s, "java"));
        }
    }
}
