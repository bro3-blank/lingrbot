package m17.putei.lingrbot.modules;

import m17.putei.lingrbot.Utils;
import m17.putei.lingrbot.infra.IDB;


public class Omikuji {
  
  private IDB db;
  private final static String[] lines = Utils.readLinesInArray("omikuji.txt");
  
  public Omikuji( IDB db ) {
    this.db = db;
  }
  
  public String run(String user) {
    return run( user, Utils.getDate() );
  }
  
  public String run(String user, String today) {
    String content = db.getContent(user, today);
    if (content!=null) return content; 
    content = Utils.random(lines);
    db.storeUser(user, today, content);
    return content;
  }
}
