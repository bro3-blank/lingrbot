package m17.putei.lingrbot.infra;

/**
 * 永続化の方法がGoogle Datastoreから変わった時のために、一応
 * インターフェイス化しておく。
 */
public interface IDB {

  void storeUser( String user, String date, String content );
  
  String getContent( String user, String date );
  
}
