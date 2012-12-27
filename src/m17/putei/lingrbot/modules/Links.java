package m17.putei.lingrbot.modules;

import java.util.Map;

import m17.putei.lingrbot.Utils;

public class Links {

  private final static String daitokuRooms = "blank_bot_test2|daitoku9ki";
  private final static Map<String,String> links = Utils.readKeyValue("links.txt");
  
  public String query( String key, String roomId ) {
    if (key.equals("管理シート") && !roomId.matches(daitokuRooms)) return "http:// .... 幹部にお問い合わせください(´・ω・`)";
    if (key.equals("ソロシート")  && !roomId.matches(daitokuRooms)) return "http:// .... 幹部にお問い合わせください(´・ω・`)";
    return links.get(key).replaceAll("<br>", "\n");
  }
  
  public String linkKeys(String roomId) {
    StringBuilder sb = new StringBuilder();
    for ( String key : links.keySet() ) {
      if (key.equals("管理シート") && !roomId.matches(daitokuRooms)) continue; 
      if (key.equals("ソロシート")  && !roomId.matches(daitokuRooms)) continue; 
      sb.append( "「"+key+"」 " );
    }
    return sb.toString();
  }
  
  public String sheetLinkKeys(String roomId) {
    StringBuilder sb = new StringBuilder();
    for ( String key : links.keySet() ) {
      if (!key.contains("シート")) continue;
      if (key.equals("管理シート") && !roomId.matches("blank2|blank3")) continue; 
      if (key.equals("ソロシート") && !roomId.matches("blank2|blank3")) continue; 
      sb.append( "「"+key+"」 " );
    }
    return sb.toString();
  }
}
