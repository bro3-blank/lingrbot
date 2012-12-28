package m17.putei.lingrbot.modules;

import java.util.LinkedHashMap;
import java.util.Map;

import m17.putei.lingrbot.infra.NetworkAPI;

public class Links {

  private final static String daitokuRooms = "blank_bot_test2|daitoku9ki";
  private final static String gasURL = "https://script.google.com/macros/s/AKfycbxduLxk12gelhoyxv8of5ZtikXVyDr0eS7t4Jd0cVdKlEMG_JZW/exec";
  private Map<String,String> links;
  
  public Links() {
//    links = Utils.readKeyValue("links.txt");
    init();
  }
  
  public void init() {
    links = loadFromSheet();
  }
  
  private synchronized Map<String,String> loadFromSheet() {
//    links = Utils.readKeyValue("links.txt");
    links = new LinkedHashMap<String,String>();
    String content = NetworkAPI.getContentFromURL(gasURL);
    String[] lines = content.split("\t");
    for ( String line : lines ) {
      String[] items = line.split(",");
      if ( items.length==2 ) {
        links.put(items[0].trim(), items[1].trim());
      }
    }
    return links;
  }
  
  public synchronized String query( String key, String roomId ) {
    if (key.equals("管理シート") && !roomId.matches(daitokuRooms)) return "http:// .... 幹部にお問い合わせください(´・ω・`)";
    if (key.equals("ソロシート")  && !roomId.matches(daitokuRooms)) return "http:// .... 幹部にお問い合わせください(´・ω・`)";
    String v = links.get(key);
    if (v!=null) v = v.replaceAll("<br>", "\n");
    return v;
  }
  
  public synchronized String linkKeys(String roomId) {
    StringBuilder sb = new StringBuilder();
    for ( String key : links.keySet() ) {
      if (key.equals("管理シート") && !roomId.matches(daitokuRooms)) continue; 
      if (key.equals("ソロシート")  && !roomId.matches(daitokuRooms)) continue; 
      sb.append( "「"+key+"」 " );
    }
    return sb.toString();
  }
  
  public synchronized String sheetLinkKeys(String roomId) {
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
