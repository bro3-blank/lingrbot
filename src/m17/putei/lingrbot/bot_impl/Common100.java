package m17.putei.lingrbot.bot_impl;

import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.DatastoreDB;
import m17.putei.lingrbot.IReplyGenerator;
import m17.putei.lingrbot.Utils;
import m17.putei.lingrbot.modules.BushoAPI;
import m17.putei.lingrbot.modules.Links;
import m17.putei.lingrbot.modules.Misawa;
import m17.putei.lingrbot.modules.Omikuji;
import m17.putei.lingrbot.modules.Osaka;
import m17.putei.lingrbot.modules.SkillAPI;

import com.google.api.server.spi.IoUtil;

public class Common100 implements IReplyGenerator {

  private final static String playerDBURL = "https://script.google.com/macros/s/AKfycbzwuID1yQArJlDRV02YtfFYpdmQdG068oJyDvel6MwCFG5_m632/exec";
  private final static String npcDBURL = "https://script.google.com/macros/s/AKfycbwTot9DKVsFu_03jMuHp1HfeQf1JOaI6fjFl5R8YYtX_taBe34/exec";

  private final static Pattern pZahyo = Pattern.compile("\\(([0-9-]+),([0-9-]+)\\)");
  private final static Pattern pCommand = Pattern.compile("[「｢](.+?)[」｣](.*)");
  private final static Pattern pKyogo = Pattern.compile("([南北][東西])[砦]?[ ]?競合[砦]?[ ]*(.*)");

  private Osaka osaka = new Osaka(); 
  private Misawa misawa = new Misawa();
  private Omikuji omikuji = new Omikuji(new DatastoreDB());
  private Links links = new Links();
  private BushoAPI bushoAPI = new BushoAPI();
  private SkillAPI skillAPI = new SkillAPI();
  
  @Override
  public String reply( String t, String user, String userSama ) {
    //100%
    {
      Matcher mZahyo = pZahyo.matcher(t);
      String s = null;
      while (mZahyo.find()) {
        s = s!=null ? "" : "っ http://m17.3gokushi.jp/big_map.php?x="+mZahyo.group(1)+"&y="+mZahyo.group(2);
      }
      if (s!=null && s.length()>0) return s;
    }
    {
      Matcher mCommand = pCommand.matcher(t);
      if (mCommand.find()) {
        String afterParen = mCommand.group(2);
        if (afterParen.length()==0 || !afterParen.matches("^(と|っ).*")) { 
          return processCommand(mCommand.group(1), t, userSama, user);
        }
      }
      if (t.charAt(0)=='!' && t.length()>2) {
        String arg = t.substring(1);
        return processCommand(arg, t, userSama, user);
      }
    }
    return "";
  }

  @Override
  public double getThreshold() {
    return 1D;
  }
  
  private String processCommand(String arg, String t, String userSama, String user) {
    try {
      String link = links.query(arg);
      if ( link != null ) {
        return "っ "+link;
      }
      Matcher mKyogo = pKyogo.matcher(arg);
      if (mKyogo.find()) {
        String direction = mKyogo.group(1);
        String q = Utils.toHankaku(mKyogo.group(2));
        URL url = new URL(npcDBURL+"?direction="+URLEncoder.encode(direction, "UTF-8")+"&q="+URLEncoder.encode(q, "UTF-8"));
        String ret = IoUtil.readStream(url.openStream());
        if (ret.startsWith("9期")) return userSama+"、"+ret;
      } else if (arg.contains("砦")) {
        return "ん？どんな砦情報が知りたいの？もっと詳しくお願い(´・ω・`)";
      }
      if (arg.matches(".*(URL|ＵＲＬ).*")) {
        return "どのURLを知りたいですか？\n"+links.keys();
      }
      if (arg.matches(".*(こまんど|コマンド|へるぷ|ヘルプ|？|\\?|めにゅー|メニュー).*")) {
        return "メカもふのコマンド教えてあげるね"+Utils.face()+"\n" +
        		"【１. 競合砦報告】　「南西競合砦」「南東競合砦　☆3」「南西競合砦　劉備軍」「南西競合砦　馬」\n" +
            "【２. 君主情報】　 「もふもふ商店」\n" +
            "【３. カード検索】　「張飛」「UC 張飛」「UC 張飛 2.5」\n" +
            "【４. スキル検索】　「飛将」\n" +
            "【５. ブックマーク】　詳しくは「URL」で\n" +
            "【６. 占い】　「おみくじ」\n" +
            "☆「コマンド」 でも !コマンド でも両方いけるよ"+Utils.face();
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
      Matcher mBusho = BushoAPI.pBusho.matcher(arg);
      if ( mBusho.find() ) {
        String result = bushoAPI.lookupBusho( arg, mBusho.group(0) );
        if (result!=null && result.length()>0) return userSama+"、以下のカードが見つかりました\n"+result;
      }
      String skillinfo = skillAPI.lookupSkill( arg );
      if (skillinfo.length()>0) {
        return userSama+"、スキル情報が見つかりました\n"+skillinfo;
      }
      URL url = new URL(playerDBURL+"?player="+URLEncoder.encode(arg, "UTF-8"));
      String ret = IoUtil.readStream(url.openStream());
      if (ret.startsWith("君主")) {
        if (ret.contains("見つかりません")) {
          return userSama+"、"+ret+"\n（メカもふのわかる命令一覧を見るには、「こまんど」って入れてね♪）";
        } else {
          return userSama+"、"+ret;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
  
}