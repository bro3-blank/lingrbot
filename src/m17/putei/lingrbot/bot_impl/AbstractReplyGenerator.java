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
  
  abstract public String reply( String t, String user, String userSama, String roomId );
  
  /**
   * しきい値を0から1の間で指定。0.8なら80%の確率で発動。
   * @return
   */
  abstract public double getThreshold();
  
}
