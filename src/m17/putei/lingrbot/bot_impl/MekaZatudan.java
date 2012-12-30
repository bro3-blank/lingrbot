package m17.putei.lingrbot.bot_impl;

import static m17.putei.lingrbot.Utils.random;

public class MekaZatudan {
  
  public static class Zatudan10 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.matches(".*メカ(ぶうすけ).*")) return "゜+.((ヽ(・ｗ・)ノ))゜+.゜しょわしょわ"; 
//        return "( ´⊿`)y-~~　＜　みぃこがリアクションの取り方教えてくれたから一応反応しとくか。\n" +
//      		"メカぶうすけは元気だぴょーん！"+random(new String[]{//"ｷｭ━.+ﾟ*(оﾟдﾟо)*ﾟ+.━ﾝ☆",
//      		        "゜+.((ヽ(・ｗ・)ノ))゜+.゜しょわしょわ"})+
//      		"\n( ´⊿`)y-~~　＜　みんなもリアクション教えてくれよな";
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.99d;
    }
  }
}
