package m17.putei.lingrbot.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m17.putei.lingrbot.Robot;
import m17.putei.lingrbot.Utils;
import m17.putei.lingrbot.infra.LingrBotAPI;
import m17.putei.lingrbot.infra.NetworkAPI;

@SuppressWarnings("serial")
public class NewsReportServlet extends HttpServlet {

  private static final int MAX_NEWS = 7;
  private static final String urlGeneralNews = "https://news.google.com/news/feeds?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss";
  private static final String urlBura3News   = "https://news.google.com/news/feeds?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss&q=%22%E3%83%96%E3%83%A9%E3%82%A6%E3%82%B6%E4%B8%89%E5%9B%BD%E5%BF%97%22";
  private static final String urlWether = "http://weather.livedoor.com/forecast/rss/index.xml";
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain; charset=utf-8");
    resp.setCharacterEncoding("utf-8");
    if (req.getServerName().contains("lingrzatsudanbot")) {
      Robot bot = Robot.MEKA_ZATSUDAN;
      String msg = generateMessage(bot);
      LingrBotAPI.safePostMessage( bot.getRoomId(), bot.getBotId(), bot.getVerifier(), msg );
    }
    resp.getWriter().println("");
    resp.getWriter().flush();
  }
  
  private static String generateMessage(Robot bot) {
    int hour = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo")).get(Calendar.HOUR_OF_DAY);
    boolean isMorning = hour<11;
    boolean isMondayMorning = isMorning && Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo")).get(Calendar.DAY_OF_WEEK)==2;
    StringBuilder sb = new StringBuilder();
    String greeting = isMorning ? "おはようございます、":"こんばんは、";
    sb.append( "( ´⊿`)y-~~　「"+greeting+bot.getBotName()+"が"+hour+"時のニュースをお伝えします。"+(isMorning?"まずはお天気から。":"")+"」\n" );
    sb.append( isMorning?getWether():"" );
    sb.append( isMorning?"( ´⊿`)y-~~　「つづいてニュースです。」\n":"" );
    sb.append( NewsReportServlet.getNews(urlGeneralNews, false) );
    sb.append( "( ´⊿`)y-~~　「" );
    sb.append( isMondayMorning?"月曜になったので、デュエルセットをお忘れなく。":Utils.random(new String[]{
            "世知辛い世の中ですね。",
            "凄惨な事件が続きますね。",
            "一足お先に、ほのぼのとした春の話題をお届けしました。",
            "年末らしい話題でしたね。",
            "たいして目新しい話題はなかったですね。",
            "今日も平和ですね。",
            "言いたい事も言えないこんな世の中じゃ POISON～♪"}) );
    sb.append( (isMorning?Utils.random(new String[]{
            "それでは気をつけていってらっしゃい！",
            "忘れ物はないですか？いってらっしゃい！",
            "それでは今日も一日お元気で！ごきげんよう！",
            "それでは、また夜のニュースで！"})
     :Utils.random(new String[]{
             "今夜も「もふもふ商店」の提供でお送りしました。さようなら！",
             "それでは、今夜も"+bot.getBotName()+"がお届けしました！",
             "それでは、また朝のニュースで！"})) );
    sb.append( "」" );
    return sb.toString();
  }
  
  public static String getNews(String url, boolean bura3News) {
    String content = NetworkAPI.getContentFromURL(url);
    Pattern pItem = Pattern.compile("<item>(.+?)</item>", Pattern.DOTALL);
    Pattern pItems = Pattern.compile("<title>(.+?)</title>.*?<link>.*?url=(.+?)</link>.*?<description>(.+?)</description>", Pattern.DOTALL);
    Matcher mItem = pItem.matcher(content);
    StringBuilder sb = new StringBuilder();
    int counter = 0;
    while ( mItem.find() ) {
      Matcher mItems = pItems.matcher(mItem.group(1));
      if ( mItems.find() ) {
        if (bura3News) {
          if (mItems.group(1).matches(".*(マーベラス|ブラウザ三国志|ONE-UP).*")) {
            sb.append(" - "+mItems.group(1)+"\n");
            sb.append("　"+mItems.group(2)+"\n");
          }
        } else {
          sb.append(" - "+mItems.group(1)+"\n");
          sb.append("　"+mItems.group(2)+"\n");
        }
      }
      if (++counter>=MAX_NEWS) break;
    }
    return sb.toString();
  }
  
  public static String getWether() {
    String content = NetworkAPI.getContentFromURL(urlWether);
    Pattern pItems = Pattern.compile("<title>\\[ 今日の天気 \\] (.+?) - (.+?) - (.+?) -", Pattern.DOTALL);//.*?<link>.*?url=(.+?)</link>.*?<description>(.+?)</description>
    StringBuilder sb = new StringBuilder();
    Matcher mItems = pItems.matcher(content);//mItem.group(1)
    int counter = 0;
    while ( mItems.find() ) {
      sb.append( counter%2==0 ? "　":"" );
      sb.append( mItems.group(1)+"・・・"+mItems.group(2)+"/"+mItems.group(3));
      sb.append( counter%2==0 ? "、　":"" );
      sb.append( counter%2==1 ? "\n":"" );
      ++counter;
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    //http://weather.livedoor.com/forecast/rss/index.xml
//    System.out.println();
    Robot bot = Robot.MEKA_ZATSUDAN_TEST;
    String msg = generateMessage(bot);
    System.out.println(msg);
//    LingrBotAPI.sendMessage( bot.getRoomId(), bot.getBotId(), 
//            bot.getVerifier(), msg );
    
//    System.out.println(NetworkAPI.getContentFromURL("http://lingr.com/api/room/say?room="
//            +bot.getRoomId()+"&bot="+bot.getBotId()+"&bot_verifier"+bot.getVerifier()+"&text=TESTING"));
//    System.out.println(NewsReportServlet.getNews(urlGeneralNews, false));
//    System.out.println(NewsReportServlet.getNews(urlBura3News, true));
  }
  
}
