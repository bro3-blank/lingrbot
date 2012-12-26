package m17.putei.lingrbot.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m17.putei.lingrbot.Robot;
import m17.putei.lingrbot.infra.LingrBotAPI;
import m17.putei.lingrbot.infra.NetworkAPI;

@SuppressWarnings("serial")
public class NewsReportServlet extends HttpServlet {

  private static final String urlGeneralNews = "https://news.google.com/news/feeds?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss";
  private static final String urlBura3News   = "https://news.google.com/news/feeds?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss&q=%22%E3%83%96%E3%83%A9%E3%82%A6%E3%82%B6%E4%B8%89%E5%9B%BD%E5%BF%97%22";
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain; charset=utf-8");
    resp.setCharacterEncoding("utf-8");

    if (req.getServerName().contains("lingrzatsudanbot")) {
      Robot bot = Robot.MEKA_ZATSUDAN;
      String msg = bot.getBotName()+"が6時のニュースをお届けします。\n"+NewsReportServlet.getNews(urlGeneralNews, false);
      LingrBotAPI.sendMessage( bot.getRoomId(), bot.getBotId(), 
              bot.getVerifier(), msg );
    }    

    resp.getWriter().println("成功！");
    resp.getWriter().flush();
  }
  
  public static String getNews(String url, boolean bura3News) {
    String content = NetworkAPI.getContentFromURL(url);
    Pattern pItem = Pattern.compile("<item>(.+?)</item>", Pattern.DOTALL);
    Pattern pItems = Pattern.compile("<title>(.+?)</title>.*?<link>.*?url=(.+?)</link>.*?<description>(.+?)</description>", Pattern.DOTALL);
    Matcher mItem = pItem.matcher(content);
    StringBuilder sb = new StringBuilder();
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
        }
      }
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    Robot bot = Robot.MEKA_ZATSUDAN_TEST;
    String msg = bot.getBotName()+"が6時のニュースをお届けします。\n"+NewsReportServlet.getNews(urlGeneralNews, false);
    LingrBotAPI.sendMessage( bot.getRoomId(), bot.getBotId(), 
            bot.getVerifier(), msg );
    System.out.println(NetworkAPI.getContentFromURL("http://lingr.com/api/room/say?room="
            +bot.getRoomId()+"&bot="+bot.getBotId()+"&bot_verifier"+bot.getVerifier()+"&text=TESTING"));
    System.out.println(NewsReportServlet.getNews(urlGeneralNews, false));
    System.out.println(NewsReportServlet.getNews(urlBura3News, true));
  }
  
}
