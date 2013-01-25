package m17.putei.lingrbot.bot_impl;

import m17.putei.lingrbot.modules.Links;



public class MekaZatsudan100 extends AbstractCommon100 {

  private Links links = new Links();
  
  @Override
  protected String getCommandMenu() {
    return  "【１. 君主情報】　登録シート http://goo.gl/Fw2M2 から君主検索。 例：「ぶうすけ」\n" +
            "【２. ブックマーク】　シートなどのURLをご案内。詳しくは「URL」で\n"+
            "【３. 盟主命令】　「ごめいれいください」で盟主に代わって指示がもらえます。\n";
//              "【４. 占い】　「おみくじ」\n" +
  }

  @Override
  protected String getBotSpecificReply(String t, String user, String userSama) {
    return "";
  }
  
  @Override
  protected String processBotSpecificCommand(String arg, String t, 
          String userSama, String user, String roomId) {
    if (arg.matches(".*(命令|めいれい).*")) {
      return "( ´⊿`)y-~~　 盟主ぶうすけからみなさんが今すべき司令を預かってるよん。\n"+
             "「お陰様で同盟レベルカンスト！寄付終了です。」\n"+
             "「シートの開きっぱなしは重くなるのでダメ。誤入力はCtrl+zでアンドゥー♪デッキ課金切れに注意。」\n"+
             "「定期的に君主登録シートから戦力情報を更新してくださいね。」";
    }
    String link = links.query(arg, roomId);
    if ( link != null ) {
      return "っ "+link;
    }
    if (arg.matches(".*(URL|ＵＲＬ).*")) {
      links.init();
      return "どのURLを知りたいですか？ (リンク先の追加／修正は http://goo.gl/UlhPb )\n"+links.linkKeys(roomId);
    }
    if (arg.matches(".*(シート).*")) {
      links.init();
      return "どのURLを知りたいですか？ (リンク先の追加／修正は http://goo.gl/UlhPb )\n"+links.sheetLinkKeys(roomId);
    }
//      if (arg.matches(".*(おみくじ|運勢|うんせい|うらない|占い).*")) {
//        return userSama+"の今日の運勢は..."+omikuji.run(user);
//      }
    return "";
  }
}
