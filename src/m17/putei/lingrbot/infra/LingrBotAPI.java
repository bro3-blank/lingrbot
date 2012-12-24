package m17.putei.lingrbot.infra;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import m17.putei.lingrbot.Robot;
import m17.putei.lingrbot.RobotFactory;
/**
 * 任意のタイミングでボットにつぶやかせるAPI
 * 
 * @author blank
 *
 */
public class LingrBotAPI {
  /**
   * 長いメッセージを15行ごとに区切って投稿
   * @param roomId
   * @param botId
   * @param verifier
   * @param message
   */
  public static void safePostMessage( String roomId, String botId, String verifier, String message ) {
    String[] lines = message.split("\\n");
    safePostMessage( roomId, botId, verifier, lines );
  }
  
  public static void safePostMessage( String roomId, String botId, String verifier, String[] lines ) {
    StringBuilder fragment = new StringBuilder();
    for ( int i=0; i<lines.length; i++ ) {
      fragment.append( lines[i]+"\n" );
      if (i%15==14) {
        sendMessageByPOST( roomId, botId, verifier, fragment.toString() );
        fragment = new StringBuilder();
      }
    }
    if (fragment.length()>0) sendMessageByPOST( roomId, botId, verifier, fragment.toString() );
  }
  
  public static void sendMessageByPOST( String roomId, String botId, String verifier, String message ) {
    try {
      String charset = "UTF-8";
      String query = String.format("room=%s&bot=%s&bot_verifier=%s&text=%s",
        URLEncoder.encode(roomId, charset), 
        URLEncoder.encode(botId, charset),
        URLEncoder.encode(verifier, charset),
        URLEncoder.encode(message, charset));
      URL url = new URL("http://lingr.com/api/room/say");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
  
  public static void sendMessageByGET( String roomId, String botId, String verifier, String message ) {
    try {
      String charset = "UTF-8";
      String query = String.format("room=%s&bot=%s&bot_verifier=%s&text=%s",
              URLEncoder.encode(roomId, charset), 
              URLEncoder.encode(botId, charset),
              URLEncoder.encode(verifier, charset),
              URLEncoder.encode(message, charset));
      URL url = new URL("http://lingr.com/api/room/say?"+query);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
  
  public static void main(String[] args) {
    String s = "ID=4079 | 水鏡 UR 1.0 | 歩 | 攻50 知17 防90-75-75-75 移9 | 水鏡鑑定Lv.1\n";
    Robot bot = RobotFactory.createMekaDaitoku();
    LingrBotAPI.safePostMessage(bot.getRoomId(), bot.getBotId(), bot.getVerifier(), s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s);
  }

}
