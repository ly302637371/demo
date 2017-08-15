package com.liyuan.dao;

import com.liyuan.base.PagingBean;
import com.liyuan.base.PlatformConstant;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author dx
 */
public interface IBaseDao<T> {
    public Session getSession();

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    public T queryById(Class<T> entityClass, Serializable id);

    /**
     * 保存实体
     *
     * @param t
     */
    public void save(T t);
    /**
     * 保存实体
     *
     * @param t
     */
    public void save(List<T> t);
    /**
     * 保存实体
     *
     * @param t
     */
    public void merge(T t);
    /**
     * 保存更新
     * @param t
     */
    public void saveUpdate(T t);

    /**
     * 更新实体
     *
     * @param t
     */
    public void update(T t);

    /**
     * 指定更新方式，主要用于删除操作
     * @param t
     * @param opType
     */
    public void update(T t, PlatformConstant.OpType opType);

    /**
     * 删除实体
     *
     * @param t
     */
    public void delete(T t);
    /**
     * 删除实体
     *
     * @param t
     */
    public void delete(List<T> t);

    /**
     * 删除实体By ID
     *
     * @param entityClass
     * @param id
     */
    public void delete(Class<T> entityClass, Serializable id);

    /**
     * HQL查询实体
     *
     * @param hql
     * @return
     */
    public List<T> queryForList(String hql);

    /**
     * 单个参数查询实体
     *
     * @param hql
     * @param param
     * @return
     */
    public List<T> queryForList(String hql, Object param);

    /**
     * 多个参数查询实体
     *
     * @param hql
     * @param params
     * @return
     */
    public List<T> queryForList(String hql, Object[] params);

    /**
     * sql带参数分页查询
     * @param entityClass
     * @param sql
     * @param params
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<T> queryForListSQL(Class<T> entityClass, String sql, Object[] params, int firstResult, int maxResult);
    /**
     * sql带参数查询
     * @param sql
     * @param params
     * @return
     */
    public List<Map> queryForListSQL(String sql, Object[] params, int firstResult, int maxResult);
    /**
     * sql带参数查询
     * @param sql
     * @param params
     * @return
     */
    public List<Map> queryForListSQL(String sql, Object[] params);


    /**
     * sql带参数查询
     * @param entityClass
     * @param sql
     * @param params
     * @return
     */
    public List<T> queryForListSQL(Class<T> entityClass, String sql, Object[] params);
    /**
     * 查询单个实体
     *
     * @param hql
     * @param params
     * @return
     */
    public T queryForObject(String hql, Object[] params);

    /**
     * HQL分页查询
     * @param hql
     * @return
     */
    public List<T> findByHql(String hql);
    /**
     * 分页查询,不传参数
     *
     * @param hql
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<T> findByPage(String hql, int firstResult, int maxResult);

    /**
     * 分页查询,单个参数
     * @param hql
     * @param param
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<T> findByPage(String hql, Object param, int firstResult, int maxResult);

    /**
     * 分页查询,多个参数
     * @param hql
     * @param params
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<T> findByPage(String hql, Object[] params, int firstResult, int maxResult);


    /**
     * 获取记录总数
     *
     * @param hql
     * @return
     */
    public Long getCount(String hql);
    /**
     * 获取记录总数
     *
     * @param hql
     * @return
     */
    public Long getCount(String hql, List<Object> params);
    /**
     * 通过SQL查询记录数
     *
     * @param sql
     * @return
     */
    public Integer getCountBySql(String sql);
    /**
     * 通过SQL查询记录数
     *
     * @param sql
     * @return
     */
    public Integer getCountBySql(String sql, Object[] params);

    /**
     * 执行sql
     *
     * @param sql
     * @return
     */
    public int updateBySql(String sql, Object[] params);

    /**
     * 执行hql
     *
     * @param hql
     * @return
     */
    public int updateByHql(String hql, Object[] params);
    /**
     * 分页查询,返回总数和列表
     * @param hql
     * @param objs
     * @param pb
     * @return
     */
    public PagingBean<T> findByPage(String hql, Object[] objs, PagingBean<T> pb);

    /**
     * 返回总记录数
     * @param queryString
     * @param values
     * @return
     */
    public Integer getTotalItems(String queryString, final Object[] values);

    /**
     *
     * @param hql
     * @param values
     * @return
     */
    public Object findUnique(final String hql, final Object[] values);

    /**
     * 执行不带参数存储过程
     * @param callSql
     * @param values
     */
    public void prepareCallNoReturn(final String callSql, final Object[] values);
    /**
     * 执行带输出参数存储过程
     * @param callSql
     * @param values
     */
    public String prepareCallAndReturn(final String callSql, final Object[] values);
}
