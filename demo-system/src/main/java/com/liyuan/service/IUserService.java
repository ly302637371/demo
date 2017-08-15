package com.liyuan.service;


import com.liyuan.base.PagingBean;
import com.liyuan.entity.User;

import java.util.List;
import java.util.Map;


public interface IUserService extends IBaseService<User> {
    /**
     * 分页查询用户信息
     *
     * @param query
     * @param firstResult
     * @param maxResult
     * @return
     */
    public List<User> getByPage(String query, String orgId, int firstResult, int maxResult);

    /**
     * 获取记录数
     *
     * @return
     */
    public long getCount(String query, String orgId);

    /**
     * 物理删除用户
     *
     * @param id
     */
    public void relDelete(String id);

    /**
     * 解除锁定
     *
     * @param userId
     */
    public void unLocked(String userId);

    /**
     * 更新用户角色
     *
     * @param userId
     * @param node
     */
    public void updateRoleForUser(String userId, String[] node);

    /**
     * 账户名密码查询用户
     *
     * @param userName
     * @return
     */
    public User getUser(String userName);

    /**
     * 账户名查找用户
     *
     * @param userAccent
     * @return
     */
    public User findUserByCode(String userAccent);

    /**
     * 分页查询用户
     *
     * @param pageing
     * @param user
     * @return
     */
    PagingBean<User> queryList(PagingBean<User> pageing, User user);

    /**
     * 分页查询用户
     *
     * @param pageing
     * @param user
     * @return
     */
    PagingBean<User> queryByAddSql(PagingBean<User> pageing, User user, String additionalQuery);

//    /**
//     * 查询组织架构人员树
//     * @return
//     */
//    public List<Map> queryJsonTreeMap(Orgs orgs);

    /**
     * 分頁自定義查詢
     * @param pageing
     * @param user
     * @param parame
     * @return
     */
    PagingBean<User> queryUserByPageCustom(PagingBean<User> pageing, User user, String parame);

    /**
     * 根據id查詢用戶
     *
     * @param id
     * @return
     */
    User findUserById(String id);
}
