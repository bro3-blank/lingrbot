package m17.putei.lingrbot.servlet;

import m17.putei.lingrbot.RobotFactory;

@SuppressWarnings("serial")
public class DaitokuServlet extends LingrBotServlet {
  public DaitokuServlet() {
    super( RobotFactory.createMekaDaitoku() );
  }
}
