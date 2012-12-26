package m17.putei.lingrbot.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import m17.putei.lingrbot.Robot;
import m17.putei.lingrbot.Utils;
import m17.putei.lingrbot.infra.LingrBotAPI;

@SuppressWarnings("serial")
public class TradeReminderServlet extends HttpServlet {

  private static final String[] tips = {
    "トレ手数料は、500TPより下は10%、500～1000TPが10～15%、そのあとはハネ上がって高額の場合は30%ぐらいと覚えておくといいよ(´・ω・`)",
    "トレードで勝つには、日々値動きを見て相場観を養うことだね、ってぽちたん言ってた(´・ω・`)",
    "きの。さんのトレードツールは使うとトレ効率が全く違うよ(´・ω・`)",
    "きの。さんの自動出兵ツール使うとTPがっぽがっぽだよ(´・ω・`)",
    "4429さんから聞いたけど、スキルレベル8までは生贄R5枚でOKらしい!(´・ω・`)",
    "4429さんから聞いたけど、合成は10:25までにするといいらしい・・・(´・ω・`)",
    "4429さんから聞いたけど、合成はスコア360万以上はいくらあっても同じだとか・・・(´・ω・`)",
    "4429さんから聞いたけど、仕様上レベル10にならないスキルはないって・・・(´・ω・`)",
    "「自分は今期レベリングできたら3コスと2.5コスの飛将を一杯作る！ 数で勝負っす！ｗ」 by 4429",
    "「遠征出来ちゃえば鹵獲じゃなくても速度武将適当になげてるだけで結構資源貯まるっすね＾＾」 by 4429",
    "「最近何故か最低しか引かなくなったんで最大★1で鹵獲10→3万 中距離★2に鹵獲10→3万」 by 4429",
    "「神医12連敗中ですが…お詫びとか来たことないｗ」 by ルイ",
  };
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain; charset=utf-8");
    resp.setCharacterEncoding("utf-8");
    
    if (req.getServerName().contains("lingrcardbot")) {
      Robot bot = Robot.MEKA_CARD;
      String msg = "【リマインダー】トレードまであと３０分です。入札はお済みですか？\n" +
      		"今日の"+bot.getBotName()+"知っ得情報：\n"+
      Utils.random(tips);
      LingrBotAPI.sendMessage( bot.getRoomId(), bot.getBotId(), 
              bot.getVerifier(), msg );
    }
    
    resp.getWriter().println("成功！");
    resp.getWriter().flush();
  }
  
}
