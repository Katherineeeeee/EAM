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
        session.removeAttribute("manager");
        session.invalidate();
        return "logout";
    }

    /**
     * 用户退出登录
     * @param session session
     * @return logout.jsp
     */
    @GetMapping("/logout/user")
    public String userLogout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "logout";
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
        HttpSession session = request.getSession();

        //解析info, 拿到解密后的信息
        String userData = DataUtil.decodeBase64(info, 3);

        //将数据转换为user的信息
        String[] userInfo = AuthUtil.getUserInfo(userData);

        //登录验证
        int status = userManageService.verify(userInfo[0], userInfo[1]);

        //验证成功
        if (status == 0) {

            //获取对应的manager
            User user = userManageService.findByUsername(userInfo[0]);

            //生成token
            String token = DataUtil.encodeMD5Hex(userData + System.currentTimeMillis());

            //session中设置token
            session.setAttribute(token, user);

            //添加cookie到response
            Cookie cookie = new Cookie("user", token);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            return new JsonResponse(302, "/api/user/properties/pNo/1/pSz/10");
        } else {//验证失败
            return new JsonResponse(302, "/api/auth/error");
        }
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
        HttpSession session = request.getSession();

        //解析info, 拿到解密后的信息
        String userData = DataUtil.decodeBase64(info, 3);

        //将数据转换为manager的信息
        String[] userInfo = AuthUtil.getUserInfo(userData);

        //验证登录
        int status = managerManageService.verify(userInfo[0], userInfo[1]);

        //登录成功
        if (status == 0) {

            //获取对应的manager
            Manager manager = managerManageService.findByUsername(userInfo[0]);

            //生成token
            String token = DataUtil.encodeMD5Hex(userData + System.currentTimeMillis());
            //session中设置token
            session.setAttribute(token, manager);

            //添加cookie到response
            Cookie cookie = new Cookie("manager", token);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            return new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10");
        } else {//登录失败
            return new JsonResponse(302, "/api/auth/error");
        }
    }
}
