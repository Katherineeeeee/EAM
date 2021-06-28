package com.rookie.util.tag.manager;

import com.rookie.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * UserManage中列表的SimpleTag处理器
 * @author 庞新程
 * @version 1.0
 */
public class UMListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
        HttpSession session = request.getSession();
        JspWriter jspWriter =getJspContext().getOut();
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null) {
            for (User user : users) {
                jspWriter.write(
                        "<tr>\n" +
                                "<td align=\"center\"><el-checkbox @change=\"selectOne(" + user.getuId() + ", $event)\"/></td>\n" +
                                "<td>" + user.getuId() + "</td>\n" +
                                "<td>" + user.getuName() + "</td>\n" +
                                "<td>" + user.getuEmail() + "</td>\n" +
                                "<td><el-link style=\"font-size: 16px;\" type=\"primary\" href=\"/api/manager/users/id/" + user.getuId() + "/pNo/1/pSz/5\">查看</el-link></td>\n" +
                                "<td>" + getStatus(user) + "</td>\n" +
                                "<td>" + generateOp(user) + "</td>\n" +
                                "</tr>\n"
                );
            }
        }
    }

    /**
     * 获得对应用户的当前状态
     * @param user 用户
     * @return
     */
    private String getStatus(User user) {
        int uStatus = user.getuStatus();
        if (uStatus == 1) {
            return "激活";
        } else if (uStatus == 0) {
            return "冻结";
        } else {
            return "删除";
        }
    }

    /**
     * 列表中对单独用户的操作前端
     * @param user
     * @return
     */
    private String generateOp(User user) {
        StringBuilder sbuf = new StringBuilder();
        int uStatus = user.getuStatus();
        if (uStatus == 1) {
            sbuf.append("<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"updateUser(" + user.getuId() + ")\">修改</el-link> ");
            sbuf.append("<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"updateUserStatus(" + user.getuId() + ", 0)\">冻结</el-link> ");
            sbuf.append("<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"updateUserStatus(" + user.getuId() + ", -1)\">删除</el-link>");
        } else if (uStatus == 0) {
            sbuf.append("<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"updateUserStatus(" + user.getuId() + ", 1)\">解冻</el-link> ");
            sbuf.append("<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"updateUserStatus(" + user.getuId() + ", -1)\">删除</el-link>");
        }
        return sbuf.toString();
    }
}
