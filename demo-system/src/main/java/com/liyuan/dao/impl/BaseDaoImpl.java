package com.liyuan.dao.impl;

import com.liyuan.base.PagingBean;
import com.liyuan.base.PlatformConstant;
import com.liyuan.dao.IBaseDao;
import com.liyuan.utils.PojoUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Long getCount(String hql) {
        Query query = getSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }
    @Override
    public Long getCount(String hql,List<Object> params) {
        Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        return (Long) query.uniqueResult();
    }
    @Override
    public T queryById(Class<T> entityClass, Serializable id) {
        return (T) getSession().get(entityClass, id);
    }

    @Override
    public void save(T t) {
        try {
            PojoUtil.setSysProperties(t, PlatformConstant.OpType.insert);
        } catch (Exception e) {
           e.printStackTrace();
            System.out.print("没有继承BeanBase:" + t.getClass());
        } finally {
            getSession().save(t);
        }
    }

    @Override
    public void save(List<T> t) {
        for (T obj : t) {
            try {
                PojoUtil.setSysProperties(obj, PlatformConstant.OpType.insert);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("没有继承BeanBase:" + obj.getClass());
            } finally {
                getSession().save(obj);
            }
        }
    }

    @Override
    public void merge(T t) {
        try {
            PojoUtil.setSysProperties(t, PlatformConstant.OpType.insert);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("没有继承BeanBase:" + t.getClass());
        } finally {
            getSession().merge(t);
        }
    }

    @Override
    public void saveUpdate(T t) {
        try {
            PojoUtil.setSysProperties(t, PlatformConstant.OpType.update);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("没有继承BeanBase:" + t.getClass());
        } finally {
            getSession().saveOrUpdate(t);
        }
    }

    @Override
    public void update(T t) {
        try {
            PojoUtil.setSysProperties(t, PlatformConstant.OpType.update);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("没有继承BeanBase:" + t.getClass());
        } finally {
            getSession().update(t);
        }
    }

    @Override
    public void update(T t,PlatformConstant.OpType opType) {
        try {
            PojoUtil.setSysProperties(t, opType);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("没有继承BeanBase:" + t.getClass());
        } finally {
            getSession().update(t);
        }
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }
    @Override
    public void delete(List<T> t) {
        for (T obj:t) {
            getSession().delete(obj);
        }
    }
    @Override
    public void delete(Class<T> entityClass, Serializable id) {
        getSession().delete(getSession().get(entityClass,id));
    }

    @Override
    public List<T> queryForList(String hql) {
        return queryForList(hql, new Object[]{});
    }

    @Override
    public List<T> queryForList(String hql, Object param) {
        return queryForList(hql, new Object[]{param});

    }

    @Override
    public List<T> queryForList(String hql, Object[] params) {
        Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        return (List<T>) query.list();
    }

    @Override
    public List<T> queryForListSQL(Class<T> entityClass, String sql, Object[] params, int firstResult, int maxResult) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        query.addEntity(entityClass);
        return query.list();
    }
    @Override
    public List<Map> queryForListSQL(String sql, Object[] params, int firstResult, int maxResult) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.list();
    }
    @Override
    public List<Map> queryForListSQL(String sql, Object[] params) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        return query.list();
    }
    @Override
    public List<T> queryForListSQL(Class<T> entityClass,String sql, Object[] params) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
        query.addEntity(entityClass);
        return query.list();
    }

    @Override
    public T queryForObject(String hql, Object[] params) {
        Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> findByHql(String hql) {
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<T> findByPage(String hql, int firstResult, int maxResult) {
        return findByPage(hql, new Object[]{}, firstResult, maxResult);
    }

    @Override
    public List<T> findByPage(String hql, Object param, int firstResult, int maxResult) {
        return findByPage(hql, new Object[]{param}, firstResult, maxResult);

    }

    @Override
    public List<T> findByPage(String hql, Object[] params, int firstResult, int maxResult) {
        Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return (List<T>) query.list();

    }

    private void setQueryParams(Query query, Object[] params) {
        if (null == params) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
    }
    private void setQueryParams(Query query, List<Object> params) {
        if (null == params||params.size()<1) {
            return;
        }
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
    }

    @Override
    public Integer getCountBySql(String sql) {
        SQLQuery query = getSession().createSQLQuery(sql);
        return Integer.valueOf(query.uniqueResult().toString()) ;
    }

    @Override
    public Integer getCountBySql(String sql, Object[] params) {
        SQLQuery query = getSession().createSQLQuery(sql);
        setQueryParams(query, params);
        return Integer.valueOf(query.uniqueResult().toString()) ;
    }

    @Override
    public int updateBySql(String sql, Object[] params) {
        SQLQuery query = getSession().createSQLQuery(sql);
        setQueryParams(query, params);
        return query.executeUpdate();
    }

    @Override
    public int updateByHql(String hql, Object[] params) {
        Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        return query.executeUpdate();
    }

    @Override
    public PagingBean<T> findByPage(String hql, Object[] objs, PagingBean<T> pb) {
        int totalItems = getTotalItems(hql, objs).intValue();
        pb.setTotal(totalItems);
        pb.setRows(findByPage(hql, objs, pb.getFirstResult(), pb.getPageSize().intValue()));
        return pb;
    }

    @Override
    public Integer getTotalItems(String queryString, Object[] values) {
        int orderByIndex = queryString.toUpperCase().indexOf(" ORDER BY ");

        if (orderByIndex != -1) {
            queryString = queryString.substring(0, orderByIndex);
        }

        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(queryString, queryString, Collections.EMPTY_MAP, (SessionFactoryImplementor) sessionFactory);
        queryTranslator.compile(Collections.EMPTY_MAP, false);
        final String sql = "select count(*) from (" + queryTranslator.getSQLString() + ") tmp_count_t";
        SQLQuery query = getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public Object findUnique(String hql, Object[] values) {
        Query query = getSession().createQuery(hql);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.uniqueResult();
    }

    @Override
    public void prepareCallNoReturn(final String callSql, final Object[] values) {
        Session session = getSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                CallableStatement cs = connection.prepareCall(callSql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        cs.setObject(i+1, values[i]);
                    }
                }
                cs.executeUpdate();
            }
        });
    }

    @Override
    public String prepareCallAndReturn(final String callSql,final Object[] values) {
        Session session = getSession();
        return session.doReturningWork(new ReturningWork<String>() {
            @Override
            public String execute(Connection connection) throws SQLException {
                CallableStatement cs = connection.prepareCall(callSql);
                int inParametersLength = values.length;
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        cs.setObject(i+1, values[i]);
                    }
                }
                cs.registerOutParameter(inParametersLength+1, Types.VARCHAR);
                cs.executeUpdate();
                return cs.getString(inParametersLength+1);
            }
        });
    }
}
