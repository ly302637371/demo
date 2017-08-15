package com.liyuan.service.impl;


import com.liyuan.base.PagingBean;
import com.liyuan.dao.IBaseDao;
import com.liyuan.service.IBaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
public class BaseServiceImpl<T> implements IBaseService<T> {

    protected IBaseDao<T> dao = null;
    public void setDao(IBaseDao dao) {
        this.dao = dao;
    }

    public BaseServiceImpl(IBaseDao dao) {
        this.dao = dao;
    }

    @Override
    public T queryById(Class<T> entityClass, Serializable id) {
        return dao.queryById(entityClass,id);
    }

    @Override
    public void save(T t) {
        dao.save(t);
    }

    @Override
    public void saveUpdate(T t) {
        dao.saveUpdate(t);
    }

    @Override
    public void update(T t) {
        dao.update(t);
    }

    @Override
    public void delete(T t) {
        dao.delete(t);
    }

    @Override
    public void delete(Class<T> entityClass, Serializable id) {
        dao.delete(entityClass,id);
    }

    @Override
    public List<T> queryForList(String hql) {
        return dao.queryForList(hql);
    }

    @Override
    public List<T> queryForList(String hql, Object param) {
        return dao.queryForList(hql,param);
    }

    @Override
    public List<T> queryForList(String hql, Object[] params) {
        return dao.queryForList(hql,params);
    }

    @Override
    public List<T> queryForListSQL(Class<T> entityClass,String sql, Object[] params) {
        return dao.queryForListSQL(entityClass,sql,params);
    }

    @Override
    public List<T> queryForListSQL(Class<T> entityClass,String sql, Object[] params, int firstResult, int maxResult) {
        return dao.queryForListSQL(entityClass,sql,params,firstResult*maxResult,maxResult);
    }
    @Override
    public List<Map> queryForListSQL(String sql, Object[] params, int firstResult, int maxResult) {
        return dao.queryForListSQL(sql,params,firstResult,maxResult);
    }
    @Override
    public T queryForObject(String hql, Object[] params) {
        return dao.queryForObject(hql,params);
    }

    @Override
    public List<T> findByHql(String hql) {
        return dao.findByHql(hql);
    }

    @Override
    public List<T> findByPage(String hql, int firstResult, int maxResult) {
        return dao.findByPage(hql,firstResult*maxResult,maxResult);
    }

    @Override
    public List<T> findByPage(String hql, Object param, int firstResult, int maxResult) {
        return dao.findByPage(hql,param,firstResult*maxResult,maxResult);
    }

    @Override
    public List<T> findByPage(String hql, Object[] params, int firstResult, int maxResult) {
        return dao.findByPage(hql,params,firstResult*maxResult,maxResult);
    }

    @Override
    public Long getCount(String hql) {
        return dao.getCount(hql);
    }
    @Override
    public Long getCountByParams(String hql,Object[] params) {
        return Long.parseLong(dao.getTotalItems(hql,params)+"");
    }

    @Override
    public Integer getCountBySql(String sql) {
        return dao.getCountBySql(sql);
    }

    @Override
    public Integer getCountBySql(String sql, Object[] params) {
        return dao.getCountBySql(sql,params);
    }

    @Override
    public int updateBySql(String sql, Object[] params) {
        return dao.updateBySql(sql,params);
    }

    @Override
    public PagingBean<T> findByPage(String hql, Object[] objs, PagingBean<T> pb) {
        return dao.findByPage(hql,objs,pb);
    }

    @Override
    public Long getTotalItems(String queryString, Object[] values) {
        return (long)dao.getTotalItems(queryString,values);
    }

    @Override
    public Object findUnique(String hql, Object[] values) {
        return dao.findUnique(hql,values);
    }
}
