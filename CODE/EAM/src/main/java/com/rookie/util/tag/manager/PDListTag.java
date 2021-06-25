package com.rookie.util.tag.manager;

import com.rookie.model.Application;
import com.rookie.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * PropertyDetail中列表的SimpleTag处理类
 */
public class PDListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
        HttpSession session = request.getSession();
        JspWriter jspWriter =getJspContext().getOut();
        List<Application> applications = (List<Application>) request.getAttribute("applications");
        if (applications != null) {
            applications = new ArrayList<>(applications);
            Collections.sort(applications, new Comparator<Application>() {
                @Override
                public int compare(Application o1, Application o2) {
                    if (o1.getEndTime() == null) {
                        return -1;
                    }
                    if (o2.getEndTime() == null) {
                        return 1;
                    }
                    return (int)(o2.getEndTime().getTime() - o1.getEndTime().getTime());
                }
            });
            for (Application application : applications) {
                jspWriter.write(
                        "<tr>\n" +
                                "<td>" + application.getUser().getuName() + "</td>\n" +
                                "<td>" + application.getUser().getuEmail() + "</td>\n" +
                                "<td>" + getStatus(application) + "</td>\n" +
                                "</tr>\n"
                );
            }
        }
    }

    /**
     * 获取申请状态信息
     * @param application 申请对象
     * @return 状态信息
     */
    private String getStatus(Application application) {
        if (application.getaStatus() == 1) {
            return "<font color=\"#67C23A\">通过</font>";
        } else if (application.getaStatus() == -1) {
            return "<font color=\"#F56C6C\">驳回</font>";
        } else if (application.getaStatus() == 2) {
            return "<font color=\"#909399\">关闭</font>";
        } else {
            return "<font color=\"#409EFF\">审核中</font>";
        }
    }
}
