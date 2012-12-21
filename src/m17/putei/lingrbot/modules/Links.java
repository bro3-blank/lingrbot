package m17.putei.lingrbot.modules;

import java.util.Map;

import m17.putei.lingrbot.Utils;

public class Links {

  private final static Map<String,String> links = Utils.readKeyValue("links.txt");
  
  public String query( String key ) {
    return links.get(key);
  }
  
  public String keys() {
    StringBuilder sb = new StringBuilder();
    for ( String key : links.keySet() ) {
      sb.append( "「"+key+"」 " );
    }
    return sb.toString();
  }
}
