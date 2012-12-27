package m17.putei.lingrbot.bot_impl;

import static m17.putei.lingrbot.Utils.face;
import static m17.putei.lingrbot.Utils.random;

import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.Utils;
import m17.putei.lingrbot.infra.DatastoreDB;
import m17.putei.lingrbot.infra.NetworkAPI;
import m17.putei.lingrbot.modules.Misawa;
import m17.putei.lingrbot.modules.Omikuji;
import m17.putei.lingrbot.modules.Osaka;

public class MekaDaitoku100 extends AbstractCommon100 {

  private final static Pattern pKyogo = Pattern.compile("([南北][東西])[砦]?[ ]?競合[砦]?[ ]*(.*)");
  private final static String npcDBURL = "https://script.google.com/macros/s/AKfycbwTot9DKVsFu_03jMuHp1HfeQf1JOaI6fjFl5R8YYtX_taBe34/exec";

  private Osaka osaka = new Osaka();
  private Misawa misawa = new Misawa();
  private Omikuji omikuji = new Omikuji(new DatastoreDB());

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
  
  @Override
  protected String processBotSpecificCommand(String arg, String t, 
          String userSama, String user, String roomId) {
    try {
      Matcher mKyogo = pKyogo.matcher(arg);
      if (mKyogo.find()) {
        String direction = mKyogo.group(1);
        String q = Utils.toHankaku(mKyogo.group(2));
        URL url = new URL(npcDBURL+"?direction="+URLEncoder.encode(direction, "UTF-8")+"&q="+URLEncoder.encode(q, "UTF-8"));
        String ret = NetworkAPI.getContentFromURL(url);
        if (ret.startsWith("9期")) return userSama+"、"+ret;
      } else if (arg.contains("砦")) {
        return "ん？どんな砦情報が知りたいの？もっと詳しくお願い(´・ω・`)";
      }
      if (arg.startsWith("大阪弁")) {
        return osaka.toOsaka(t, 0);
      }
      if (arg.startsWith("みさわ")) {
        return misawa.misawa();
      }
      if (arg.matches(".*(おみくじ|運勢|うんせい|うらない|占い).*")) {
        return userSama+"の今日の運勢は..."+omikuji.run(user);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
  
}