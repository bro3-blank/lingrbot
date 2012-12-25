package m17.putei.lingrbot.infra;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.api.server.spi.IoUtil;

public class NetworkAPI {

  public static String getContentFromURL( String url ) {
    try {
      return getContentFromURL(new URL(url));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static String getContentFromURL( URL url ) {
    try {
      return IoUtil.readStream(url.openStream());
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  public static void sendMessageByPOST( String url, Map<String,String> params ) {
    try {
      String charset = "UTF-8";
      URL u = new URL(url);
      String query = getQuery( params );
      HttpURLConnection connection = (HttpURLConnection) u.openConnection();
      connection.setDoOutput(true);
      connection.setRequestProperty("Accept-Charset", charset);
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
      connection.setRequestMethod("POST");
      connection.getOutputStream().write( query.getBytes() );
      connection.getResponseCode();      //Fires when res code returned
      connection.disconnect();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void sendMessageByGET( String url, Map<String,String> params ) {
    try {
      String charset = "UTF-8";
      String query = getQuery( params );
      URL u = new URL(url+"?"+query);
      HttpURLConnection connection = (HttpURLConnection) u.openConnection();
      connection.setRequestProperty("Accept-Charset", charset);
      connection.setRequestMethod("GET"); 
      connection.getResponseCode();      //Fires when res code returned
      connection.disconnect();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getQuery( Map<String,String> params ) {
    String charset = "UTF-8";
    StringBuilder sb = new StringBuilder();
    try {
      for (String key : params.keySet()) {
        sb.append(sb.length() > 0 ? "&" : "");
        sb.append(key + "=" + URLEncoder.encode(params.get(key), charset));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
  
}
