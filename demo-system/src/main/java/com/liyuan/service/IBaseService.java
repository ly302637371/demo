package com.liyuan.service;



import com.liyuan.base.PagingBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
public interface IBaseService<T> {
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
     * 删除实体
     *
     * @param t
     */
    public void delete(T t);

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
     * sql带参数查询
     *
     * @param sql
     * @param params
     * @return
     */
    public List<T> queryForListSQL(Class<T> entityClass, String sql, Object[] params);
    /**
     * sql带参数分页查询
     *
     * @param sql
     * @param params
     * @return
     */
    public List<T> queryForListSQL(Class<T> entityClass, String sql, Object[] params, int firstResult, int maxResult);
    /**
     * sql带参数分页查询
     *
     * @param sql
     * @param params
     * @return
  */
    public List<Map> queryForListSQL(String sql, Object[] params, int firstResult, int maxResult);

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
     * @param page
     * @param pageSize
     * @return
     */
    public List<T> findByPage(String hql, int page, int pageSize);

    /**
     * 分页查询,单个参数
     * @param hql
     * @param param
     * @param page
     * @param pageSize
     * @return
     */
    public List<T> findByPage(String hql, Object param, int page, int pageSize);

    /**
     * 分页查询,多个参数
     * @param hql
     * @param params
     * @param page
     * @param pageSize
     * @return
     */
    public List<T> findByPage(String hql, Object[] params, int page, int pageSize);

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
    public Long getCountByParams(String hql, Object[] params);

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
    public Long getTotalItems(String queryString, final Object[] values);

    /**
     *
     * @param hql
     * @param values
     * @return
     */
    public Object findUnique(final String hql, final Object[] values);
}
