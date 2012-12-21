package m17.putei.lingrbot.modules;


public class Busho {

  private final static int ID       = 1;
  private final static int NAME     = 2;
  private final static int RARITY   = 3;
  private final static int COST     = 4;
  private final static int TYPE     = 5;
  private final static int KOUGEKI  = 6;
  private final static int CHIRYOKU = 7;
  private final static int DEF1     = 8;
  private final static int DEF2     = 9;
  private final static int DEF3     = 10;
  private final static int DEF4     = 11;
  private final static int ISOKU    = 12;
  private final static int SKILL    = 16;
  
  private String[] internalData;
  
  public Busho( String[] cols ) {
    internalData = cols;
  }
  
  public String toString() {
    return null;
  }
  
}
