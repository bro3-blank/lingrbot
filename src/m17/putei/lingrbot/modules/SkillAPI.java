package m17.putei.lingrbot.modules;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.Utils;

public class SkillAPI {

  public final static Pattern pSkill = Pattern.compile("[\\p{InHiragana}\\p{InCJKUnifiedIdeographs}]+");
  public final static Pattern pWikiUrls = Pattern.compile("<a href=\"(.+?)\"[^<>]+?>(.+?)</a>");  
  
  private final static String filename1 = "skillData.html";
  private final static String filename2 = "atwiki-browser3594.html";
  
  private final static Pattern p = Pattern.compile("^<tr><td>(\\d{4})");

  private Map<String,Skill> skillDB;
  
  public SkillAPI() {
    skillDB = load();
  }
  
  public Map<String,Skill> load() {
    Map<String,Skill> db = new LinkedHashMap<String,Skill>();
    List<String> lines = Utils.readLines( filename1 );
    for ( String line : lines ) {
      Matcher m = p.matcher(line);
      if ( m.find() ) {
        String[] cols = line.replaceAll("><", "").split("<.+?>");
        String name = cols[2];
        Skill skill = db.get( name );
        if ( skill == null ) skill = new Skill( cols[2], cols[3], cols[4], cols[5], cols[6] );
        skill.getSkillOwner().add( cols[1] );
        db.put( name, skill );
      }
    }
    String atwiki = Utils.readFully( filename2 );
    Matcher mWikiUrls = pWikiUrls.matcher(atwiki);
    while ( mWikiUrls.find() ) {
      String url = mWikiUrls.group(1);
      String name = mWikiUrls.group(2);
      Skill s = db.get(name);
      if ( s!=null ) s.setUrl(url);
    }
    return db;
  }
  
  public Skill getSkill( String name ) {
    Skill skill = skillDB.get(name);
    return skill;
  }

  public List<Skill> getGosei( String name ) {
    List<Skill> skills = new ArrayList<Skill>();
    for ( Skill skill : skillDB.values() ) {
      if ( skill.getSkill1().equals(name) || 
              skill.getSkill2().equals(name) || 
              skill.getSkill3().equals(name) || 
              skill.getSkill4().equals(name) ) {
        skills.add(skill);
      }
    }
    return skills;
  }
  
  public String lookupSkill( String name ) {
    StringBuilder sb = new StringBuilder();
    Skill skill = getSkill( name );
    List<Skill> skills = getGosei( name );
    if(skill!=null || skills.size()>0) sb.append("| 初期 | 中確率 | 低確率 | 極低確率 | 隠し |\n");
    if (skill!=null) sb.append( skill.toString()+"\n" );
    for ( Skill s : skills ) {
      sb.append(s.toString()+"\n");
    }
    if (skill!=null) {
      sb.append( "初期装備武将: "+skill.getSkillOwner()+"\n" );
      sb.append( skill.getUrl() );
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    SkillAPI api = new SkillAPI();
    System.out.println(api.lookupSkill("豪傑"));
  }

}
