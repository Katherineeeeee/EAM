package com.rookie.controller;

import com.rookie.model.Application;
import com.rookie.model.Manager;
import com.rookie.model.Property;
import com.rookie.model.User;
import com.rookie.service.*;
import com.rookie.util.DataUtil;
import com.rookie.util.jsonmodel.JsonManager;
import com.rookie.util.jsonmodel.JsonProperty;
import com.rookie.util.jsonmodel.JsonResponse;
import com.rookie.util.jsonmodel.JsonUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 管理员管理的Controller
 */
@Controller
@RequestMapping("/api/manager")
public class ManagerController {

    @Resource
    UserManageService userManageService;

    @Resource
    PropertyManageService propertyManageService;

    @Resource
    ApplicationManageService applicationManageService;

    @Resource
    ManagerManageService managerManageService;

    /**
     * 获取当前登录的管理员的信息
     * @param request Http请求
     * @return JsonResponse 200 管理员的信息/404 未发现
     */
    @GetMapping("/self")
    @ResponseBody
    public JsonResponse getSelf(HttpServletRequest request) {
        Manager self = getManager(request);
        if (self == null) {
            return new JsonResponse(404, "Not Found");
        }
        String info = "name:" + self.getmName() + "\nphone:" + self.getmPhone() + "\nemail:" + self.getmEmail();
        return new JsonResponse(200, DataUtil.encodeBase64(info, 3));
    }

    /**
     * 更新管理员个人信息，包括手机号和email，当且仅当提供非空属性时完成更新
     * @param manager 更新值
     * @param request 请求
     * @return 更新成功返回200，当管理员不存在或者信息非法返回403
     */
    @PutMapping("/self")
    @ResponseBody
    public JsonResponse updateSelf(JsonManager manager, HttpServletRequest request) {
        Map<String, String> infos = manager.toMap();
        Manager self = getManager(request);
        if (infos.get("password") != null) {
            self.setmPassword(infos.get("password"));
        }
        if (infos.get("email") != null) {
            self.setmEmail(infos.get("email"));
        }
        if (infos.get("phone") != null) {
            self.setmPhone(infos.get("phone"));
        }
        boolean res = managerManageService.updateSelf(self);
        if (res) {
            String token = getToken(request);
            request.getSession().removeAttribute(token);
            request.getSession().invalidate();
            return new JsonResponse(200, "更新成功");
        }
        else return new JsonResponse(403, "更新失败");
    }

    /**
     * 返回用户管理对应分页下的信息
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return manager/userManage.jsp
     */
    @GetMapping("/users/pNo/{pageNo}/pSz/{pageSz}")
    public String userManage(@PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        int num = userManageService.getNumOfPage(pageSz);
        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查找
        List<User> users = userManageService.findByPage(pageInfo[0], pageInfo[1]);

        //绑定属性, 用于jsp渲染
        request.setAttribute("users", users);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "manager/userManage";
    }

    /**
     * 获取用户管理下的用户详情的视图
     * @param id 用户的id
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return manager/userDetail.jsp, 用户不存在则返回404
     */
    @GetMapping("/users/id/{id}/pNo/{pageNo}/pSz/{pageSz}")
    public String getUserDetail(@PathVariable Integer id, @PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        User user = userManageService.findByuId(id);
        if (user == null) {
            return "404";
        }
        request.setAttribute("user",user);

        int num = applicationManageService.getNumOfPageAndUser(Math.min(pageSz, 10),user);

        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查找
        List<Application> applications = applicationManageService.findByPageAndUser(pageInfo[0], pageInfo[1], "aId", user);

        //绑定属性, 用于jsp渲染
        request.setAttribute("userid", id);
        request.setAttribute("username", user.getuName());
        request.setAttribute("email", user.getuEmail());
        request.setAttribute("status", user.getuStatus() == 1 ? "激活" : (user.getuStatus() == -1 ? "删除" : "冻结"));
        request.setAttribute("applications", applications);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "manager/userDetail";
    }

    /**
     * 添加用户(会检查数据的格式和用户名是否冲突)
     * @param jsonUser 用户的信息封装类,通过相应的方法可以转换为User
     * @return JsonResponse 添加成功则返回302 /api/manager/users/pNo/1/pSz/10, 添加失败则返回403 Add failed
     */
    @PostMapping("/users")
    @ResponseBody
    public JsonResponse addUser(JsonUser jsonUser) {
        //添加一个User到数据库, 检查名字是否冲突
        boolean res = userManageService.addUser(jsonUser.parse());
        if (res) return new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10");
        else {
            return new JsonResponse(403, "Add failed");
        }
    }

    /**
     * 删除列表ids中的用户
     * @param ids 待删除用户id的列表
     * @param request Http请求
     * @return JsonResponse 302 /api/manager/users/pNo/1/pSz/10
     */
    @DeleteMapping("/users")
    @ResponseBody
    public JsonResponse delUsers(@RequestBody List<Integer> ids, HttpServletRequest request) {
        //从userTable中删除ids中的user(只修改删除位为1), 然后返回重定向请求
        for (Integer id: ids) {
            userManageService.delUserByuId(id);
        }
        return new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10");
    }

    /**
     * 获取对应用户的详细信息
     * @param id 用户的id
     * @return JsonResponse 用户不存在, 则返回404 Not Found, 否则返回200 Base64加密3次后的信息
     */
    @GetMapping("/users/id/{id}")
    @ResponseBody
    public JsonResponse getUserDetail(@PathVariable Integer id) {
        //得到用户的信息
        User user = userManageService.findByuId(id);
        if (user == null) return new JsonResponse(404, "Not Found");
        String info = "name:" + user.getuName() + "\nemail:" + user.getuEmail();
        return new JsonResponse(200, DataUtil.encodeBase64(info, 3));
    }

    /**
     * 更新用户的状态
     *
     * @param id     用户ID
     * @param status 更新状态
     * @return 更新失败返回403，更新成功返回302
     */
    @PatchMapping("/users/id/{id}/status/{status}")
    @ResponseBody
    public JsonResponse updateUser(@PathVariable Integer id, @PathVariable Integer status, HttpServletRequest request) {
        boolean res = userManageService.updateUser(id, status);
        if (res) return new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10");
        return new JsonResponse(403, "更新失败");
    }

    /**
     * 更新用户个人信息email以及密码，当且仅当提供非空属性时完成更新
     * @param id       目标ID
     * @param jsonUser 更新信息
     * @return 更新成功返回200，更新失败返回403
     */
    @PutMapping("/users/id/{id}")
    @ResponseBody
    public JsonResponse updateUser(@PathVariable Integer id, JsonUser jsonUser) {
        boolean res = userManageService.updateUser(id, jsonUser.parse());
        if (res) return new JsonResponse(200, "更新成功");
        return new JsonResponse(403, "更新失败");
    }

    /**
     * 返回资产详情的视图
     * @param pageNo 页号
     * @param pageSz 页面的最大的大小
     * @param request Http请求
     * @return manager/propertyManage.jsp
     */
    @GetMapping("/properties/pNo/{pageNo}/pSz/{pageSz}")
    public String propertyManage(@PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        int num = propertyManageService.getNumOfPage(pageSz);
        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查找
        List<Property> properties = propertyManageService.findByPage(pageInfo[0], pageInfo[1]);

        //绑定属性, 用于jsp渲染
        request.setAttribute("properties", properties);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "manager/propertyManage";
    }

    /**
     * 添加资产
     * @param jsonProperty 资产信息的封装类, 可以通过相应的方法将其转换为Property
     * @return JsonResponse
     */
    @PostMapping("/properties")
    @ResponseBody
    public JsonResponse addProperty(JsonProperty jsonProperty) {
        //增加一个资产
        Map<String, String> json = jsonProperty.toMap();

        //读取数量, 数量不存在则为1
        int num = 1;
        try{
            num = Math.max(1, Integer.parseInt(json.get("num")));
        } catch (Exception e) {
        }
        System.out.println(num);
        //添加Property
        for (int i = 0; i < num; i++) {
            Property property = new Property();
            property.setpName(json.get("name"));
            property.setpBrand(json.get("brand"));
            property.setpModel(json.get("model"));
            property.setpSpec(json.get("spec"));
            property.setpTime(new Date());
            if (!propertyManageService.addProperty(property)) {//只要有一个添加失败,就算失败
                return new JsonResponse(403, "添加失败");
            }
        }
        return new JsonResponse(302, "/api/manager/properties/pNo/1/pSz/10");
    }

    /**
     * 获取资产详情
     * @param id 资产的id
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return manager/propertyDetail.jsp
     */
    @GetMapping("/properties/id/{id}/pNo/{pageNo}/pSz/{pageSz}")
    public String getPropertyDetail(@PathVariable Integer id, @PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        Property property = propertyManageService.findBypId(id);
        if (property == null) return "404";
        request.setAttribute("property", property);

        int num = applicationManageService.getNumOfPageByPageAndProperty(Math.min(pageSz, 10), property);

        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查找
        List<Application> applications = applicationManageService.findByPageAndProperty(pageInfo[0], pageInfo[1], "aId", property);

        //绑定属性, 用于JSP渲染
        request.setAttribute("id", property.getpId());
        request.setAttribute("name", property.getpName());
        request.setAttribute("brand", property.getpBrand());
        request.setAttribute("model", property.getpModel());
        request.setAttribute("spec", property.getpSpec());
        request.setAttribute("time", property.getpTime());
        request.setAttribute("status", property.getUser() == null ? "空闲" : "使用中");
        request.setAttribute("applications", applications);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "manager/propertyDetail";
    }

    /**
     * 获取申请信息
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return manager/applicationManage.jsp
     */
    @GetMapping("/applications/pNo/{pageNo}/pSz/{pageSz}")
    public String getApplicationManage(@PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        int num = applicationManageService.getNumOfPage(Math.min(pageSz, 10));
        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查找
        List<Application> applications = applicationManageService.findByPage(pageInfo[0], pageInfo[1]);

        //绑定属性, 用于JSP渲染
        request.setAttribute("applications", applications);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "manager/applicationManage";
    }

    /**
     * 审核申请单
     * @param id 申请单Id
     * @param status 通过1/驳回-1
     * @param request Http请求
     * @return JsonResponse 成功302 /api/manager/applications/pNo/1/pSz/10, 失败403 更新失败
     */
    @PatchMapping("/applications/id/{id}/status/{status}")
    @ResponseBody
    public JsonResponse reviewApplication(@PathVariable Integer id, @PathVariable Integer status, HttpServletRequest request) {
        //审核使用, id表示申请单的id, status表示是否审核通过
        boolean res = applicationManageService.updateApplication(id, status, getManager(request));
        if (res) return new JsonResponse(302, "/api/manager/applications/pNo/1/pSz/10");
        else {
            return new JsonResponse(403, "更新失败");
        }
    }

    /**
     * 管理员管理视图
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param request Http请求
     * @return manager/managerManage
     */
    @GetMapping("/managers/pNo/{pageNo}/pSz/{pageSz}")
    public String managerManage(@PathVariable Integer pageNo, @PathVariable Integer pageSz, HttpServletRequest request) {
        int num = managerManageService.getNumOfPage(Math.min(pageSz, 10));
        int[] pageInfo = getPageInfo(num, pageNo, pageSz);

        //分页查找
        List<Manager> managers = managerManageService.findByPage(pageInfo[0], pageInfo[1], "mId");

        //绑定属性, 用于JSP渲染
        request.setAttribute("managers", managers);
        request.setAttribute("curPage", pageInfo[0]);
        request.setAttribute("sumPage", num);
        request.setAttribute("prePage", pageInfo[2]);
        request.setAttribute("nextPage", pageInfo[3]);
        return "manager/managerManage";
    }

    /**
     * 添加管理员
     * @param jsonManager 管理员信息的封装类, 通过相应的方法可以转换为Manager
     * @return JsonResponse 添加成功则返回302 /api/manager/managers/pNo/1/pSz/10, 添加失败则返回403 添加失败
     */
    @PostMapping("/managers")
    @ResponseBody
    public JsonResponse addManager(JsonManager jsonManager) {
        //添加一个管理员
//        System.out.println(jsonManager.getInfo());
        boolean res = managerManageService.addManager(jsonManager.parse());
        if (res) return new JsonResponse(302, "/api/manager/managers/pNo/1/pSz/10");
        else return new JsonResponse(403, "添加失败");
    }

    /**
     * 删除管理员
     * @param ids  管理员列表
     * @param request Http请求
     * @return JsonResponse 302 /api/manager/managers/pNo/1/pSz/10
     */
    @DeleteMapping("/managers")
    @ResponseBody
    public JsonResponse delManager(@RequestBody List<Integer> ids, HttpServletRequest request) {
        Manager self = getManager(request);
        ids.remove(self.getmId());
        ids.remove(new Integer(0));
        //删除ids中的所有管理员
        for (Integer id: ids) {
            managerManageService.delManagerById(id);
        }
        return new JsonResponse(302, "/api/manager/managers/pNo/1/pSz/10");
    }

    /**
     * 更新管理员
     * @param id 管理员的id
     * @param status -1删除
     * @param request Http请求
     * @return JsonResponse 更新成功则302 /api/manager/managers/pNo/1/pSz/10, 更新失败则403 /api/manager/managers/pNo/1/pSz/10
     */
    @PatchMapping("/managers/id/{id}/status/{status}")
    @ResponseBody
    public JsonResponse updateManager(@PathVariable Integer id, @PathVariable Integer status, HttpServletRequest request) {
        Manager manager = getManager(request);
        if ((id.equals(0) || id.equals(manager.getmId())) && status == -1) {
            return new JsonResponse(403, "/api/manager/managers/pNo/1/pSz/10");
        }
        boolean res = managerManageService.updateManager(id, status);
        if (res) return new JsonResponse(302, "/api/manager/managers/pNo/1/pSz/10");
        else return new JsonResponse(403, "/api/manager/managers/pNo/1/pSz/10");
    }

    /**
     * 辅助方法, 从request中获取当前的Manager
     * @param request Http请求
     * @return 当前登录的Manager
     */
    private Manager getManager(HttpServletRequest request) {
        String token = getToken(request);
        return token != null ? (Manager) request.getSession().getAttribute(token) : null;
    }

    /**
     * 辅助方法, 从request中获取当前登录的mamager的token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("manager")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
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
