package com.liyuan.base;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PagingBean<T> {
    public static Integer DEFAULT_PAGE_SIZE = Integer.valueOf(15);
    public Integer start;
    private Integer pageSize;
    private Integer total;
    List<T> rows = new ArrayList<T>();

    public PagingBean(int start, int limit) {
        this.pageSize = Integer.valueOf(limit);
        this.start = Integer.valueOf(start);
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Integer.valueOf(pageSize);
    }

    public int getTotal() {
        return this.total.intValue();
    }

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setTotal(int total) {
        this.total = Integer.valueOf(total);
    }

    public int getFirstResult() {
        return this.start;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public static <T> PagingBean<T> fromRequest(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        int pageInt = StringUtils.isEmpty(pageStr) ? 1 : Integer.parseInt(pageStr);
        int rowsInt = StringUtils.isEmpty(rowsStr) ? 10 : Integer.parseInt(rowsStr);
        int startRow = (pageInt - 1 < 1) ? 0 : (pageInt - 1) * rowsInt;
        PagingBean page = new PagingBean(startRow, rowsInt);
        return page;
    }
    public static <T> PagingBean<T> fromRequest(Class<T> tClass,HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        int pageInt = StringUtils.isEmpty(pageStr) ? 1 : Integer.parseInt(pageStr);
        int rowsInt = StringUtils.isEmpty(rowsStr) ? 10 : Integer.parseInt(rowsStr);
        int startRow = (pageInt - 1 < 1) ? 0 : (pageInt - 1) * rowsInt;
        PagingBean<T> page = new PagingBean(startRow, rowsInt);
        return page;
    }
}