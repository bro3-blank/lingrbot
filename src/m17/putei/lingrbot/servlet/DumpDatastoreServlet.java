package m17.putei.lingrbot.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
//See https://appengine.google.com
public class DumpDatastoreServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    StringBuilder sb = new StringBuilder();
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//    datastore.delete(KeyFactory.createKey("LingrBot", "blank"), KeyFactory.createKey("LingrBot", "ぶらんく"));
    Query query = new Query();
    List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
    if ( !entities.isEmpty() ) {
      for ( Entity e : entities ) {
        //e.getKey()+"\t"+e.getKind()
        //LingrBot("Omikuji")/TARO(10)  TARO
        sb.append(e.getKey()+"\t"+e.getProperties()+"\n");
      }
    }
    System.out.println(sb);
//    resp.setContentType("text/plain; charset=utf-8");
//    resp.setCharacterEncoding("utf-8");
//    resp.getWriter().println("");
//    resp.getWriter().flush();
  }
}
