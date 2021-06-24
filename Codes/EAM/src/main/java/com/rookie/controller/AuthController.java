package com.rookie.controller;

import com.rookie.model.Manager;
import com.rookie.model.User;
import com.rookie.service.ManagerManageService;
import com.rookie.service.UserManageService;
import com.rookie.util.AuthUtil;
import com.rookie.util.DataUtil;
import com.rookie.util.jsonmodel.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录的Controller类
 */
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    UserManageService userManageService;

    @Resource
    ManagerManageService managerManageService;

    /**
     * 返回登录失败的页面
     * @return loginError.jsp
     */
    @GetMapping("/error")
    public String loginErrorPage() {
        return "loginError";
    }

    /**
     * 管理员退出登录
     * @param session session
     * @return logout.jsp
     */
    @GetMapping("/logout/manager")
    public String managerLogout(HttpSession session) {
        //Todo
    }

    /**
     * 用户退出登录
     * @param session session
     * @return logout.jsp
     */
    @GetMapping("/logout/user")
    public String userLogout(HttpSession session) {
        //Todo
    }

    /**
     * 用户登录
     * @param info 用户登录的账号和密码信息(base64加密3次后的信息)
     * @param request Http请求
     * @param response Http响应
     * @return JsonResponse 登录成功, 则返回302 + /api/user/properties/pNo/1/pSz/10, 登录失败, 则返回302 + /api/auth/error
     */
    @PostMapping("/users")
    @ResponseBody
    public JsonResponse userLogin(@RequestParam(value="info") String info, HttpServletRequest request, HttpServletResponse response) {
        //Todo
    }

    /**
     * 管理员登录
     * @param info 管理员登录的账号和密码信息(base64加密3次后的信息)
     * @param request Http请求
     * @param response Http响应
     * @return JsonResponse 登录成功, 则返回302 + /api/manager/users/pNo/1/pSz/10, 登录失败, 则返回302 + /api/auth/error
     */
    @PostMapping("/managers")
    @ResponseBody
    public JsonResponse managerLogin(@RequestParam String info, HttpServletRequest request, HttpServletResponse response) {
        //Todo
    }
}
