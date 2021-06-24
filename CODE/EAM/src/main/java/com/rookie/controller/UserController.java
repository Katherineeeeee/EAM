package com.rookie.controller;

import com.rookie.model.Application;
import com.rookie.model.Property;
import com.rookie.model.User;
import com.rookie.service.UserApplicationService;
import com.rookie.service.UserPropertyService;
import com.rookie.util.jsonmodel.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户管理的Controller
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserPropertyService userPropertyService;

    @Resource
    UserApplicationService userApplicationService;

    /**
     * 浏览资产的视图
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return user/userViewProperties
     */
    @GetMapping("/properties/pNo/{pageNo}/pSz/{pageSz}")
    public String viewProperties(@PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        //Todo
    }

    /**
     * 申请资产
     * @param id 资产的id
     * @param request Http请求
     * @return JsonResponse
     *      资产为空403 申请失败
     *      资产被使用中403 申请失败
     *      有一个相同的申请, 403, 已经添加了一个申请
     *      添加成功302 /api/user/properties/pNo/1/pSz/10
     *      添加失败303 申请失败
     */
    @PostMapping("/properties/id/{id}")
    @ResponseBody
    public JsonResponse applyProperty(@PathVariable Integer id, HttpServletRequest request) {
        //Todo
    }

    /**
     * 归还资产
     * @param id 申请单的id
     * @param request Http请求
     * @return JsonResponse 归还成功302 /api/user/properties/pNo/1/pSz/10, 归还失败403 归还失败
     */
    @PostMapping("/applications/id/{id}")
    @ResponseBody
    public JsonResponse returnProperty(@PathVariable Integer id, HttpServletRequest request) {
        //Todo
    }

    /**
     * 查看已经申请的资产
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return user/userViewApplications.jsp
     */
    @GetMapping("/applications/pNo/{pageNo}/pSz/{pageSz}")
    public String viewUsed(@PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        //Todo
    }

    /**
     * 辅助工具, 用于获取当前登录的用户
     * @param request Http请求
     * @return 当前登录的用户
     */
    private User getUser(HttpServletRequest request) {
        //Todo
    }

    /**
     * 对分页查询进行限定(页面超过最大页数, 则返回最大页数对应的信息, 页面最大大小超过10, 强制限制为10)
     * @param num 最大的页数
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @return 一个数组, 包含了分页的信息
     */
    private int[] getPageInfo(int num, int pageNo, int pageSz) {
        //Todo
    }
}
