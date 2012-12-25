package m17.putei.lingrbot.servlet;

import java.io.IOException;
import java.util.Calendar;
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

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain; charset=utf-8");
    resp.setCharacterEncoding("utf-8");
    
    String msg = Calendar.getInstance().getTime()+" テストです";
    Robot bot = Robot.MEKA_DAITOKU_TEST;
    LingrBotAPI.sendMessage( bot.getRoomId(), bot.getBotId(), 
            bot.getVerifier(), msg );
    
    resp.getWriter().println(msg);
    resp.getWriter().flush();
  }
  
  public static void main(String[] args) {
    String url = "https://news.google.com/news/feeds?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss";
    String url2 = "https://news.google.com/news/feeds?hl=ja&ned=us&ie=UTF-8&oe=UTF-8&output=rss&q=%22%E3%83%96%E3%83%A9%E3%82%A6%E3%82%B6%E4%B8%89%E5%9B%BD%E5%BF%97%22";
    String content = NetworkAPI.getContentFromURL(url);
    Pattern pItem = Pattern.compile("<item>(.+?)</item>", Pattern.DOTALL);
    Pattern pItems = Pattern.compile("<title>(.+?)</title>.*?<link>.*?url=(.+?)</link>.*?<description>(.+?)</description>", Pattern.DOTALL);
    Matcher mItem = pItem.matcher(content);
    while ( mItem.find() ) {
      Matcher mItems = pItems.matcher(mItem.group(1));
//      System.out.println("--\n"+mItem.group(1));
      if ( mItems.find() ) {
        System.out.println(mItems.group(1)+"\n"+mItems.group(2)+"\n"+mItems.group(3).replaceAll("&lt;.*?&gt;", "")+"\n");
      }
    }
  }
  
}
