package m17.putei.lingrbot.modules;

import m17.putei.lingrbot.Utils;


public class Misawa {

  private String[] urls = {
          "http://img.jigokuno.com/20121217_330597.gif",
          "http://img.jigokuno.com/20121207_313545.gif",
          "http://img.jigokuno.com/20121203_306311.gif"//知ってる？
          };
  
  public String misawa() {
    return Utils.random(urls);
  }
  
}
