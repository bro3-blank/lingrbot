package m17.putei.lingrbot;

public class Test {
  
  public static void main(String[] args) {
    String text = "「大阪弁　こんにちは、今日はいい天気ですね」";
    System.out.println("> "+text);
    Robot g = RobotFactory.createMekaDaitoku();
    long t0 = System.currentTimeMillis();
    System.out.println("> "+ g.reply(text,"ぶらんく"));
    long t1 = System.currentTimeMillis();
    System.out.println((double)(t1-t0)/1000+" sec.");
  }
  
}
