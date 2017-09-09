package com.readmain.service.dao;

import com.readmain.common.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IAdminDao {

    List<SysUserEntity> queryAllUsers();

    SysUserEntity getUserByLoginName(String loginName);

    SysUserEntity getUserById(Long userId);

    int saveUser(SysUserEntity userEntity);

    int updateUser(SysUserEntity userEntity);

    List<SysRoleEntity> queryAllRoles();

    SysRoleEntity getRoleById(Long id);

    int saveRole(SysRoleEntity roleEntity);

    int updateRole(SysRoleEntity roleEntity);

    int deleteRole(Long id);

    List<SysResourceEntity> queryAllResources();

    SysResourceEntity getResourceById(Long id);

    int saveResource(SysResourceEntity resourceEntity);

    int updateResource(SysResourceEntity resourceEntity);

    int deleteResource(@Param("resourceIds") List<Long> ids);

    SysUserRoleEntity getUserRolesByUserId(Long userId);

    int saveUserRole(SysUserRoleEntity userRoleEntity);

    int deleteUserRole(Long userId);

    SysRoleResourceEntity getRoleResourcesByRoleId(Long roleId);

    int saveRoleResource(SysRoleResourceEntity roleResourceEntity);

    int deleteRoleResource(Long roleId);

    List<SysResourceEntity> queryResourcesByResourceIDSs(@Param("resourcesIDSs") List<String> resourceIDSs);

}
