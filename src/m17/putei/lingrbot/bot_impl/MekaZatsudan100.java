package m17.putei.lingrbot.bot_impl;


public class MekaZatsudan100 extends AbstractCommon100 {

  @Override
  protected String getBotSpecificCommand(String t, String arg) {
    return "";
  }

  @Override
  protected String getCommandMenu() {
    return  "【１. 君主情報】　 例：「ぶうすけ」\n" +
            "【２. ブックマーク】　詳しくは「URL」で\n"+
            "※カードやスキル情報を教えてくれるのはメカギコ軍曹。彼はカード部屋にいるよ♪\n";
//              "【４. 占い】　「おみくじ」\n" +
  }

  @Override
  protected String getBotSpecificReply(String t, String user, String userSama) {
    return null;
  }
}
