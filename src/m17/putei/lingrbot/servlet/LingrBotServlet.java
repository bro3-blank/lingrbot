package m17.putei.lingrbot.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m17.putei.lingrbot.Robot;
import m17.putei.lingrbot.infra.LingrBotAPI;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class LingrBotServlet extends HttpServlet {

  private Robot bot;
  
  public LingrBotServlet( Robot bot ) {
    this.bot = bot;
  }
  
  //For testing!!
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain; charset=utf-8");
    resp.setCharacterEncoding("utf-8");
    String u = req.getParameter("user");
    String t = req.getParameter("text");
    String reply = bot.reply(t, u);
    resp.getWriter().println(reply);
    resp.getWriter().flush();
  }
  
  //Real impl here!!
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain; charset=utf-8");
    resp.setCharacterEncoding("utf-8");
    InputStream is = req.getInputStream();
    if (is==null) return;
    BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
    StringBuilder sb = new StringBuilder();
    String line = null;
    while ((line=br.readLine())!=null) {
      sb.append(line+"\n");
    }
    br.close();
    try {
      JSONObject json = new JSONObject( sb.toString() );
      JSONObject msg = json.getJSONArray("events").getJSONObject(0).getJSONObject("message");
      String text = msg.getString("text").trim();
      String user = msg.getString("nickname").trim();
      String reply = bot.reply( text.trim(), user.trim() );
      String[] lines = reply.split("\n");
      if (lines.length<15) {
        resp.getWriter().println(reply);
        resp.getWriter().flush();
      } else {
        //１５行以上のメッセージは分割投稿
        LingrBotAPI.safePostMessage( bot.getRoomId(), bot.getBotId(), bot.getVerifier(), lines );
        resp.getWriter().print("");
        resp.getWriter().flush();
      }
    } catch (JSONException e) {}
  }
  
}
