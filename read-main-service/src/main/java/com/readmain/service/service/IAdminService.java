package com.readmain.service.service;


import com.readmain.common.entity.*;

import java.util.List;

public interface IAdminService {

    // ====================User Start====================
    List<SysUserEntity> queryAllUsers() throws Exception;

    SysUserEntity getUserByUsername(String username) throws Exception;

    SysUserEntity getUserById(Long userId) throws Exception;

    SysUserEntity doLogin(String loginName, String password, String googleKey) throws Exception;

    void doSaveUser(SysUserEntity userEntity) throws Exception;

    void doUpdateUser(SysUserEntity userEntity) throws Exception;

    void doUpdateUserStatus(Long userId, Integer status) throws Exception;

    void doChangeUserPassword(Long userId, String oldPassword, String newPassword, String newPasswordConfirm) throws Exception;
    // ====================User End====================

    // ====================Role Start====================
    List<SysRoleEntity> queryAllRoles() throws Exception;

    SysRoleEntity getRoleById(Long roleId) throws Exception;

    void doSaveRole(SysRoleEntity roleEntity) throws Exception;

    void doUpdateRole(SysRoleEntity roleEntity) throws Exception;

    void doDeleteRole(Long roleId) throws Exception;
    // ====================Role End====================

    void doSaveUserRole(SysUserRoleEntity userRole) throws Exception;

    // ====================Resource Start====================
    List<SysResourceTreeEntity> queryAllResources() throws Exception;

    SysResourceEntity getResourceById(Long resourceId) throws Exception;

    void doSaveResource(SysResourceEntity resourceEntity) throws Exception;

    void doUpdateResource(SysResourceEntity resourceEntity) throws Exception;

    void doDeleteResource(Long... resourceIds) throws Exception;
    // ====================Resource End====================

    void doSaveRoleResource(SysRoleResourceEntity roleResource) throws Exception;

    /**
     * 查询用户的角色列表
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    List<SysRoleEntity> queryUserRoles(Long userId) throws Exception;

    /**
     * 查询角色的资源列表
     *
     * @param roleId 用户ID
     * @return
     * @throws Exception
     */
    List<SysResourceEntity> queryRoleResources(Long roleId) throws Exception;

    /**
     * 查询用户的资源列表
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    List<SysResourceEntity> queryUserResources(Long userId) throws Exception;

    /**
     * 获取顶级父菜单
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    List<SysResourceEntity> queryRootMenus(Long userId) throws Exception;

    /**
     * 查询用户的左侧菜单列表
     *
     * @param userId  用户ID
     * @param rootPid 顶级父菜单ID
     * @return
     * @throws Exception
     */
    List<SysUserMenuEntity> queryLeftMenus(Long userId, Long rootPid, Boolean isParent) throws Exception;

    void clearUserMenuCache(Long userId);

    /**
     * 授权时查询标示已授权的资源Tree
     *
     * @param userId
     * @return
     */
    List<SysResourceTreeEntity> queryAllResourceWithGranted(Long userId);

    List<SysResourceTreeEntity> queryRoleResourceTree(Long roleId);

    void updateGoogleKey(Long id, String secretKey) throws Exception;
}
