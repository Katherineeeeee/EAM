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
        int num = userPropertyService.getNumOfUnused(pageSz);
        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查询
        List<Property> properties = userPropertyService.findUnusedByPage(pageInfo[0], pageInfo[1]);

        //绑定属性, 用于JSP渲染
        request.setAttribute("properties", properties);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "user/userViewProperties";
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
        Property property = userPropertyService.findBypId(id);
        User curUser = getUser(request);
        if (property == null) {
            return new JsonResponse(403, "申请失败");
        }
        if (property.getUser() != null) return new JsonResponse(403, "申请失败");
        else {
            boolean exist = userApplicationService.existsByUserAndProperty(curUser, property);
            if (exist) {
                return new JsonResponse(403, "已经添加了一个申请");
            }
            boolean res = userApplicationService.addApplication(1 ,curUser, property);
            if (res) return new JsonResponse(302, "/api/user/properties/pNo/1/pSz/10");
            else {
                return new JsonResponse(403, "申请失败");
            }
        }
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
        User curUser = getUser(request);
        boolean res = userApplicationService.returnProperty(id, curUser.getuId());
        if (res) return new JsonResponse(302, "/api/user/properties/pNo/1/pSz/10");
        else {
            return new JsonResponse(403, "归还失败");
        }
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
        User user = getUser(request);
        if(user == null)return "404";
        int num = userPropertyService.getNumOfPageByUser(Math.min(10, pageSz), user);
        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查询
        List<Application> applications = userApplicationService.findByPageAndUser(pageNo,pageSz,"aId",user);

        //绑定属性, 用于JSP渲染
        request.setAttribute("applications", applications);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "user/userViewApplications";
    }

    /**
     * 辅助工具, 用于获取当前登录的用户
     * @param request Http请求
     * @return 当前登录的用户
     */
    private User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token != null ? (User) session.getAttribute(token) : null;
    }

    /**
     * 对分页查询进行限定(页面超过最大页数, 则返回最大页数对应的信息, 页面最大大小超过10, 强制限制为10)
     * @param num 最大的页数
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @return 一个数组, 包含了分页的信息
     */
    private int[] getPageInfo(int num, int pageNo, int pageSz) {
        if (pageSz > 10) pageSz = 10;
        if (pageNo > num) {
            pageNo = num;
        }
        if (pageNo < 1) {
            pageNo = 1;
        }
        int prePage = pageNo - 1;
        int nextPage = pageNo + 1;
        if(prePage == 0){
            prePage = -1;
        }
        if(nextPage > num){
            nextPage = -1;
        }
        return new int[]{pageNo, pageSz, prePage, nextPage};
    }
}
