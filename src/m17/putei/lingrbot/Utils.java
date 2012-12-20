package m17.putei.lingrbot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;

public class Utils {
  
  public static Map<String,String> readKeyValue( String filename ) {
    Map<String,String> kv = null;
    try {
      List<String> l = IOUtils.readLines(Utils.class.getResourceAsStream("/"+filename), "utf-8");
      kv = new LinkedHashMap<String,String>(l.size()*4/3+1);
      for ( String line : l ) {
        line = line.replaceAll("\\s*#.*", "");
        String[] items = line.split("\\s+");
        if (items.length==2) {
          kv.put(items[0], items[1]);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return kv;
  }
  
  public static String[] readLines( String filename ) {
    List<String> list = null;
    try {
      List<String> l = IOUtils.readLines(Utils.class.getResourceAsStream("/"+filename), "utf-8");
      list = new ArrayList<String>(l.size());
      for ( String line : l ) {
        if (line.length()>0) list.add(line); 
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (list!=null) {
      return (String[]) list.toArray(new String[list.size()]);
    } else {
      return null;
    }
  }
  
  public static String getDate() {
    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("JST"));
    return c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH);
//    Date now = new Date();
//    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//    return dateFormatter.format(now);
  }
 
  
  private final static String[] face = {"(´・ω:;.:...","( ･`ω･´)",
    "(*´д｀*)ｱﾊｧ","(*ﾉω・*)","(｀･ω･´)ゞ","･:*:･(*´∀｀*)ｳｯﾄﾘ･:*:･","(｀・∀・´)","(´・ω・`)"};
  
  public static String face() {
    return random(face);
  }
  
  public static String random(String ... messages) {
    int i = (int)Math.floor((messages.length) * Math.random());
    return messages[i];
  }
  
  public static String random005( String msg ) {
    return Math.random() < 0.05 ? msg : "";
  }
  
  private final static int[] num = {0,1,2,3,4,5,6,7,8,9};
  private final static String[] num2 = {"０", "１", "２", "３", "４", "５", "６", "７", "８", "９"};
  public static String replaceNum( String s ) {
    for ( int i=0; i<num.length; i++ ) {
      s = s.replaceAll(num2[i], num[i]+"");
    }
    return s;
  }
  
}