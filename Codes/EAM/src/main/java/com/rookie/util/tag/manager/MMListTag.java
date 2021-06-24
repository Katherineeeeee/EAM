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
        //Todo
    }

    private String getStatus(Manager manager) {
        if (manager.getMStatus() == 1) {
            return "激活";
        } else {
            return "删除";
        }
    }

    private String generateOp(Manager manager) {
        //Todo
    }
}
