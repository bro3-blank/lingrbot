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
      return "( ´⊿`)y-~~　 盟主ぶうすけからみなさんが今がすべき司令を預かってるよん。\n"+
             "「今後は、上級研究。寄付。殲滅。寄付。に注力してください。12/31の寄付額は79万。」\n"+
             "「NPCですが、合流する支部の皆さんに最速で拠点枠を！ってことで、攻略ペース落とします」\n"+
             "「車の生産は、個人の目途でストップしてもらっておｋです！ソロ攻略はおしまいです。」\n"+
             "「もうすぐ始まるチーム攻略に参加するには君主登録シートから登録してください。」\n"+
             "「1人最低1名声使ってNPC包囲にご協力を。シート: http://goo.gl/vlYPI 」\n"+
             "「南東南西に未取得の隣接地が目立ちます、他同盟の侵入もありますので急いで取得を！」";
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
