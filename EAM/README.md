## 企业资产管理系统

### 简要说明  
- 组织名: com.Rookie  
- 数据库: 使用PostgreSQL(驱动使用的是postgresql-42.2.5)  
- 包管理工具: Maven  
- 工程结构:  
    ```
    src
    |_main
      |_java: java代码包  
      | |_com.rookie
      |   |_controller: 所有的cotroller层的实现
      |   |_service: 业务逻辑层的接口及其实现
      |   |_dao: 数据访问层的接口(使用JPA定义)
      |   |_model: entity层的实现
      |   |_interceptor: 拦截器的实现(目前只有登录拦截器)
      |   |_util: 工具包, 其中包含了一些工具类
      |_resources: 项目配置文件
      | |_db.properties: 配置了数据库
      | |_spring-xxx.xml: 配置spring
      |_webapp: Web资源目录
        |_static: 包含了静态资源css, js, html, img等
        |_WEB-INF
          |_view: 视图层,存放了所有的JSP视图
          |_web.xml: 配置web的servlet(只有一个servlet, spring的servlet会接管所有的请求)
    ```
  
### 数据库设计

- Manager(表名:ManagerTable)

  | 名称      | 类型    | 备注                                         |
  | --------- | ------- | -------------------------------------------- |
  | mId       | Integer | 主键,自增序列                                |
  | mName     | String  | unique = true,不允许为空                     |
  | mPassword | String  | 不允许为空                                   |
  | mPhone    | String  | 可以为空                                     |
  | mEmail    | String  | 可以为空                                     |
  | mStatus   | Integer | 不允许为空(1表示正常, 0表示冻结, -1表示删除) |

- User(表名:UserTable)

  | 名称      | 类型    | 备注                                         |
  | --------- | ------- | -------------------------------------------- |
  | uId       | Integer | 主键,自增序列                                |
  | uName     | String  | unique = true, 不允许为空                    |
  | uPassword | String  | 不允许为空                                   |
  | uEmail    | String  | 可以为空                                     |
  | uStatus   | Integer | 不允许为空(1表示正常, 0表示冻结, -1表示删除) |

- Property(表名:PropertyTable)

  | 名称   | 类型    | 备注                       |
  | ------ | ------- | -------------------------- |
  | pId    | Integer | 主键,自增序列              |
  | pName  | String  | 不允许为空                 |
  | pBrand | String  | 不允许为空                 |
  | pModel | String  | 不允许为空                 |
  | pSpec  | String  | 不允许为空                 |
  | pTime  | Date    | 不允许为空                 |
  | user   | User    | 外键,为null时,表示没人使用 |

- Application(表名:ApplicationTable)

  | 名称       | 类型     | 备注                                           |
  | ---------- | -------- | ---------------------------------------------- |
  | aId        | Integer  | 主键,自增序列                                  |
  | user       | User     | 外键                                           |
  | property   | Property | 外键                                           |
  | manager    | Manager  | 外键                                           |
  | beginTime  | Date     | 申请单提出时间,不允许为空                      |
  | reviewTime | Date     | 审核时间                                       |
  | endTime    | Date     | 申请单关闭时间                                 |
  | aStatus    | Integer  | 不允许为空(1表示通过, 0表示审核中, -1表示驳回) |
  | operation  | Integer  | 不允许为空(1表示申请, 0表示归还)               |

  

### Controller设计

> 下面的接口中有的有@ResponseBody是用于直接向前台返回一个Json数据, 该Json数据封装在Http响应报文的body体中, 其中定义了一个简单的协议, status表示响应的结果, msg表示响应消息, 之所以不用Http自己的响应报文, 是因为Ajax无法拦截重定向请求(前台的重定向由浏览器自动完成, 这样Ajax只能拿到重定向的返回数据, 于是前台的重定向就没法用Ajax完成), 所以此处做了一个简单的处理, Http响应的报文中的status一直都是200, 真正的status在body中封装, 重定向在前台用window.location.href手动完成

##### 1. AuthController

所有AuthController的URL前缀均为__`/api/auth`__

---

- __用户登录__

  - __URL__

    `/api/auth/users`

  - __API接口说明__

    使用的Http Method为POST

    需要加上__`@ResponseBody`__

    参数

    ​	用户的登录信息`@RequestParam(value="info") String info`

    ​	Request`HttpServletRequest request`

    ​	Response`HttpServletResponse response`

  - __功能说明__

    1. 前台传回来的用户名和密码的BASE64加密__3次__后的数据保存在info中,解码三次后得到相应的账号密码数据为userData, 其格式为

       ```
       username:pxc
       password:000000
       ```

    2. 需要对字符串进行稍微的处理,然后拿到相应的用户名(pxc)和密码(000000)

    3. 拿到username和password后需要调用userService中的`verify(username, password)`进行验证.

    4. 验证成功后需要将userData进行MD5加密计算出token,加入到cookie中(key为user), 然后在session中添加一个属性, 其key为__计算出来的token__, 其value为对应的用户的User实体

  - __返回值__

    类型: JsonResponse

    登录成功时,返回`new JsonResponse(302, "/api/user/properties/pNo/1/pSz/10")`

    登录失败时,返回`new JsonResponse(302, "/api/auth/error")`

  ---

- __管理员登录__

  - __URL__

    `/api/auth/managers`

  - __API接口说明__

    使用的Http Method为POST

    需要加上__`@ResponseBody`__

    参数

    ​	管理员的登录信息`@RequestParam(value="info") String info`

    ​	Request`HttpServletRequest request`

    ​	Response`HttpServletResponse response`

  - __功能说明__

    1. 前台传回来的用户名和密码的BASE64加密__3次__后的数据保存在info中,解码三次后得到相应的账号密码数据为userData, 其格式为

       ```
       username:pxc
       password:000000
       ```

    2. 需要对字符串进行稍微的处理,然后拿到相应的用户名(pxc)和密码(000000)

    3. 拿到username和password后需要调用userService中的`verify(username, password)`进行验证.

    4. 验证成功后需要将userData进行MD5加密计算出token,加入到cookie中(key为manager),然后在session中添加一个属性, 其key为__计算出来的token__, 其value为对应的Manager的Manager实体

  - __返回值__

    类型: JsonResponse

    登录成功时,返回`new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10")`

    登录失败时,返回`new JsonResponse(302, "/api/auth/error")`

  ---

- 登录错误页面

  - __URL__

    `/api/auth/error`

  - __API接口说明__

    使用的Http Method为GET

    不需要使用__`@ResponseBody`__

  - __功能说明__

    直接返回视图`loginError`即可

  - __返回值__

    类型: String

    返回`logout`

  ---

- 管理员退出页面

  - __URL__

    `/api/auth/logout/manager`

  - __API接口说明__

    使用的Http Method为GET

    不需要使用__`@ResponseBody`__

  - __功能说明__

    删除session中的manager,然后返回视图`logout`

  - __返回值__

    类型: String

    返回`logout`

  ---

- 用户退出页面

  - __URL__

    `/api/auth/logout/user`

  - __API接口说明__

    使用的Http Method为GET

    不需要使用__`@ResponseBody`__

    参数

    ​	Session`HttpSession session`

  - __功能说明__

    删除session中的manager,然后返回视图`logout`

  - __返回值__

    类型: String

    返回`logout`

  ---

##### 2. ManagerController

所有ManagerController的URL前缀均为__`/api/manager`__

---

- 获取当前管理员的信息

  - __URL__

    `/api/manager/self`

  - __API接口说明__

    使用的Http Method是GET

    需要添加__`@ResponseBody`__

    参数

    ​	Request`HttpServletRequest request`

  - __功能说明__

    返回当前管理员的信息(只包括管理员用户名, 手机和邮箱),对这些信息需要__使用Base64加密3次再返回__

  - __返回值__

    返回`new JsonResponse(200, DataUtil.encodeBase64(info, 3));`

  ---

- 修改自己的信息

  - __URL__

    `/api/manager/self`

  - __API接口说明__

    使用的Http Method是PUT

    需要添加__`@ResponseBody`__

    参数

    ​	json格式的manager数据`@RequestBody JsonManager jsonManager`

    ​	Request`HttpServletRequest request`

  - __功能说明__

    更新当前的Manager的信息, 用户名不可以更新

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(200, "更新成功")`

  ---

- 用户管理视图

  - __URL__

    `/api/manager/users/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method是GET

    不需要使用__`@ResponseBody`__

    参数: 

    ​	页号`@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据pageSz和pageNo从数据库中拿到相应的分页用户数据,然后加到request中(key值是users, value是一个`List<User>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    返回`manager/userManage`

  ---

- 用户详情视图

  - __URL__

    `/api/manager/users/id/{id}/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method为GET

    不需要使用__`@ResponseBody`__

    参数:

    ​	用户Id: `@PathVariable Integer id`

    ​	页号: `@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据id从数据库中拿到相应的用户,然后加到request中(key值是user, value是相应的User实体)

    根据pageSz和pageNo从数据库中拿到相应的申请单数据,然后加入到request中(key值是applications, value是一个`List<Application>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    当用户不存在时,返回`redirect:/404`;

    否则返回`manager/userDetail`

  ---

- 增加用户

  - __URL__

    `/api/manager/users`

  - __API接口说明__

    使用的Http Method是POST

    需要添加__`@ResponseBody`__

    参数

    ​	json格式的User`@RequestBody JsonUser jsonUser`

  - __功能说明__

    添加一个用户到数据库中, 如果用户名已经存在则什么不添加

  - __返回值__

    类型: JsonResponse

    添加成功返回`new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10")`

    添加失败返回`new JsonResponse(403, "/api/manager/users/pNo/1/pSz/10")`

  ---

- 删除用户

  - __URL__

    `/api/manager/users`

  - __API接口说明__

    使用的Http Method是DELETE

    需要添加__`@ResponseBody`__

    参数

    ​	删除用户的列表`@RequestBody List<Integer> ids`

  - __功能说明__

    将这些用户的status修改为-1,如果某一个用户不存在,则不对其进行操作

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10")`

  ---

- 得到用户的详情信息

  - __URL__

    `/api/manager/users/id/{id}`

  - __API接口说明__

    使用的Http Method是GET

    需要添加__`@ResponseBody`__

    参数

    ​	用户Id`@PathVariable Integer id`

  - __功能说明__

    从数据库中读取Id对应的用户信息, 只把用户的用户名,邮箱返回即可. 对这些信息需要__使用Base64加密3次再返回__

  - __返回值__

    类型: JsonResponse

    用户存在, 则返回`new JsonResponse(200, DataUtil.encodeBase64(info, 3));`

- 更新用户的状态

  - __URL__

    `/api/manager/users/id/{id}/status/{status}`

  - __API接口说明__

    使用的Http Method是PATCH

    需要添加__`@ResponseBody`__

    参数

    ​	用户Id`@PathVariable Integer id`

    ​	目标状态`@PathVariable Integer status`

  - __功能说明__

    将id对应的用户的状态修改为status, 如果id对应的用户不存在,则不对其进行操作

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/users/pNo/1/pSz/10");`

  ---

- 更新用户的信息

  - __URL__

    `/api/manager/users/id/{id}`

  - __API接口说明__

    使用的Http Method是PUT

    需要添加__`@ResponseBody`__

    参数

    ​	用户的Id`@PathVariable Integer id`

    ​	用户的信息`@RequestBody JsonUser jsonUser`

  - __功能说明__

    修改数据库中id对应的User

  - __返回值__

    类型: JsonResponse

    如果id对应的用户不存在,则返回`new JsonResponse(404, "用户不存在")`

    更新成功, 则返回`new JsonResponse(200, "更新成功")`

- 资产管理视图

  - __URL__

    `/api/manager/properties/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method是GET

    不需要添加__`@ResponseBody`__

    参数

    ​	页号`@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据pageSz和pageNo从数据库中拿到相应的分页资产数据,然后加到request中(key值是properties, value是一个`List<Property>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    返回视图`manager/propertyManage`

  ---

- 增加资产

  - __URL__

    `/api/manager/properties`

  - __API接口说明__

    使用的Http Method是POST

    需要添加__`@ResponseBody`__

    参数

    ​	Json格式的Property数据`@RequestBody JsonProperty jsonProperty`

  - __功能说明__

    向数据库中添加一个资产信息

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/properties/pNo/1/pSz/10")`

  ---

- 删除资产

  - __URL__

    `/api/manager/properties/id/{id}`

  - __API接口说明__

    使用的Http Method是DELETE

    需要添加__`@ResponseBody`__

    参数:

    ​	资产Id`@PathVariable Integer id`

  - __功能说明__

    将数据库中资产的status置为-1, 不存在则不修改

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/properties/pNo/1/pSz/10")`

  ---

- 查看资产细节

  - __URL__

    `/api/manager/properties/id/{id}/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method是GET

    不需要添加__`@ResponseBody`__

    参数

    ​	资产Id`@PathVariable Integer id`

    ​	页号: `@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据id查找相应的property信息, 然后添加到request中(key为property, value为相应的Property实体)

    根据pageSz和pageNo从数据库中拿到相应的申请数据,然后添加到request中(key为applications, value为`List<Application>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    如果能够查找到property,则返回`manager/propertyDetail`

    查找不到则返回`redirect:/404`

  ---

- 审核视图

  - __URL__

    `/api/manager/applications/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method为GET

    不需要添加__`@ResponseBody`__

    参数:

    ​	页号: `@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据pageSz和pageNo从数据库中拿到相应的分页资产数据,然后加到request中(key值是applications, value是一个`List<Application>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型:String

    返回`manager/applicationManage`

  ---

- 审核申请单

  - __URL__

    `/api/manager/applications/id/{id}/status/{status}`

  - __API接口说明__

    使用的Http Method为PATCH

    需要添加__`@ResponseBody`__

    参数:

    ​	申请单id`@PathVariable Integer id`

    ​	状态`@PathVariable Integer status`

  - __功能说明__

    更新id对应的申请单的状态为status, 如果该申请单不存在,则不做任何事情

    如果原来的申请状态为0并且status == 1, 那么通过,同时修改reviewTime为当前时间, 修改manager为当前登录的manager

    如果原来的申请状态为0并且status == -1, 那么关闭,同时修改reviewTime为当前时间, endTime为当前时间

    __判断一下operation是0还是1,然后进行相关的数据库的操作__

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/uses/pNo/1/pSz/10")`

- 管理员管理视图

  - __URL__

    `/api/manager/managers/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method为GET

    不需要添加__`@ResponseBody`__

    参数:

    ​	页号: `@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据pageSz和pageNo从数据库中拿到相应的分页管理员数据,然后加到request中(key值是managers, value是一个`List<Manager>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    返回视图`manager/managerManage`

  ---

- 添加管理员

  - __URL__

    `/api/manager/managers`

  - __API接口说明__

    使用的Http Method为POST

    需要添加__`@ResponseBody`__

    参数

    ​	json格式的manager数据`@RequestBody JsonManager jsonManager`

  - __功能说明__

    将jsonManager对应的manager添加到数据库中, 需要检测名字是否冲突

  - __返回值__

    类型: JsonResponse

    添加成功,返回`new JsonResponse(302, "/api/manager/managers/pNo/1/pSz/10")`

    添加失败,返回`new JsonResponse(403, "/api/manager/managers/pNo/1/pSz/10")`

  ---

- 删除管理员

  - __URL__

    `/api/manager/managers`

  - __API接口说明__

    使用的Http Method为DELETE

    需要添加__`@ResponseBody`__

    参数

    ​	待删除的管理员的Id列表`@RequestBody List<Integer> ids`

  - __功能说明__

    删除数据库中管理员Id在ids中的管理员, 需要注意的是,不能把自己删除了,对于不存在的Id忽略即可

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/managers/pNo/1/pSz/10")`

  ---

- 更新管理员的状态

  - __URL__

    `/api/manager/id/{id}/status/{status}`

  - __API接口说明__

    使用的Http Method为PATCH

    需要添加__`@ResponseBody`__

    参数

    ​	管理员的Id`@PathVariable Integer id` 

    ​	状态`@PathVariable Integer status`

  - __功能说明__

    如果id对应的manager为当前登录用户或者不存在,不进行操作

    如果status == -1, 不可以进行操作

    否则更新即可

  - __返回值__

    类型: JsonResponse

    返回`new JsonResponse(302, "/api/manager/managers/pNo/1/pSz/10")`

  ---

##### 3. UserController

所有UserController的URL前缀均为__`/api/user`__

---

- 资产查看的用户视图

  - __URL__

    `/api/user/properties/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method为GET

    不需要添加__`@ResponseBody`__

    参数

    ​	页号`@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

  - __功能说明__

    根据pageSz和pageNo从数据库中拿到相应的分页资产数据,然后加到request中(key值是properties, value是一个`List<Property>`)(注意如果资产的User不为Null,不需要展示给用户)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    返回`user/userViewProperties`

  ---

- 申请/归还资产

  - __URL__

    `/api/user/properties/id/{id}/op/{operation}`

  - __API接口说明__

    使用的Http Method为POST

    需要添加__`@ResponseBody`__

    参数

    ​	资产的Id`@PathVariable Integer id`

    ​	操作`@PathVariable Integer operation`

    ​	Request`HttpServletRequest request`

  - __功能说明__

    如果operation为1并且id对应的资产没有人使用中,且该用户没有一个审核中的对这个资产发起的申请单,那么添加一个申请单

    如果operation为0并且id对应的资产使用人是当前User,且该资产确实是被当前的User使用申,那么添加一个申请单

  - __返回值__

    类型: JsonResponse

    添加成功, 返回`new JsonResponse(302, "/api/user/properties/pNo/1/pSz/10")`

    添加失败, 返回`new JsonResponse(403, "/api/user/properties/pNo/1/pSz/10")`

  ---

- 浏览自己的申请

  - __URL__

    `/api/user/applications/pNo/{pageNo}/pSz/{pageSz}`

  - __API接口说明__

    使用的Http Method为GET

    不需要添加__`@ResponseBody`__

    参数

    ​	页号`@PathVariable Integer pageNo`

    ​	页面的最大容量`@PathVariable Integer pageSz`

    ​	Request`HttpServletRequest request`

  - __功能说明__

    根据pageSz和pageNo从数据库中拿到当前用户的申请记录,然后加到request中(key值是applications, value是一个`List<Application>`)

    如果pageSz超过10,强制设置它为10,pageNo如果超过最大页数,强制设置它为最后一页

  - __返回值__

    类型: String

    返回`user/userViewUses`

  ---