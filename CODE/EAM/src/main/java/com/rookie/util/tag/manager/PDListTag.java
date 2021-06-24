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
 * ManagerManage中列表的SimpleTag处理类
 */
public class PDListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        //Todo
    }

    private String getStatus(Application application) {
        //Todo
    }
}
