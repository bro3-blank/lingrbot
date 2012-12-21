package m17.putei.lingrbot.servlet;

import m17.putei.lingrbot.RobotFactory;

@SuppressWarnings("serial")
public class HentaiCardServlet extends LingrBotServlet {
  public HentaiCardServlet() {
    super( RobotFactory.createMekaHentaiCard() );
  }
}
