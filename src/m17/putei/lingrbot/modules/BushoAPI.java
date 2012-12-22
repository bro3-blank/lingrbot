package m17.putei.lingrbot.modules;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.Utils;

public class BushoAPI {

  public final static Pattern pBusho = Pattern.compile("\\p{InCJKUnifiedIdeographs}+");
  private final static Pattern pRarity = Pattern.compile("[CUSR]+", Pattern.CASE_INSENSITIVE);
  private final static Pattern pCost = Pattern.compile("[0-9.]+");
  
  private final static String filename = "bushoData.html";
  
//  private final static Pattern p = Pattern.compile(
//          "^<tr><td>(\\d{4})</td><td>(.+?)</td><td>(.+?)</td><td align=\"center\">(.+?)</td><td align=\"center\">槍</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td align=\"right\">(.+?)</td><td>(.+?)</td><td></td><td></td></tr>");
  private final static Pattern p = Pattern.compile("^<tr><td>(\\d{4})</td>");

  private Map<String,List<Busho>> bushoDB;
  
  public BushoAPI() {
    bushoDB = load();
  }
  
  public Map<String,List<Busho>> load() {
    Map<String,List<Busho>> db = new LinkedHashMap<String,List<Busho>>();
    List<String> lines = Utils.readLines( filename );
    for ( String line : lines ) {
      Matcher m = p.matcher(line);
      if ( m.find() ) {
        String[] cols = line.replaceAll("><", "").split("<.+?>");
        String name = cols[2];
        List<Busho> bushos = db.get( name );
        if ( bushos == null ) bushos = new ArrayList<Busho>();
        bushos.add( new Busho(cols) );
        db.put( name, bushos );
      }
    }
    return db;
  }
  
  public List<Busho> getBusho( String name ) {
    List<Busho> bushos = bushoDB.get(name);
    return bushos;
  }
  
  public List<Busho> getBusho( String arg, String name ) {
    arg =  Utils.toHankaku(arg);
    List<Busho> bushos = getBusho( name );
    if (bushos==null) return null;
    Matcher mRarity = pRarity.matcher(arg);
    if (mRarity.find()) {
      List<Busho> filteredBushos = new ArrayList<Busho>();
      String rarity = mRarity.group(0).toUpperCase();
      for ( Busho busho : bushos ) {
        if (busho.getRarity().equals(rarity)) {
          filteredBushos.add( busho );
        }
      }
      bushos = filteredBushos;
    }
    Matcher mCost = pCost.matcher(arg);
    if (mCost.find()) {
      String costText = mCost.group(0);
      if (costText.length()<=3) {//reject busho id
        List<Busho> filteredBushos = new ArrayList<Busho>();
        int cost = (int)(Double.parseDouble(costText)*10);
        for ( Busho busho : bushos ) {
          int bCost = (int)(Double.parseDouble(busho.getCost())*10);
          if (bCost==cost) {//hack for comparing 3 against 3.0
            filteredBushos.add( busho );
          }
        }
        bushos = filteredBushos;
      }
    }
    return bushos;
  }
  
  public String lookupBusho( String arg, String name ) {
    List<Busho> bushos = getBusho( arg, name );
    if (bushos==null) return "";
    StringBuilder sb = new StringBuilder();
    for ( int i=0; i<bushos.size(); i++ ) {
      Busho busho = bushos.get(i);
      //"["+(i+1)+"] "+
      sb.append( busho.toString()+"\n" );
    }
    for ( int i=0; i<bushos.size(); i++ ) {
      Busho busho = bushos.get(i);
      sb.append( "http://m17.3gokushi.jp/card/trade.php?s=price&o=a&t=no&k="+busho.getID()+"\n" );
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    BushoAPI api = new BushoAPI();
    System.out.println(api.lookupBusho("張飛", "張飛"));
  }
  
}
