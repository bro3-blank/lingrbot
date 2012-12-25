package m17.putei.lingrbot.modules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m17.putei.lingrbot.Utils;

public class SkillAPI {

  public final static Pattern pSkill = Pattern.compile("[\\p{InHiragana}\\p{InCJKUnifiedIdeographs}]+");
//  public final static Pattern pWikiUrls = Pattern.compile("<a href=\"(.+?)\"[^<>]+?>(.+?)</a>");  
  public final static Pattern pWikiUrls = Pattern.compile("<a href=\"(http://www35.atwiki.jp/browser3594/pages/.+?)\"[^<>]+?title=\"コスト別[^<>]+?>(.+?)</a>");  
  
  private final static String filename1 = "skillData.html";
  private final static String filename2 = "atwiki-browser3594.html";
  
  private final static Pattern p = Pattern.compile("^<tr><td>(\\d{4})");
  private final static Pattern pOwner = Pattern.compile("[0-9]+ (.+?) \\((\\w{1,2})\\)");
  private Properties skillNameMapper;
  
  private Map<String,Skill> skillDB;
  
  public SkillAPI() {
    skillNameMapper = Utils.loadProperties("skill-name-mapping.properties");
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
        Matcher mOwner = pOwner.matcher(cols[1]);
        if (mOwner.find()) skill.getSkillOwner().add( mOwner.group(2)+mOwner.group(1) );
        db.put( name, skill );
      }
    }
    //初期スキル持ち武将がいない場合用　例：剣兵の猛撃
    Set<String> allSkills = new HashSet<String>();
    for ( Skill skill : db.values() ) {
      allSkills.add(skill.getSkill1());
      allSkills.add(skill.getSkill2());
      allSkills.add(skill.getSkill3());
      allSkills.add(skill.getSkill4());
    }
//    List<String> aaa = new ArrayList<String>();
    String atwiki = Utils.readFully( filename2 );
    Matcher mWikiUrls = pWikiUrls.matcher(atwiki);
    while ( mWikiUrls.find() ) {
      String url = mWikiUrls.group(1);
      String wikiname = mWikiUrls.group(2);
//      aaa.add(wikiname);
      if ( wikiname.contains("＞") ) continue;
      System.out.println(wikiname+"\t"+url);
      {
        Skill s = db.get(wikiname);
        if ( s==null ) {
//          System.out.println(wikiname);
          s = new Skill(wikiname, "?", "?", "?", "?");
          db.put(wikiname, s);
        }
        s.setUrl(url);
      }
      for ( String name : allSkills ) {
        String mappedWikiname = skillNameMapper.getProperty(name);
        mappedWikiname = (mappedWikiname!=null) ? mappedWikiname.trim() : name;
        if ( wikiname.equals(mappedWikiname) ) {
          Skill s = db.get(name);
          if ( s==null ) {
            s = new Skill(name, "?", "?", "?", "?");
            db.put(name, s);
          }
          if (s.getUrl()==null) s.setUrl(url);
        }
      }
    }
    for ( String name : allSkills ) {
      String mappedWikiname = skillNameMapper.getProperty(name);
      if (mappedWikiname!=null) mappedWikiname = mappedWikiname.trim();
    }
    return db;
  }
  
  public Skill getSkill( String name ) {
    Skill skill = skillDB.get(name);
    return skill;
  }

  //key: skill, val: skill owner bushos
  public List<Skill> getGoseiNormal( String name ) {
    List<Skill> skills = new ArrayList<Skill>();
    for ( Skill skill : skillDB.values() ) {
      if ( skill.getSkill1().equals(name) || 
              skill.getSkill2().equals(name) || 
              skill.getSkill3().equals(name) ) {
        skills.add(skill);
      }
    }
    return skills;
  }
  
  //key: skill, val: skill owner bushos
  public List<Skill> getGoseiKakushi( String name ) {
    List<Skill> skills = new ArrayList<Skill>();
    for ( Skill skill : skillDB.values() ) {
      if ( skill.getSkill4().equals(name) ) {
        skills.add(skill);
      }
    }
    return skills;
  }
  
  public String lookupSkill( String name ) {
    StringBuilder sb = new StringBuilder();
    Skill skill = getSkill( name );
    List<Skill> skillsNormal  = getGoseiNormal( name );
    List<Skill> skillsKakushi = getGoseiKakushi( name );
    
    if(skill!=null || skillsNormal.size()>0 || skillsKakushi.size()>0) sb.append("| 初期 | 中確率 | 低確率 | 極低確率 | 隠し |\n");
    if (skill!=null) sb.append( skill.toString()+"\n" );
    for ( Skill s : skillsNormal )  sb.append(s.toString()+"\n");
    for ( Skill s : skillsKakushi ) sb.append(s.toString()+"\n");
    if (skill!=null) {
      List<String> owners = skill.getSkillOwner();
      sb.append( "初期装備武将: "+(owners.size()>0?sortByRarity(owners):"なし")+"\n" );
    }
    {
      List<String> sozaiNormal = new ArrayList<String>();
      List<String> sozaiKakushi = new ArrayList<String>();
      for ( Skill s : skillsNormal ) sozaiNormal.addAll(s.getSkillOwner());
      for ( Skill s : skillsKakushi ) sozaiKakushi.addAll(s.getSkillOwner());
      sb.append( "通常付与素材武将: " + (sozaiNormal.size()>0 ? sortByRarity(sozaiNormal):"なし")+"\n" );
      sb.append( "隠し付与素材武将: " + (sozaiKakushi.size()>0 ? sortByRarity(sozaiKakushi):"なし")+"\n" );
    }
    if (skill!=null && skill.getUrl()!=null) sb.append( skill.getUrl() );
    return sb.toString();
  }
  
  private static String sortByRarity( List<String> bushos ) {
    String[] rarities = {"C", "UC", "R", "SR", "UR"};
    StringBuilder sb = new StringBuilder();
    for ( String rarity : rarities ) {
      for ( String busho : bushos ) {
        if ( busho.startsWith(rarity) ) {
          sb.append(sb.length()>0?", ":"");
          sb.append(busho);
        }
      }
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    SkillAPI api = new SkillAPI();
    String[] names = {"精鋭の進撃", "剣兵の猛撃", "槍兵の強撃", "剣兵防御", "神医の術式", 
            "飛蹄進軍", "農林技術", "食糧技術"};
    for ( String name : names ) {
      System.out.println("["+name+"]\n"+api.lookupSkill(name)+"\n");
    }
  }

}
