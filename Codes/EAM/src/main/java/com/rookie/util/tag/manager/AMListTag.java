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
        //Todo
    }

    private String getOp(Application application) {
        if (application.getOperation() == 1) {
            return "申请";
        } else {
            return "归还";
        }
    }
}
