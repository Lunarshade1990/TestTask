package com.lunarshade.controller;

import com.lunarshade.Dao.UserDataDataImpl;
import com.lunarshade.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
public class UserDataImpController {

    @RequestMapping(value = "/top-five-form-controller")
    public String showForms(Model model) {

        List<UserData> fiveForms = new UserDataDataImpl().getTopFiveForms();

        model.addAttribute("fiveForms", fiveForms);

        return "showFiveTopForms";

    }

    @RequestMapping(value = "/used-form-for-last-hour")
    public String showUsedForms(Model model) {

        List<UserData> usedForms = new UserDataDataImpl().getUsedFormForLastHour();

        model.addAttribute("usedForms", usedForms);

        return "usedFormForLastHour";
    }

    @RequestMapping(value = "/unfinished_users")
    public String showUnfinishedUsers(Model model) {

        List<UserData> unfinishedUsers = new UserDataDataImpl().getUnfinishedUsers();

        model.addAttribute("unfinishedUsers", unfinishedUsers);

        return "unfinishedUsers";
    }
}
