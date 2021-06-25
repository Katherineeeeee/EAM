package com.rookie.util.tag.manager;

import com.rookie.model.Application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
/**
 * ApplicationManage中列表的SimpleTag处理器
 */
public class AMListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
        HttpSession session = request.getSession();
        JspWriter jspWriter =getJspContext().getOut();
        List<Application> applications = (List<Application>) request.getAttribute("applications");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (applications != null) {
            for (Application application : applications) {
                jspWriter.write(
                        "<tr>\n" +
                                "<td>" + application.getaId() + "</td>\n" +
                                "<td>" + application.getProperty().getpName() + "</td>\n" +
                                "<td>" + application.getUser().getuName() + "</td>\n" +
                                "<td>" + dateFormat.format(application.getBeginTime()) + "</td>\n" +
                                "<td>" + getOp(application) + "</td>\n" +
                                "<td>" +
                                "<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"reviewApplication(" + application.getaId() + ", 1)\">同意</el-link> " +
                                "<el-link style=\"font-size: 16px;\" type=\"primary\" @click=\"reviewApplication(" + application.getaId() + ", -1)\">驳回</el-link>" +
                                "</td>\n" +
                                "</tr>\n"
                );
            }
        }
    }

    private String getOp(Application application) {
        if (application.getOperation() == 1) {
            return "申请";
        } else {
            return "归还";
        }
    }
}
