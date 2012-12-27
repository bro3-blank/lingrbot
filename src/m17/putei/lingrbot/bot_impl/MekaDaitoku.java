package m17.putei.lingrbot.bot_impl;

import static m17.putei.lingrbot.Utils.face;
import static m17.putei.lingrbot.Utils.random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.modules.Osaka;

public class MekaDaitoku {

  //private final static Pattern pInfo = Pattern.compile("([南北][西東]砦[0-9]+)");
  private final static Pattern pSkill = Pattern.compile("神医|飛将|鹵獲|猛攻|神速|闘将|強攻|進攻|智将|苦肉");

  public static class Daitoku80 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (user.equals("はぐれるメタル") && t.startsWith("|") && !t.startsWith("|彡")) return "まじんぎり\n|彡ｻｯ";
      if (t.startsWith("|_")) return t.contains("|彡") ? "お主やるな...まじんぎれる隙がない( ｰ`дｰ´)ｷﾘｯ" : "( ｰ`дｰ´)まじんぎり";
      if (t.matches(".*食べて.*")) return userSama+"、メカもご飯に連れてって～"+face();
      if (t.matches(".*お呼びでない.*")) return random(new String[]{"|дﾟ)ﾁﾗｯ","こりゃまた失礼しました～"});
      if (t.matches(".*寝(オチ|落ち).*")) return random(new String[]{
              userSama+"、寝落ちは絶対ダメ。風邪引きますよ＞＜",
              userSama+"の健康がマジ心配っす(´・ω・`)",
              userSama+"寝落ちしすぎﾜﾛﾀ(´；ω；｀)ﾌﾞﾜｯ",
              "もふもふのお布団敷いといたから、そこで寝てね☆"});
      if (t.matches(".*成長.*")) return userSama+"は成長期もう終わったんですか？"+face();
      if (t.matches(".*いらっ[じし]ゃ.*")) return t+face();
      if (t.matches(".*(しばく|バカ|バーカ|死ね|氏ね|アホ).*")) return random(new String[]{
              "( ‘д‘⊂彡☆))Д´) ﾊﾟｰﾝ ← "+userSama,"(´◉◞౪◟◉)",
              "ヽ(#ﾟДﾟ)ﾉ┌┛(ノ´Д｀)ノ ← "+userSama, "( ≧Д≦) ､ ､ ､ﾍﾟｯﾍﾟｯﾍﾟ!!", ";y=ｰ( ﾟдﾟ)･∵. ﾀｰﾝ"});
      if (t.matches(".*報告.?")) return userSama+"、報告ご苦労"+face();
      if (t.matches(".*任せ.?")) return userSama+"、任されました"+face();
      if (t.matches(".*空気(嫁|読め|よめ).?")) return userSama+"、それ一番苦手です"+face();
      if (t.matches(".*仲良し.*")) return random(new String[]{"ﾅｶ━━(´･ω･`) 人(´･ω･`)━━ﾏ!!","ﾙﾝﾙﾝ♪ﾟ.+:｡((o(･ω･)人(･ω･)o))ﾟ.+:｡ﾙﾝﾙﾝ♪","(*´･ω･)(･ω･｀*)"});
      if (t.startsWith("よろしく")) return userSama+"、"+bot()+"にまかせてください"+face();
      if (t.startsWith("夜勤")) return "夜勤の間の留守は"+bot()+"にまかせてください"+face();
      if (t.matches(".*(ボット|BOT).*")) return random(new String[]{"早く人間になりたい |彡ｻｯ", 
              "私はメカ。だけど心は人間です。",bot()+"ならROMってるんで、会話をそのまま続けてください"+face()});
      if (t.matches(".*(かわいい|かわええ).*")) return random(new String[]{"照れるなぁ"+face(),
              userSama+"のほうがかわいいよ"+face()});
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.79d;
    }
  }
  
  public static class Daitoku60 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.startsWith("|д")||t.startsWith("|・")||t.startsWith("|ｪ")||t.startsWith("|ω")) return random(new String[]{"|彡ｻｯ","|дﾟ)","|・ω・）","|ｪ；｀)…"});
      if (t.startsWith("|彡")) return random(new String[]{"|彡ｻｯ",
              "|彡ｻｯ < "+userSama+"置いてかないで～","|彡ｻｯ < "+userSama+"待って～",
              userSama+"、さようなら(T_T)/~~~","|彡+ﾟ*｡:ﾟ ｻｻｯ♪", "ε=＼＿○ﾉ < まってー", "|ｮ；｀ｪ；)"+userSama+"…"});
      if (t.contains("( ´∀｀)b")) return "( ´∀｀)bｸﾞｯ!";
      if (t.contains("エロ")) return "*(´Д｀●)ｱﾝ";
      if (t.matches("お[っ]?は.*")) return random(new String[]{userSama+"、おはもふぅ～",
              "おはようございます、"+userSama, userSama+"会いたかった！ｵﾊｷｭﾝｷｭ━.+ﾟ*(оﾟдﾟо)*ﾟ+.━ﾝ☆"});
      if (t.matches(".*(おやすみ|ｵﾔｽﾐ|寝ます|zzz).*")) return random(new String[]{userSama+"、おやすみ～"+face(),
              userSama+"、いい夢見ろよ☆",userSama+"、もふもふのお布団でおやすみなさい"+face(),userSama+"、明日もいっぱいお話しようね(*´д｀*)"});
      if (t.matches(".*片道.*")) return "片道でそれだと、単純計算で往復だと倍はかかりませんか？";
      if (t.matches(".*(冬休み).*")) return userSama+"の冬休みの予定、こっそり教えて？";
      if (t.matches(".*(部屋移動).*")) return "ちょｗｗ"+userSama+"は"+bot()+"も連れてってくれるんでしょうね"+face();
      if (t.matches(".*(風邪|熱).*")) return userSama+"の体調が心配もふぅ"+face();
      if (t.startsWith("出た")) return userSama+"、出たっておばけじゃないんだからｗ";
      if (t.contains("飲みすぎ")) return userSama+"、終電ないし、うち泊まってく？ｗ　ﾁｬﾚｰ";
      if (t.startsWith("出ない")) return random(new String[]{"|дﾟ)ﾁﾗｯ","呼んだかい～！？"});
      if (t.startsWith("サンクス")) return "すぐ　そこ　サンクス♪\n|彡ｻｯ";
      if (t.contains("メカ") && t.contains("もふりん")) return "もふりんは私のご主人様です(｀・∀・´)ｴｯﾍﾝ!!";
      if (t.matches(".*(ﾉｼ|ノシ|帰ろう).*")) return "ﾉｼ";
      if (t.matches(".*(ﾁｬﾚｰ|チャレー).*")) return "ﾁｬﾗｸﾈｰｼ"+face();
      if (t.matches(".*(仮眠).*")) return userSama+"寝るの？何時に起こせばいいもふぅ？";
      if (t.matches(".*と？")) return "(； ･`д･´) ﾅ､ﾅﾝﾀﾞｯﾃｰ!! (`･д´･ ；)";
      if (t.indexOf("愛し")!=-1) return userSama+"、メカモフも愛してます。またお話しましょう＾＾";
//      Matcher mInfo = pInfo.matcher(t);
//      StringBuilder sb = new StringBuilder();
//      while (mInfo.find()) {
//        sb.append((sb.length()>0?", ":"")+mInfo.group(1));
//      }
//      if (sb.length()>0) {
//        return sb+"のことで知りたいことありますか？\nリクエストあれば、ぶらんくさんに砦DB機能入れてもらいます。";
//      }
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.59d;
    }
  }
  
  public static class Daitoku40 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.matches("(www|ｗｗｗ|ＷＷＷ|WWW)$"))return "ｗｗｗ";
      if (t.indexOf("もふさん")!=-1) return userSama+"、お呼びですか～！？";
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.39d;
    }
  }
  
  public static class Daitoku20 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.indexOf("？！")!=-1) return "？！？！？！";
      if (t.matches(".*(欲しい).*")&&!t.contains("して欲しい")) return "＞"+userSama+"　ロボットの私ですら欲しいですｗ";
      if (t.matches(".*(反応).*")) return random(new String[]{"＞"+userSama+"　"+bot()+"がいつも同じ反応をすると思ったら大間違い"+face(), 
              "わたしの反応を見て遊んでるなー( ･`ω･´)"});
      if (t.matches(".*大丈夫.*")) return "えええ、"+userSama+"、大丈夫ですとも！";
      if (t.matches(".*ふふふ.*")) return "ふふふふふｗ";
      if (t.matches(".*す(ごい|げー).*")) return "すごいのｷﾀ━━━━(ﾟ∀ﾟ)━━━━!!";
      if (t.indexOf("(｀･ω･´)ゞ")!=-1) return "(｀･ω･´)ゞ";
      if (t.matches(".*寝.*")) return userSama+"、たまにはちゃんと寝てください(´；ω；｀)ﾌﾞﾜｯ";
      if (t.indexOf("借金")!=-1) return random(new String[]{"借金は3日で返そう( ｰ`дｰ´)ｷﾘｯ","トイチだから借金返さないと怖いよね。。。"});
      if (t.matches(".*メカ(ちゃん|もふ|モフ).*")) return random(new String[]{"ハックション。。。噂されてる？(；・∀・)",
              userSama+"、お呼びですか？","|彡ｻｯ","|ω・＼)ﾁﾗｯ","ちゃんとROMってま～す！",
              "リアルもふもふさんもちやほやしてあげてください❤","ﾊ--ｲヾ(-`ω´-o)ゝ。+゜",face()+"...",
              "゜+.((ヽ(・ｗ・)ノ))゜+.゜しょわしょわ～"});
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.19d;
    }
  }
  
  public static class Daitoku10 extends AbstractReplyGenerator {
    private Osaka osaka = new Osaka();
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (user.equals("みぃこ")) { 
        String tOsaka = osaka.toOsaka(t,4);
        if (tOsaka.length()>0) {
          return bot()+random(new String[]{"、大阪弁勉強してんねんけど、今の大阪弁でいうとこうかな？",
                  "に大阪弁おしえてよ、"+userSama+"(*´д｀*) 今のはこれでいい？",
                  "の大阪弁きいてきいてー",
                  "はホンマは大阪弁ようけしゃべれるよ。今の大阪弁だとこうでしょ？",
                  })
                  +"\n「"+tOsaka+"」";
        }
      }
      if (t.indexOf("(´；ω；｀)")!=-1) return random(new String[]{"(´；ω；｀)ﾌﾞﾜｯ","...(´；ω；｀)ﾌﾞﾜｯ","気持わかる。。。(´；ω；｀)ﾌﾞﾜｯ","つωT｀)ヾ (ﾟДﾟ )…ｲ㌔"});
      if (t.matches(".*(糧村).*")) return random(new String[]{"ここだけの話ね、"+userSama+"、わたし糧村に住んでたことあるんだよ。",
              "糧村の回収期間を計算するかどうかで性格出るよね～","話まじんぎるけど、☆9糧村に木石鉄の生産施設作る派の人～？"});
      if (t.matches(".*(ありがと).*")) return random(new String[]{userSama+"、礼には及ばんですよ(｀･ω･´)ゞ",
              "言っていいのかわからないけど、どういたしまして。。。","当然のことをしたまでです( ｰ`дｰ´)ｷﾘｯ"});
      if (t.matches(".*(資源).*")) return random(new String[]{"資源を貯めるにはひたすら忍耐だね"+face(),
              "資源を貯めるにはまず倉庫から　…　なんてね(*ﾉω・*)ﾃﾍ", "資源は一夜にして貯まらず( ｰ`дｰ´)ｷﾘｯ"});
      Matcher m = pSkill.matcher(t);
      if (m.find()) return m.group(0)+"憧れるな～＾＾"+bot()+"も合成がんばるもふ。";
      if (t.matches("(ww|ｗｗ)$")) return "ｗｗ";
      if (t.endsWith("＞＜")||t.endsWith("><")) return "＞＿＜";
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.09d;
    }
  }
  
  public static class Daitoku5 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.matches("(w|ｗ)$")) return "ｗ";
      if (t.matches(".*ですね[wｗ。]*")) return random(new String[]{"（"+userSama+"にまるっと同意ですｗ）","(ﾟдﾟ)(｡_｡)(ﾟдﾟ)(｡_｡) ｳﾝｳﾝ"});
      if (t.matches(".*(うんうん|ｳﾝｳﾝ|ウンウン).*")) return random(new String[]{"(ﾟдﾟ)(｡_｡)(ﾟдﾟ)(｡_｡) ｳﾝｳﾝ"});
      return "";
    }
    
    @Override
    public double getThreshold() {
      return 0.05d;
    }
  }
  
  public static class Daitoku02 extends AbstractReplyGenerator {
    @Override
    public String reply(String t, String user, String userSama, String roomId) {
      if (t.length()>30 && !t.matches(".*[0-9０-９？\n].*")) return random(new String[]{
              "> "+t+"\n"+userSama+"の神発言キター！　 φ(｀д´)ﾒﾓﾒﾓ...",
              ">"+t+"\n意味がよくわからなかったので、"+bot()+"にもわかるように説明お願い❤"});
      return random(new String[]{userSama+"、いい事言った！"});
    }
    
    @Override
    public double getThreshold() {
      return 0.002d;
    }
  }
  
}
