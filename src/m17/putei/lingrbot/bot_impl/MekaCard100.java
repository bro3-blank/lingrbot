package m17.putei.lingrbot.bot_impl;


public class MekaCard100 extends AbstractCommon100 {

  @Override
  protected String getBotSpecificCommand(String t, String arg) {
    return "";
  }

  @Override
  protected String getCommandMenu() {
    return  "【１. 君主情報】　 例：「ギコ軍曹」\n" +
            "【２. カード検索】　例：「張飛」「UC 張飛」「UC 張飛 2.5」\n" +
            "【３. スキル検索】　例：「飛将」\n"+
            "【４. ブックマーク】　詳しくは「URL」で\n";
//              "【５. ブックマーク】　詳しくは「URL」で\n" +
//              "【４. 占い】　「おみくじ」\n" +
  }

  @Override
  protected String getBotSpecificReply(String t, String user, String userSama) {
    return null;
  }
}
