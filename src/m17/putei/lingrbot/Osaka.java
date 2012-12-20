package m17.putei.lingrbot;

import java.util.Map;


public class Osaka {

  private Map<String,String> conv = Utils.readKeyValue("osaka.txt");
  
  public String toOsaka( String t, int threshold ) {
    int counter = 0;
    for (String key : conv.keySet()) {
      if ( t.contains(key) ) {
        counter++;
        t = t.replaceAll(key, conv.get(key));
      }
    }
    t = t.replaceAll("やか？", "や？");
    t = t.replaceAll("やか。", "や。");
    return (counter>=threshold) ? t : "";
  }
  
  public static void main(String[] args) {
    System.out.println(new Osaka().toOsaka("去年のクリスマス前位に私がおめめ支部から合流した位ですね。11月末スタートだった気がします。",0));
  }
}
