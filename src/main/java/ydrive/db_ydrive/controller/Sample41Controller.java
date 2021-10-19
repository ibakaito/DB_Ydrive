package ydrive.db_ydrive.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ydrive.db_ydrive.model.Chamber;
import ydrive.db_ydrive.model.ChamberMapper;
import ydrive.db_ydrive.model.ChamberUser;
import ydrive.db_ydrive.model.UserInfo;

@Controller
@RequestMapping("/sample4")
public class Sample41Controller {

  @Autowired
  ChamberMapper chamberMapper;

  @GetMapping("step1")
  public String sample41() {
    return "sample41.html";
  }

  @GetMapping("step4")
  public String sample44() {
    return "sample44.html";
  }

  @GetMapping("step2/{id}")
  public String sample42(@PathVariable Integer id, ModelMap model) {
    Chamber chamber2 = chamberMapper.selectById(id);
    model.addAttribute("chamber2", chamber2);

    return "sample41.html";
  }

  @PostMapping("step3")
  @Transactional
  public String sample43(@RequestParam Integer number, ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    Chamber chamber3 = new Chamber();
    chamber3.setNumber(number);
    chamber3.setUser(loginUser);
    chamberMapper.insertChamber(chamber3);
    model.addAttribute("chamber3", chamber3);
    // System.out.println("ID:" + chamber3.getId());
    return "sample41.html";
  }

  @PostMapping("step5")
  public String sample45(@RequestParam Integer number, ModelMap model) {
    ArrayList<Chamber> chambers5 = chamberMapper.selectAllByNumber(number);
    model.addAttribute("chambers5", chambers5);
    return "sample44.html";
  }

  @GetMapping("step6")
  public String sample46() {
    return "sample46.html";
  }

  @GetMapping("step7")
  @Transactional
  public String sample47(ModelMap model) {
    ArrayList<ChamberUser> chamberUsers7 = chamberMapper.selectAllChamberUser();
    model.addAttribute("chamberUsers7", chamberUsers7);
    return "sample46.html";
  }

  @PostMapping("step8")
  @Transactional
  public String sample48(@RequestParam Double height, ModelMap model, Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    UserInfo ui = new UserInfo();
    ui.setUser(loginUser);
    ui.setHeight(height);
    try {
      chamberMapper.insertUserInfo(ui);
    } catch (RuntimeException e) {// 既に身長が登録されているユーザでさらに登録しようとすると実行時例外が発生するので，コンソールに出力してinsertをSkipする
      System.out.println("Exception:" + e.getMessage());
    }
    // insert後にすべての身長が登録されているユーザを取得する
    ArrayList<ChamberUser> chamberUsers7 = chamberMapper.selectAllChamberUser();
    model.addAttribute("chamberUsers7", chamberUsers7);
    return "sample46.html";
  }


}
