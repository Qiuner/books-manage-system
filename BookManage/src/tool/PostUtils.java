package tool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class PostUtils {
    /* 发送一个post请求 */
    public static void toPost(String url) throws IOException {
        URL postUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();
        System.out.println("请求返回: " + responseCode);
        connection.disconnect();
    }

}
