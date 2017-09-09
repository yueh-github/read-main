package com.readmain.service.service.impl;

import com.readmain.common.constants.Constants;
import com.readmain.common.entity.*;
import com.readmain.common.enums.EExceptionCode;
import com.readmain.common.enums.ESysResourceType;
import com.readmain.common.utils.PasswordUtils;
import com.readmain.service.dao.IAdminDao;
import com.readmain.service.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private IAdminDao adminDao;

//    @Resource
//    private EmailUtils emailUtils;

    @Override
    public List<SysUserEntity> queryAllUsers() throws Exception {
        return adminDao.queryAllUsers();
    }

    @Override
    public SysUserEntity getUserByUsername(String username) throws Exception {
        return null;
    }

    @Override
    public SysUserEntity getUserById(Long userId) throws Exception {
        SysUserEntity userEntity = adminDao.getUserById(userId);
        if (userEntity != null) {
            userEntity.setPassword(null);
        }
        return userEntity;
    }

    @Override
    public SysUserEntity doLogin(String loginName, String password, String googleKey) throws Exception {
        SysUserEntity user = adminDao.getUserByLoginName(loginName);
        if (user == null) {
            throw EExceptionCode.SYS_LOGIN_INPUT_ERROR.buildCustomerException();
        }


//        log.info("登录密码串：" + PasswordUtils.encodePassword(password));
        if (StringUtils.equals(user.getPassword(), PasswordUtils.encodePassword(password))) {
            if (user.getStatus() == 0) {
                throw EExceptionCode.SYS_ACCOUNT_UNAVAILABLE.buildCustomerException();
            }
            user.setPassword(Constants.COMMON_PWD);
            SysUserEntity updateInfo = new SysUserEntity();
            updateInfo.setId(user.getId());
            updateInfo.setLastLoginTime(new Date().getTime());
            adminDao.updateUser(updateInfo);
            return user;
        } else {
            throw EExceptionCode.SYS_LOGIN_INPUT_ERROR.buildCustomerException();
        }
    }

    @Override
    public void doSaveUser(SysUserEntity userEntity) throws Exception {
        String password = PasswordUtils.generateRandomPassWord();
        userEntity.setPassword(PasswordUtils.encodePassword(DigestUtils.md5Hex(password)));
        userEntity.setCreated(new Date().getTime());
        adminDao.saveUser(userEntity);

        try {
            String content = "\t您的系统密码是\n\t\t\t" + password + ", \n\t请用邮箱登录, 登陆后请修改修改密码!";
//            emailUtils.sendMail("管理系统账号", content, userEntity.getEmail());
        } catch (Exception e) {
//            log.error("发送邮件失败:{}", e);
        }
    }

    @Override
    public void doUpdateUser(SysUserEntity userEntity) throws Exception {
        userEntity.setUpdated(new Date().getTime());
        adminDao.updateUser(userEntity);
    }

    @Override
    public void doUpdateUserStatus(Long userId, Integer status) throws Exception {

    }

    @Override
    public void doChangeUserPassword(Long userId, String oldPassword, String newPassword, String newPasswordConfirm) throws Exception {

    }

    @Override
    public List<SysRoleEntity> queryAllRoles() throws Exception {
        return adminDao.queryAllRoles();
    }

    @Override
    public SysRoleEntity getRoleById(Long roleId) throws Exception {
        return adminDao.getRoleById(roleId);
    }

    @Override
    public void doSaveRole(SysRoleEntity roleEntity) throws Exception {
        adminDao.saveRole(roleEntity);
    }

    @Override
    public void doUpdateRole(SysRoleEntity roleEntity) throws Exception {
        adminDao.updateRole(roleEntity);
    }

    @Override
    public void doDeleteRole(Long roleId) throws Exception {
        adminDao.deleteRole(roleId);
        adminDao.deleteRoleResource(roleId);
    }

    @Override
    public void doSaveUserRole(SysUserRoleEntity userRole) throws Exception {
        adminDao.saveUserRole(userRole);
    }

    @Override
    public List<SysResourceTreeEntity> queryAllResources() throws Exception {
        List<SysResourceEntity> resourceList = adminDao.queryAllResources();
        return generateResourceTree(resourceList, null, 0L);
    }

    private List<SysResourceTreeEntity> generateResourceTree(List<SysResourceEntity> resourceList, String[] resourceIds, Long rootPid) {
        List<SysResourceTreeEntity> resourceTreeList = new ArrayList<>();
        List<SysResourceEntity> sonResourceList = querySonResourceList(resourceList, rootPid);
        sonResourceList.sort(Comparator.comparing(SysResourceEntity::getSort));
        if (CollectionUtils.isNotEmpty(sonResourceList)) {
            for (SysResourceEntity resource : sonResourceList) {
                SysResourceTreeEntity resourceTree = new SysResourceTreeEntity(resource);
                if (ArrayUtils.isNotEmpty(resourceIds) && ArrayUtils.contains(resourceIds, resource.getId().toString())) {
                    resourceTree.setChecked(true);
                }
                if (CollectionUtils.isNotEmpty(querySonResourceList(resourceList, resource.getId()))) {
                    resourceTree.getSonResourceTree().addAll(generateResourceTree(resourceList, resourceIds, resource.getId()));
                }
                resourceTreeList.add(resourceTree);
            }
        }
        return resourceTreeList;
    }

    @Override
    public SysResourceEntity getResourceById(Long resourceId) throws Exception {
        return adminDao.getResourceById(resourceId);
    }

    @Override
    public void doSaveResource(SysResourceEntity resourceEntity) throws Exception {
        adminDao.saveResource(resourceEntity);
    }

    @Override
    public void doUpdateResource(SysResourceEntity resourceEntity) throws Exception {
        adminDao.updateResource(resourceEntity);
    }

    @Override
    public void doDeleteResource(Long... resourceIds) throws Exception {
        List<Long> resourceIdList = new ArrayList<>();
        List<SysResourceEntity> resourceList = adminDao.queryAllResources();
        List<SysResourceEntity> sonResourceList = null;
        for (Long resourceId : resourceIds) {
            sonResourceList = querySonResourceList(resourceList, resourceId);
            for (SysResourceEntity resourceEntity : sonResourceList) {
                resourceIdList.add(resourceEntity.getId());
            }
            resourceIdList.add(resourceId);
        }
        adminDao.deleteResource(resourceIdList);
    }

    @Override
    public void doSaveRoleResource(SysRoleResourceEntity roleResource) throws Exception {
        adminDao.saveRoleResource(roleResource);
    }

    /**
     * 这个查询的其实是全部的角色, 只不过如果用户有改角色, 设置checked=true
     *
     * @param userId 用户ID
     * @return
     * @throws Exception
     */
    @Override
    public List<SysRoleEntity> queryUserRoles(Long userId) throws Exception {
        List<SysRoleEntity> roleList = adminDao.queryAllRoles();
        SysUserRoleEntity userRole = adminDao.getUserRolesByUserId(userId);
        if (userRole != null && StringUtils.isNotEmpty(userRole.getRoleIds())) {
            String[] roleIds = userRole.getRoleIds().split(",");
            for (SysRoleEntity role : roleList) {
                if (ArrayUtils.contains(roleIds, role.getId().toString())) {
                    role.setChecked(true);
                }
            }
        }
        return roleList;
    }

    @Override
    public List<SysResourceEntity> queryRoleResources(Long roleId) throws Exception {
        List<SysResourceEntity> resourceList = new ArrayList<>();
        SysRoleResourceEntity roleResource = adminDao.getRoleResourcesByRoleId(roleId);
        if (roleResource != null) {
            List<String> resourceIds = new ArrayList<>();
            resourceIds.add(roleResource.getResourceIds());
            resourceList = adminDao.queryResourcesByResourceIDSs(resourceIds);
        }
        return resourceList;
    }

    @Override
    public List<SysResourceEntity> queryUserResources(Long userId) throws Exception {
        List<String> resourceIDSs = new ArrayList<>();
        SysRoleResourceEntity roleResource = null;
        for (SysRoleEntity sysRoleEntity : queryUserRoles(userId)) {
            if (sysRoleEntity.getChecked()) {
                roleResource = adminDao.getRoleResourcesByRoleId(sysRoleEntity.getId());
                if (roleResource != null) {
                    resourceIDSs.add(roleResource.getResourceIds());
                }
            }
        }
        return adminDao.queryResourcesByResourceIDSs(resourceIDSs);
    }

    @Override
    public List<SysResourceEntity> queryRootMenus(Long userId) throws Exception {
        List<SysResourceEntity> resourceList = queryUserResourceList(userId, ESysResourceType.MENU.getCode());
        return querySonResourceList(resourceList, 0L);
    }

    @Override
//    @Cacheable(value = "springCache", condition = "#isParent", key = "'rootMenuList_' + #userId + '_' + #rootPid")
    public List<SysUserMenuEntity> queryLeftMenus(Long userId, Long rootPid, Boolean isParent) throws Exception {
        List<SysResourceEntity> resourceList = queryUserResourceList(userId, ESysResourceType.MENU.getCode());
        return generateUserMenu(resourceList, rootPid);
    }

    @Override
//    @CacheEvict(value = "springCache", key = "'rootMenuList_' + #userId + '_*'")
    public void clearUserMenuCache(Long userId) {
    }

    @Override
    public List<SysResourceTreeEntity> queryAllResourceWithGranted(Long userId) {
        SysUserRoleEntity userRole = adminDao.getUserRolesByUserId(userId);
        String[] resourceIdsGranted = new String[0];
        if (userRole != null) {
            SysRoleResourceEntity roleResource = null;
            for (String roleId : userRole.getRoleIds().split(",")) {
                roleResource = adminDao.getRoleResourcesByRoleId(Long.valueOf(roleId));
                if (roleResource != null) {
                    resourceIdsGranted = ArrayUtils.addAll(resourceIdsGranted, roleResource.getResourceIds().split(","));
                }
            }
        }
        List<SysResourceEntity> resourceList = adminDao.queryAllResources();
        return generateResourceTree(resourceList, resourceIdsGranted, 0L);
    }

    @Override
    public List<SysResourceTreeEntity> queryRoleResourceTree(Long roleId) {
        String[] resourceIdsGranted = new String[0];
        SysRoleResourceEntity roleResource = adminDao.getRoleResourcesByRoleId(roleId);
        if (roleResource != null) {
            resourceIdsGranted = ArrayUtils.addAll(resourceIdsGranted, roleResource.getResourceIds().split(","));
        }

        List<SysResourceEntity> resourceList = adminDao.queryAllResources();
        return generateResourceTree(resourceList, resourceIdsGranted, 0L);
    }

    private List<SysUserMenuEntity> generateUserMenu(List<SysResourceEntity> resourceList, Long rootPid) {
        List<SysUserMenuEntity> menuList = new ArrayList<>();
        // 获取左侧的父菜单
        List<SysResourceEntity> leftRootResourceList = querySonResourceList(resourceList, rootPid);
        leftRootResourceList.sort(Comparator.comparing(SysResourceEntity::getSort));
        if (CollectionUtils.isNotEmpty(leftRootResourceList)) {
            for (SysResourceEntity leftRootResource : leftRootResourceList) {
                /* 生成每个父菜单的子菜单信息 */
                SysUserMenuEntity userMenuEntity = new SysUserMenuEntity(leftRootResource);
                /* 查询子菜单是否还有子菜单, 如果存在，继续逐级生成该子菜单的所有子孙菜单, 如果不存在, 继续 */
                List<SysResourceEntity> sonResourceList = querySonResourceList(resourceList, leftRootResource.getId());
                if (CollectionUtils.isNotEmpty(sonResourceList)) {
                    userMenuEntity.getSonMenuList().addAll(generateUserMenu(resourceList, leftRootResource.getId()));
                }
                // 添加子菜单信息
                menuList.add(userMenuEntity);
            }
        }
        return menuList;
    }

    @SuppressWarnings(value = {"unchecked"})
    private List<SysResourceEntity> querySonResourceList(List<SysResourceEntity> allResources, Long pid) {
        return (List<SysResourceEntity>) CollectionUtils.select(allResources, object -> {
            SysResourceEntity resourceEntity = (SysResourceEntity) object;
            if (resourceEntity.getPid().longValue() == pid) {
                return true;
            }
            return false;
        });
    }

    private List<SysResourceEntity> queryUserResourceList(Long userId, Integer resourceType) throws Exception {
        List<SysResourceEntity> requiredList = new ArrayList<>();
        for (SysResourceEntity resourceEntity : queryUserResources(userId)) {
            if (resourceEntity.getResourceType().intValue() == resourceType) {
                requiredList.add(resourceEntity);
            }
        }
        return requiredList;
    }

    @Override
    public void updateGoogleKey(Long id, String secretKey) throws Exception {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setId(id);
        userEntity.setGoogleKey(secretKey);
        doUpdateUser(userEntity);
    }
}
