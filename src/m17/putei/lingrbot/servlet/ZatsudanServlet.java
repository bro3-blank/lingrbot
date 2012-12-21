package m17.putei.lingrbot.servlet;

import m17.putei.lingrbot.RobotFactory;

@SuppressWarnings("serial")
public class ZatsudanServlet extends LingrBotServlet {
  public ZatsudanServlet() {
    super( RobotFactory.createMekaZatsudan() );
  }
}
