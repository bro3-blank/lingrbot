package m17.putei.lingrbot.infra;

import java.util.LinkedHashMap;
import java.util.Map;

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
        sendMessage( roomId, botId, verifier, fragment.toString() );
        fragment = new StringBuilder();
      }
    }
    if (fragment.length()>0) sendMessage( roomId, botId, verifier, fragment.toString() );
  }
  
  public static void sendMessage( String roomId, String botId, String verifier, String message ) {
    Map<String,String> params = new LinkedHashMap<String,String>();
    params.put("room", roomId);
    params.put("bot", botId);
    params.put("bot_verifier", verifier);
    params.put("text", message);
    String url = "http://lingr.com/api/room/say";
    NetworkAPI.sendMessageByPOST(url, params);
  }
  
  public static void main(String[] args) {
    String s = "ID=4079 | 水鏡 UR 1.0 | 歩 | 攻50 知17 防90-75-75-75 移9 | 水鏡鑑定Lv.1\n";
    Robot bot = RobotFactory.createMekaDaitoku();
    LingrBotAPI.safePostMessage(bot.getRoomId(), bot.getBotId(), bot.getVerifier(), s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s+s);
  }

}
