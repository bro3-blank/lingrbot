package m17.putei.lingrbot.servlet;

import m17.putei.lingrbot.RobotFactory;

@SuppressWarnings("serial")
public class CardServlet extends LingrBotServlet {
  public CardServlet() {
    super( RobotFactory.createMekaCard() );
  }
}
