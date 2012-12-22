package m17.putei.lingrbot;

import java.util.List;
import java.util.Properties;

import m17.putei.lingrbot.bot_impl.UserMapper;

public class Robot {

  private List<IReplyGenerator> replyGenerators;
  private String roomId;
  private String botId;
  private String verifier;
  
  public Robot( List<IReplyGenerator> replyGenerators, String botId ) {
    this.replyGenerators = replyGenerators;
    this.botId = botId;
    try {
      Properties p = new Properties();
      p.load(Robot.class.getResourceAsStream("/bot_config.properties"));
      this.roomId = p.getProperty(botId + ".roomId").trim();
      this.verifier = p.getProperty(botId + ".verifier").trim();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public Robot( List<IReplyGenerator> replyGenerators, String roomId, String botId, String verifier ) {
    this.replyGenerators = replyGenerators;
    this.roomId = roomId;
    this.botId = botId;
    this.verifier = verifier;
  }
  
  public String reply( String t, String user ) {
    t = t.replaceAll("　", " ");
    user = user.replaceAll("^\\(\\(\\(\\s*","").replaceAll("[(（].+?[)）].*","").replaceAll("[@＠].*", "").replaceAll("。", "");
    String userSama = UserMapper.getName(user);
    double r = Math.random();
    //sort
    for ( IReplyGenerator rg : replyGenerators ) {
      if ( r > rg.getThreshold() ) return "";
      String result = rg.reply( t, user, userSama );
      if (result!=null && result.length()>0) return result; 
    }
    return "";
  }

  public String getRoomId() {
    return roomId;
  }

  public String getBotId() {
    return botId;
  }

  public String getVerifier() {
    return verifier;
  }
  
}
