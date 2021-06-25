package com.rookie.util.tag.user;

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
 * UserProperty中列表的SimpleTag处理类
 */
public class UPListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
        HttpSession session = request.getSession();
        JspWriter jspWriter =getJspContext().getOut();
        List<Property> properties = (List<Property>) request.getAttribute("properties");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (properties != null) {
            for (Property property : properties) {
                jspWriter.write(
                        "<tr>\n" +
                                "<td>" + property.getpId() + "</td>\n" +
                                "<td>" + property.getpName() + "</td>\n" +
                                "<td>" + property.getpBrand() + "</td>\n" +
                                "<td>" + property.getpModel() + "</td>\n" +
                                "<td>" + property.getpSpec() + "</td>\n" +
                                "<td>" + dateFormat.format(property.getpTime()) + "</td>\n" +
                                "<td><el-link type=\"primary\" @click=\"applyProperty(" + property.getpId() + ")\">申请</el-link></td>\n" +
                                "</tr>\n"
                );
            }
        }
    }
}
