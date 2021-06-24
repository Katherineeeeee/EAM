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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * UserDetail中列表的SimpleTag处理类
 * @author 庞新程
 * @version 1.0
 */
public class UDListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        //Todo
    }

    private String getStatus(Application application) {
        //Todo
    }
}
