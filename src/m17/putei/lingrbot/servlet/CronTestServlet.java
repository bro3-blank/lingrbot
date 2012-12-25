package m17.putei.lingrbot.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m17.putei.lingrbot.Robot;
import m17.putei.lingrbot.infra.LingrBotAPI;

@SuppressWarnings("serial")
public class CronTestServlet extends HttpServlet {

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
  
}
