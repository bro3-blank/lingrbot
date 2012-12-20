package m17.putei.lingrbot;

import m17.putei.lingrbot.ReplyGenerator;

public class Omikuji {
  
  private IDB db;
  private final static String[] lines = Utils.readLines("omikuji.txt");
  
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
