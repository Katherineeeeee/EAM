package com.rookie.util.tag.user;

import com.rookie.model.Application;
import com.rookie.model.Property;

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
 * UserApplication中列表的SimpleTag处理类
 */
public class UAListTag extends SimpleTagSupport {

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
//                System.out.println(application.getProperty().getpId());
                jspWriter.write(
                        "<tr>\n" +
                                "<td>" + application.getProperty().getpId() + "</td>\n" +
                                "<td>" + application.getProperty().getpName() + "</td>\n" +
                                "<td>" + application.getProperty().getpBrand() + "</td>\n" +
                                "<td>" + application.getProperty().getpModel() + "</td>\n" +
                                "<td>" + application.getProperty().getpSpec() + "</td>\n" +
                                "<td>" + dateFormat.format(application.getProperty().getpTime()) + "</td>\n" +
                                "<td><el-link type=\"primary\" @click=\"returnProperty(" + application.getaId() + ")\">归还</el-link></td>\n" +
                                "</tr>\n"
                );
            }
        }
    }
}
