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
 * @version 1.0
 */
public class UMListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        //Todo
    }

    private String getStatus(User user) {
        //Todo
    }

    private String generateOp(User user) {
        //Todo
    }
}
