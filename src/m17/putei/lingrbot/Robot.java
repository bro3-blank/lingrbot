package m17.putei.lingrbot;

import java.util.List;
import java.util.Properties;

import m17.putei.lingrbot.bot_impl.AbstractReplyGenerator;
import m17.putei.lingrbot.bot_impl.UserMapper;

public enum Robot {
  MEKA_DAITOKU("blank2"),
  MEKA_DAITOKU_TEST("blank3"),
  MEKA_ZATSUDAN("mekamofu2"),
  MEKA_ZATSUDAN_TEST("busuke_test"),
  MEKA_CARD("meka3"),
  MEKA_CARD_TEST("giko_test");

  private List<AbstractReplyGenerator> replyGenerators;
  private String roomId;
  private String botId;
  private String verifier;
  private String botName;
  
  private Robot( String botId ) {
    this.botId = botId;
    try {
      Properties p = Utils.loadProperties("bot_config.properties");
      this.roomId = p.getProperty(botId + ".roomId").trim();
      this.verifier = p.getProperty(botId + ".verifier").trim();
      this.botName = p.getProperty(botId + ".botName").trim();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private Robot( String roomId, String botId, String verifier, String botName ) {
    this.roomId = roomId;
    this.botId = botId;
    this.verifier = verifier;
    this.botName = botName;
  }
  
  //separated from the constructor; factory class responsible for init
  public void initialize( List<AbstractReplyGenerator> replyGenerators ) {
    this.replyGenerators = replyGenerators;
  }
  
  public String reply( String t, String user ) {
    t = t.replaceAll("　", " ");
    user = user.replaceAll("^\\(\\(\\(\\s*","").replaceAll("[(（].+?[)）].*","").replaceAll("[@＠].*", "").replaceAll("。", "");
    String userSama = UserMapper.getName(user);
    double r = Math.random();
    //sort
    for ( AbstractReplyGenerator rg : replyGenerators ) {
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

  public String getBotName() {
    return botName;
  }
  
}
