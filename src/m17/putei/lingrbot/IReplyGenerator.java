package m17.putei.lingrbot;

public interface IReplyGenerator {

  String reply( String t, String user, String userSama );
  double getThreshold();
  
}
