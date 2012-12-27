package m17.putei.lingrbot.bot_impl;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.Utils;
import m17.putei.lingrbot.infra.NetworkAPI;
import m17.putei.lingrbot.modules.Links;

public abstract class AbstractCommon100 extends AbstractReplyGenerator {

  private final static String playerDBURL = "https://script.google.com/macros/s/AKfycbzwuID1yQArJlDRV02YtfFYpdmQdG068oJyDvel6MwCFG5_m632/exec";
 
  private final static Pattern pZahyo = Pattern.compile("\\(([0-9-]+),([0-9-]+)\\)");
  private final static Pattern pCommand = Pattern.compile("[「｢](.+?)[」｣](.*)");

  private Links links = new Links();
  
  @Override
  public final String reply( String t, String user, String userSama, String roomId ) {
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
          return processCommand(mCommand.group(1), t, userSama, user, roomId);
        }
      }
      if ((t.charAt(0)=='!' || t.charAt(0)=='！') 
              && (!t.contains("！？") && !t.contains("！！") &&!t.contains("!?") && !t.contains("!!")) 
              && t.length()>2) {
        String arg = t.substring(1);
        return processCommand(arg, t, userSama, user, roomId);
      }
    }
    {
      String reply = getBotSpecificReply( t, user, userSama );
      if ( reply!=null && reply.length()>0 ) return reply;
    }
    return "";
  }

  @Override
  public final double getThreshold() {
    return 1D;
  }
  
  abstract protected String processBotSpecificCommand(String arg, String t, 
          String userSama, String user, String roomId);

  protected String processCommand(String arg, String t, 
          String userSama, String user, String roomId) {
    String reply = processBotSpecificCommand( arg, t, userSama, user, roomId );
    if (reply!=null && reply.length()>0) return reply; 
    String link = links.query(arg, roomId);
    if ( link != null ) {
      return "っ "+link;
    }
    if (arg.matches(".*(URL|ＵＲＬ).*")) {
      return "どのURLを知りたいですか？\n"+links.linkKeys(roomId);
    }
    if (arg.matches(".*(シート).*")) {
      return "どのURLを知りたいですか？\n"+links.sheetLinkKeys(roomId);
    }
    if (arg.matches(".*(こまんど|コマンド|へるぷ|ヘルプ|？|\\?|めにゅー|メニュー).*")) {
      return bot()+"のコマンド教えてあげるね"+Utils.face()+"\n" +
           getCommandMenu()+
          "※「コマンド」 でも !コマンド でも両方いけるよ"+Utils.face();
    }
    try {
      String url = playerDBURL+"?player="+URLEncoder.encode(arg, "UTF-8");
      String ret = NetworkAPI.getContentFromURL(url);
      if (ret.startsWith("君主")) {
        if (ret.contains("見つかりません")) {
//          return userSama+"、"+ret+"\n（"+bot()+"のわかる命令一覧を見るには、「こまんど」って入れてね♪）";
          return "";
        } else {
          return userSama+"、"+ret;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  abstract protected String getBotSpecificReply( String t, String user, String userSama );
  
  abstract protected String getCommandMenu();
  
}