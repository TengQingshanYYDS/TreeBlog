package com.eh00.controller.admin;

import com.eh00.entity.User;
import com.eh00.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class BackUserController {
    @Autowired
    private UserService userService;

    /**
     * 后台用户列表页面显示
     * @param model
     * @return
     */
    @RequestMapping("")
    public String userList(Model model) {
        List<User> userList = userService.listUser();
        model.addAttribute("userList", userList);

        return "Admin/User/index";
    }

    /**
     * 后台添加用户页面显示
     * @return
     */
    @RequestMapping("/insert")
    public String insertUserView() {
        return "Admin/User/insert";
    }

    /**
     * 检查用户名是否存在
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String checkUserName(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        // 用户名已存在且不是当前用户 才提示重复
        if (user != null && user.getUserId() != id) {
            map.put("code", 1);
            map.put("msg", "用户名已存在");
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 检查Email是否存在
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserEmail", method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String checkUserEmain(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        // 邮箱已存在且不是当前用户 才提示重复
        if (user != null && user.getUserId() != id) {
            map.put("code", 1);
            map.put("msg", "邮箱已存在");
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 后台添加用户提交
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertUserSubmit(User user) {
        // 确保用户名和email不会重复
        User userByName = userService.getUserByName(user.getUserName());
        User userByEmail = userService.getUserByEmail(user.getUserEmail());
        if (userByName == null && userByEmail != null) {
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insertUser(user);
        }

        return "redirect:/admin/user";
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);

        return "redirect:/admin/user";
    }

    /**
     * 编辑用户页面显示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editUserView(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "Admin/User/edit";
    }

    /**
     * 编辑用户提交
     * @param user
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editUserSubmit(User user) {
        userService.updateUser(user);

        return "redirect:/admin/user";
    }
}
