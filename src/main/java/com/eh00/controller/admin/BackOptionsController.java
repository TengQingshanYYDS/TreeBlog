package com.eh00.controller.admin;

import com.eh00.entity.Options;
import com.eh00.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {
    @Autowired
    private OptionsService optionsService;

    /**
     * 基本信息显示
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        Options options = optionsService.getOptions();
        model.addAttribute("options", options);

        return "Admin/Options/index";
    }

    /**
     * 编辑基本信息提交
     *
     * @param options
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editOptionSubmit(Options options)  {
        optionsService.updateOptions(options);
        return "redirect:/admin/options";
    }

}
