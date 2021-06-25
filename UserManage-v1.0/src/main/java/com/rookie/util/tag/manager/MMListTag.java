package com.rookie.util.tag.manager;

import com.rookie.model.Manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * ManagerManage中列表的SimpleTag处理类
 */
public class MMListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
        HttpSession session = request.getSession();
        JspWriter jspWriter =getJspContext().getOut();
        List<Manager> managers = (List<Manager>) request.getAttribute("managers");
        if (managers != null) {
            for (Manager manager : managers) {
                jspWriter.write(
                        "<tr>\n" +
                                "<td align=\"center\"><el-checkbox @change=\"selectOne(" + manager.getmId() + ", $event)\"/></td>\n" +
                                "<td>" + manager.getmId() + "</td>\n" +
                                "<td>" + manager.getmName() + "</td>\n" +
                                "<td>" + manager.getmPhone() + "</td>\n" +
                                "<td>" + manager.getmEmail() + "</td>\n" +
                                "<td>" + getStatus(manager) + "</td>\n" +
                                "<td>" + generateOp(manager) + "</td>\n" +
                                "</tr>\n"
                );
            }
        }
    }

    private String getStatus(Manager manager) {
        if (manager.getMStatus() == 1) {
            return "激活";
        } else {
            return "删除";
        }
    }

    private String generateOp(Manager manager) {
        if (manager.getMStatus() == 1) {
            return "<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"updateManagerStatus(" + manager.getmId() + ", -1)\">删除</el-link>";
        } else {
            return "";
        }
    }
}
