package m17.putei.lingrbot;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class DatastoreDB implements IDB {

  public final static String PARENT_KEY_KIND = "LingrBot";
  public final static String CHILD_KEY_KIND = "Omikuji";
  
  @Override
  public void storeUser(String user, String date, String content) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key omikujiKey = KeyFactory.createKey(PARENT_KEY_KIND, PARENT_KEY_KIND);//String kind, String name
    Entity eOmikuji = new Entity(CHILD_KEY_KIND, omikujiKey);//String kind, Key parent 
    eOmikuji.setProperty("user", user);
    eOmikuji.setProperty("date", date);
    eOmikuji.setProperty("content", content);
    datastore.put(eOmikuji);
  }

  @Override
  public String getContent(String user, String date) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key omikujiKey = KeyFactory.createKey(PARENT_KEY_KIND, PARENT_KEY_KIND);//String kind, String name
    Filter filter = new FilterPredicate("user", FilterOperator.EQUAL, user);
    Query query = new Query(CHILD_KEY_KIND, omikujiKey).setFilter(filter);//addSort("user", Query.SortDirection.DESCENDING)
    Entity userOmikuji = datastore.prepare(query).asSingleEntity();
    if ( userOmikuji != null) {
      if (date.equals(userOmikuji.getProperty("date"))) {
        return userOmikuji.getProperty("content").toString();
      } else {
        datastore.delete(userOmikuji.getKey());
      }
    }
    return null;
  }

  
  
}
