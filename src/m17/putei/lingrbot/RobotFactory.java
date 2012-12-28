package m17.putei.lingrbot;

import java.util.ArrayList;
import java.util.List;

import m17.putei.lingrbot.bot_impl.AbstractReplyGenerator;
import m17.putei.lingrbot.bot_impl.MekaCard100;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku02;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku10;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku20;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku40;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku5;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku60;
import m17.putei.lingrbot.bot_impl.MekaDaitoku.Daitoku80;
import m17.putei.lingrbot.bot_impl.MekaDaitoku100;
import m17.putei.lingrbot.bot_impl.MekaZatsudan100;
import m17.putei.lingrbot.bot_impl.MekaZatudan.Zatudan10;

/**
 * Assemble reply generators to make individual robot
 * 
 * @author blank
 *
 */
public class RobotFactory {

  //メカもふ、大督部屋
  public static Robot createMekaDaitoku() {
    Robot bot = Robot.MEKA_DAITOKU;
    List<AbstractReplyGenerator> replyGenerators = new ArrayList<AbstractReplyGenerator>();
    replyGenerators.add( new MekaDaitoku100() );
    replyGenerators.add( new Daitoku80() );
    replyGenerators.add( new Daitoku60() );
    replyGenerators.add( new Daitoku40() );
    replyGenerators.add( new Daitoku20() );
    replyGenerators.add( new Daitoku10() );
    replyGenerators.add( new Daitoku5() );
    replyGenerators.add( new Daitoku02() );
    for ( AbstractReplyGenerator rg : replyGenerators ) {
      rg.initialize(bot);
    }
    bot.initialize(replyGenerators);
    return bot;
  }

  //メカギコ、カード部屋
  public static Robot createMekaCard() {
    Robot bot = Robot.MEKA_CARD;
    List<AbstractReplyGenerator> replyGenerators = new ArrayList<AbstractReplyGenerator>();
    replyGenerators.add( new MekaCard100() );
    for ( AbstractReplyGenerator rg : replyGenerators ) {
      rg.initialize(bot);
    }
    bot.initialize(replyGenerators);
    return bot;
  }

  //メカぶうすけ、雑談部屋
  public static Robot createMekaZatsudan() {
    Robot bot = Robot.MEKA_ZATSUDAN;
    List<AbstractReplyGenerator> replyGenerators = new ArrayList<AbstractReplyGenerator>();
    replyGenerators.add( new MekaZatsudan100() );
    replyGenerators.add( new Zatudan10() );
    for ( AbstractReplyGenerator rg : replyGenerators ) {
      rg.initialize(bot);
    }
    bot.initialize(replyGenerators);
    return bot;
  }
  
}
