package m17.putei.lingrbot.modules;

import java.util.ArrayList;
import java.util.List;


public class Skill {

  private String skill;//初期スキル
  private String skill1;//中確率
  private String skill2;//低確率
  private String skill3;//極低確率
  private String skill4;//隠しスキル
  
  private List<String> skillOwner = new ArrayList<String>();//スキル所持武将
  
  private String url;//atwiki url
  
  public Skill( String skill, String skill1, String skill2, String skill3, String skill4 ) {
    this.skill = skill;
    this.skill1 = skill1;
    this.skill2 = skill2;
    this.skill3 = skill3;
    this.skill4 = skill4;
  }
  
  public List<String> getSkillOwner() {
    return skillOwner;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSkill1() {
    return skill1;
  }

  public String getSkill2() {
    return skill2;
  }

  public String getSkill3() {
    return skill3;
  }

  public String getSkill4() {
    return skill4;
  }

  @Override
  public String toString() {
    return "| "+skill+" | "+skill1+" | "+skill2+" | "+skill3+" | "+skill4+" |";
  }
}
