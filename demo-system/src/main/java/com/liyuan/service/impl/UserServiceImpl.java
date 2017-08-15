package com.liyuan.service.impl;

import com.liyuan.base.PagingBean;
import com.liyuan.dao.IUserDao;
import com.liyuan.entity.User;
import com.liyuan.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

//    @Autowired
//    IUserRoleDao userRoleDao;
    @Autowired
    IUserDao userDao;

    @Autowired
    public UserServiceImpl(IUserDao dao) {
        super(dao);
        userDao = dao;
    }

    @Override
    public List<User> getByPage(String query, String orgId, int firstResult, int maxResult) {
        String coodition = "";
        if (query != null && !"".equals(query)) {
            coodition += " and (userAccent like '%" + query + "%' or userName like '%" + query + "%')";
        }
        String hql = "from User where orgId='" + orgId + "'" + coodition + " and IFNULL(isDelate,0)<>1 order by creationDate desc";
        return userDao.findByPage(hql, new Object[]{}, firstResult * maxResult, maxResult);
    }

    @Override
    public long getCount(String query, String orgId) {
        String coodition = "";
        if (query != null && !"".equals(query)) {
            coodition += " and (userAccent like '%" + query + "%' or userName like '%" + query + "%')";
        }
        String hql = "select count(*) from User where orgId='" + orgId + "'" + coodition;
        return userDao.getCount(hql);
    }

    @Override
    public void relDelete(String id) {
        userDao.delete(User.class, id);
    }

    @Override
    public void unLocked(String userId) {
        String hql = "update User set locked=? where id=?";
        userDao.updateBySql(hql, new Object[]{false, userId});
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void updateRoleForUser(String userId, String[] node) {
//        String hql = "from UserRole where uId = '" + userId + "'";
//        List<UserRole> relations = userRoleDao.queryForList(hql);
//        for (UserRole relation : relations) {
//            userRoleDao.delete(relation);
//        }
//        if (node != null && !"".equals(node) && node.length > 0) {
//            for (String nodeStr : node) {
//                UserRole relation = new UserRole();
//                relation.setId(ComUtil.getId());
//                relation.setuId(userId);
//                relation.setrId(nodeStr);
//                userRoleDao.save(relation);
//            }
//        }
    }

    @Override
    public User getUser(String userName) {
        String hql = "from User where lower(userName)=?";
        return userDao.queryForObject(hql, new Object[]{userName.toLowerCase()});
    }

    @Override
    public User findUserByCode(String userAccent) {
        String hql = "from User where lower(userAccent)=?";
        return userDao.queryForObject(hql, new Object[]{userAccent.toLowerCase()});
    }

    @Override
    public PagingBean<User> queryList(PagingBean<User> pageing, User user) {
        StringBuffer hql = new StringBuffer();
        List<Object> param = new ArrayList<>();
        hql.append(" from User where IFNULL(isDelate,0)<>1 ");
        if (!StringUtils.isEmpty(user.getUserAccent())) {
            hql.append(" and userAccent like ? ");
            param.add("%" + user.getUserAccent() + "%");
        }
        hql.append(" order by creationDate desc ");
        pageing.setRows(userDao.findByPage(hql.toString(), param.toArray(), pageing.getFirstResult(), pageing.getPageSize()));
        pageing.setTotal(userDao.getTotalItems(hql.toString(), param.toArray()));
        return pageing;
    }

    @Override
    public PagingBean<User> queryByAddSql(PagingBean<User> pageing, User user, String additionalQuery) {
        StringBuffer hql = new StringBuffer();
        List<Object> param = new ArrayList<>();
        hql.append(" from User where IFNULL(isDelate,0)<>1 ");
        if (!StringUtils.isEmpty(user.getUserAccent())) {
            hql.append(" and userAccent like ? ");
            param.add("%" + user.getUserAccent() + "%");
        }
        hql.append(" and (" + additionalQuery + ")");
        hql.append(" order by creationDate desc ");
        pageing.setRows(userDao.findByPage(hql.toString(), param.toArray(), pageing.getFirstResult(), pageing.getPageSize()));
        pageing.setTotal(userDao.getTotalItems(hql.toString(), param.toArray()));
        return pageing;
    }

//    @Override
//    public List<Map> queryJsonTreeMap(Orgs orgs) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(" SELECT * from ( ");
//        sb.append(" SELECT id,org_name as name,ifnull(org_number,'') as num,org_parent_id as _parentId,org_type as types,ifnull(state,'') as state,id as orgId from sys_orgs ");
//        sb.append(" UNION ");
//        sb.append(" SELECT id,user_name as name,ifnull(user_accent,'') as num,org_id as _parentId,'-1' as types,'' as state,org_id as orgId from sys_user ");
//        sb.append(" ) e where e.name != '超级管理员' ");
//        List<Object> param = new ArrayList<>();
////        if(orgs != null){
////            if("2".equals(orgs.getOrgType())){
////                sb.append(" and or e.orgId = ? ");
////                param.add(orgs.getId());
////            }else if("1".equals(orgs.getOrgType())){
////                sb.append(" and e.orgId in (SELECT id from base_site_manage where belongs_college_id = ?) ");
////                param.add(orgs.getId());
////            }
////        }
//        sb.append(" ORDER BY e.types desc,e.num ");
//        return userDao.queryForListSQL(sb.toString(), param.toArray());
//    }

    @Override
    public PagingBean<User> queryUserByPageCustom(PagingBean<User> pageing, User user, String parame) {
        StringBuffer hql = new StringBuffer();
        List<Object> param = new ArrayList<>();
        hql.append(" from User where IFNULL(isDelate,0)<>1 ");
        if (!StringUtils.isEmpty(user.getUserAccent())) {
            hql.append(" and userAccent like ? ");
            param.add("%" + user.getUserAccent() + "%");
        }
        if (!StringUtils.isEmpty(parame)) {
            hql.append(" and ( 1=1 and " + parame + " ) ");
        }
        hql.append(" order by creationDate desc ");
        pageing.setRows(userDao.findByPage(hql.toString(), param.toArray(), pageing.getFirstResult(), pageing.getPageSize()));
        pageing.setTotal(userDao.getTotalItems(hql.toString(), param.toArray()));
        return pageing;
    }

    @Override
    public User findUserById(String id) {
        return userDao.queryById(User.class, id);
    }
}
