package m17.putei.lingrbot.bot_impl;

import static m17.putei.lingrbot.Utils.face;
import static m17.putei.lingrbot.Utils.random;
import m17.putei.lingrbot.modules.Misawa;
import m17.putei.lingrbot.modules.Osaka;

public class MekaDaitoku100 extends AbstractCommon100 {

  private Osaka osaka = new Osaka();
  private Misawa misawa = new Misawa();
  
  @Override
  public String getBotSpecificCommand( String t, String arg ) {
    if (arg.startsWith("大阪弁")) {
      return osaka.toOsaka(t, 0);
    }
    if (arg.startsWith("みさわ")) {
      return misawa.misawa();
    }
    return "";
  }

  @Override
  protected String getCommandMenu() {
    return  "【１. 競合砦報告】　「南西競合砦」「南東競合砦　☆3」「南西競合砦　劉備軍」「南西競合砦　馬」\n" +
            "【２. 君主情報】　 「もふもふ商店」\n" +
            "【３. カード検索】　「張飛」「UC 張飛」「UC 張飛 2.5」\n" +
            "【４. スキル検索】　「飛将」\n" +
            "【５. ブックマーク】　詳しくは「URL」で\n" +
            "【６. 占い】　「おみくじ」\n";
  }

  @Override
  protected String getBotSpecificReply( String t, String user, String userSama ) {
    if (t.equals("テスト")) return userSama+"のテスト入りましたー！";
    if (t.matches(".*メカ(ちゃん|もふ|モフ).*？")) return random(new String[]{"個人情報にはお答えできません。。。",
            "ご想像におまかせ(*ﾉω・*)ﾃﾍ"});
    if (t.matches(".*(ショワショワ|しょわしょわ|ｼｮﾜｼｮﾜ).*")) return "゜+.((ヽ(・ｗ・)ノ))゜+.゜しょわしょわ～";
    if (t.matches(".*なでなで.*")) return random(new String[]{
            "嬉しいなぁ"+face(),"もっともっと～"+face(),"(∀｀*ゞ)エヘヘ",bot()+"もなでなで(。-∀-)ﾉｼ(´･ω･`)　← "+userSama,
            "(*´д｀*)ヾ(・ω・*) "+userSama+"にお返しなでなで",
            "嬉しくて、思わず...゜+.((ヽ(・ｗ・)ノ))゜+.゜しょわしょわ～", "(　´∀｀)σ)∀`) < "+userSama+"、それなでなでちゃうよ～",
            "♬♩♫♪☻(●´∀｀●）☺♪♫♩♬", "|彡ｻｯ < 人が見てるよ"});
    return "";
  }
  
}