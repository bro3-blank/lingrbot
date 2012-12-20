package m17.putei.lingrbot;

public interface IDB {

  void storeUser( String user, String date, String content );
  
  String getContent( String user, String date );
  
}
