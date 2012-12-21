package m17.putei.lingrbot.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.Utils;

public class BushoAPI {

  String filename = "bushoData.html";
  
//  private final static Pattern p = Pattern.compile(
//          "^<tr><td>(\\d{4})</td><td>(.+?)</td><td>(.+?)</td><td align=\"center\">(.+?)</td><td align=\"center\">槍</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td>(.+?)</td><td></td><td></td></tr>");
  private final static Pattern p = Pattern.compile("^<tr><td>(\\d{4})</td>");

  private Busho[] bushoDB;
  
  public BushoAPI() {
    bushoDB = load();
  }
  
  public Busho[] load() {
    List<String> lines = Utils.readLines( filename );
    List<Busho> data = new ArrayList<Busho>();
    for ( String line : lines ) {
      Matcher m = p.matcher(line);
      if ( m.find() ) {
        String[] cols = line.replaceAll("><", "").split("<.+?>");
        data.add(new Busho(cols));
      }
    }
    return (Busho[]) data.toArray(new Busho[data.size()]);
  }
  
  public String lookup( String name, String rarity ) {
    return null;
  }
  
  public static void main(String[] args) {
    new BushoAPI().load();
  }
  
}
