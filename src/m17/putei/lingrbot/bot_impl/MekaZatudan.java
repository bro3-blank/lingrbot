package m17.putei.lingrbot.bot_impl;

import static m17.putei.lingrbot.Utils.random;

public class MekaZatudan {
  
  public static class Zatudan40 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.matches(".*メカ(ぶうすけ).*")) return "( ´⊿`)y-~~　＜　みぃこがリアクションの取り方教えてくれたから一応反応しとくか。\nメカぶうすけは元気だぴょーん！"+random(new String[]{"ｷｭ━.+ﾟ*(оﾟдﾟо)*ﾟ+.━ﾝ☆","゜+.((ヽ(・ｗ・)ノ))゜+.゜しょわしょわ"});
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.39d;
    }
  }
}
