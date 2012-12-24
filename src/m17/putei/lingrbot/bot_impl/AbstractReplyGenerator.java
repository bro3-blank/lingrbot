package m17.putei.lingrbot.bot_impl;

import m17.putei.lingrbot.Robot;

public abstract class AbstractReplyGenerator {

  protected Robot bot;
  public void initialize( Robot bot ) {
    this.bot = bot;
  }
  
  public String bot() {
    return bot.getBotName();
  }
  
  abstract public String reply( String t, String user, String userSama );
  abstract public double getThreshold();
  
}
