package m17.putei.lingrbot;

import java.util.ArrayList;
import java.util.List;

import m17.putei.lingrbot.bot_impl.Common100;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku02;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku10;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku100;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku20;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku40;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku5;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku60;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku80;

/**
 * Assemble reply generators to make individual robot
 * 
 * @author blank
 *
 */
public class RobotFactory {

  public static Robot createMekaDaitoku() {
    List<IReplyGenerator> replyGenerators = new ArrayList<IReplyGenerator>();
    replyGenerators.add( new Common100() );
    replyGenerators.add( new Daitoku100() );
    replyGenerators.add( new Daitoku80() );
    replyGenerators.add( new Daitoku60() );
    replyGenerators.add( new Daitoku40() );
    replyGenerators.add( new Daitoku20() );
    replyGenerators.add( new Daitoku10() );
    replyGenerators.add( new Daitoku5() );
    replyGenerators.add( new Daitoku02() );
    Robot robot = new Robot(replyGenerators);
    return robot;
  }

  public static Robot createMekaHentaiCard() {
    List<IReplyGenerator> replyGenerators = new ArrayList<IReplyGenerator>();
    Robot robot = new Robot(replyGenerators);
    return robot;
  }

  public static Robot createMekaZatsudan() {
    List<IReplyGenerator> replyGenerators = new ArrayList<IReplyGenerator>();
    Robot robot = new Robot(replyGenerators);
    return robot;
  }
  
}
