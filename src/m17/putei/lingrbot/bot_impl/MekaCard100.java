package m17.putei.lingrbot.bot_impl;

import java.util.regex.Matcher;

import m17.putei.lingrbot.modules.BushoAPI;
import m17.putei.lingrbot.modules.SkillAPI;


public class MekaCard100 extends AbstractCommon100 {

  private BushoAPI bushoAPI = new BushoAPI();
  private SkillAPI skillAPI = new SkillAPI();
  
  @Override
  protected String getCommandMenu() {
    return  "【１. 君主情報】　 登録シート http://goo.gl/Fw2M2 から君主検索。例：「ギコ軍曹」\n" +
            "【２. カード検索】　例：「張飛」「UC 張飛」「UC 張飛 2.5」\n" +
            "【３. スキル検索】　例：「飛将」\n";
  }

  @Override
  protected String getBotSpecificReply(String t, String user, String userSama) {
    return "";
  }
  
  @Override
  protected String processBotSpecificCommand(String arg, String t, 
          String userSama, String user, String roomId) {
    Matcher mBusho = BushoAPI.pBusho.matcher(arg);
    if ( mBusho.find() ) {
      String result = bushoAPI.lookupBusho( arg, mBusho.group(0) );
      if (result!=null && result.length()>0) return userSama+"、以下のカードが見つかりました\n"+result;
    }
    String skillinfo = skillAPI.lookupSkill( arg );
    if (skillinfo.length()>0) {
      return userSama+"、「"+arg+"」のスキル情報が見つかりました\n"+skillinfo;
    }
    return "";
  }
}
