package m17.putei.lingrbot.bot_impl;

import m17.putei.lingrbot.Utils;

public class UserMapper {

  public static String getName( String user, String botName ) {
    String suffix = getSuffix( user, botName );
    if (user.equals("4429")) return Utils.random005("レベル10スキルしかもってない")+Utils.random(new String[]{"先代","４様","４４２９"+suffix});
    if (user.equals("ぶうすけ")) return Utils.random005("溥杏の盟主代々の神技を引き継いだニヒルな")+user+"盟主";
    if (user.equals("ダンディ坂野")) return "ダンディ(σ・∀・)σさん";
    if (user.equals("きっこりぃ")) user = Utils.random005("議事録をまとめがすごかった")+user; 
    if (user.equals("バジル")) user = Utils.random005("みかんの星からやってきた")+"バジ";
    if (user.equals("ペレオン大提督")) user = "ペレオン";
    if (user.equals("海馬竜")) user = Utils.random005("カワハギ釣り名人")+Utils.random(new String[]{"トド","とど"});
    if (user.equals("みぃこ")) user = Utils.random005("愛とエロの伝道師、")+user;
    if (user.equals("ぷり☆けつ")) user = Utils.random005("なぜお尻なのか謎の")+Utils.random(new String[]{"お尻","プリ"});
    if (user.equals("とんとん")) user = Utils.random005("なぜかﾁｬﾚｰってよく言われてる")+Utils.random(new String[]{"とんとん","トントン"});
    if (user.equals("はぐれるメタル")) user = Utils.random005("経験値たっぷり(^q^)の")+"はぐりん";
    if (user.equals("ぽるぽち")) user = Utils.random005("URを3ページ持つ変態、")+Utils.random(new String[]{"ぽち","ぬるぽち"});
    if (user.equals("もふもふ商店")) return Utils.random005("冬でも裸でもふもふの")+Utils.random(new String[]{"もふりん"+suffix}); 
    return user+suffix;
  }
  
  private final static String suffix = Utils.random(new String[]{
          "さん","さん","さん","さん","さん","さん","さん","さん","さん","さん","さん","さん",
          "たん","たん","様","師匠","先生","選手"});//,"大提督","将軍"
  public static String getSuffix( String user, String botName ) {
    if ( botName.startsWith("メカもふ") ) {
      
    } else if ( botName.startsWith("メカぶうすけ") ) {
      if ( user.equals("ぶうすけ") ) return "盟主殿";
      if ( user.equals("伊波") ) return "破壊王";
      if ( user.equals("がんじょ") ) return "神";
      if ( user.equals("もふもふ商店") ) return "大督殿";
      if ( user.equals("琥武") ) return "長者殿";
      if ( user.equals("真皇") ) return "外交官殿";
      if ( user.equals("きっこりぃ") ) return "盟主補佐殿";
      if ( user.equals("三条") ) return "軍師殿";
    } else if ( botName.startsWith("メカギコ") ) {
      if ( !user.endsWith("軍曹") && !user.endsWith("提督") )
      return "二等兵";
    }
    if (user.matches("みぃこ|TARO|ちょちょこ|バジル|めいこ|三条|夜の舞蝶")) return Utils.random(new String[]{"お嬢様",suffix,suffix});
    return suffix;
  }
}
