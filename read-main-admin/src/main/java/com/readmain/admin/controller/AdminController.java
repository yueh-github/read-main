package com.readmain.admin.controller;

import com.readmain.admin.utils.CommonUtils;
import com.readmain.common.entity.*;
import com.readmain.common.exception.ApiException;
import com.readmain.common.exception.CustomerException;
import com.readmain.common.utils.SessionAware;
import com.readmain.service.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @RequestMapping(value = "/user")
    public String userList(Model model) throws Exception {
        model.addAttribute("users", adminService.queryAllUsers());
        return "admin/user";
    }

    @RequestMapping(value = "/user/info")
    @ResponseBody
    public SysUserEntity userInfo(Model model, @RequestParam Long userId) throws Exception {
        return adminService.getUserById(userId);
    }

    @RequestMapping(value = "/user/role")
    @ResponseBody
    public List<SysRoleEntity> userRole(@RequestParam Long userId) throws Exception {
        return adminService.queryUserRoles(userId);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody SysUserEntity user) throws Exception {
        try {
            adminService.doSaveUser(user);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyUser(@RequestBody SysUserEntity user) throws Exception {
        try {
            user.setPassword(null);
            user.setUpdated(new Date().getTime());
            adminService.doUpdateUser(user);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/user/modify_pwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyUserPwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String newConfirmPwd) throws Exception {
        try {
            Long userId = (Long) SessionAware.getSession().getAttribute("userId");
            adminService.doChangeUserPassword(userId, oldPwd, newPwd, newConfirmPwd);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/user/role/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUserRole(@RequestParam Long userId, @RequestParam String roleIds) throws Exception {
        try {
            SysUserRoleEntity userRole = new SysUserRoleEntity();
            userRole.setUserId(userId);
            userRole.setRoleIds(roleIds);
            adminService.doSaveUserRole(userRole);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delUser(@RequestParam Long id) throws Exception {
        try {
            adminService.doUpdateUserStatus(id, 0);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/role")
    public String roleList(Model model) throws Exception {
        List<SysRoleEntity> roleList = adminService.queryAllRoles();
        model.addAttribute("roles", roleList);
        return "admin/role";
    }

    @RequestMapping(value = "/role/resource")
    @ResponseBody
    public List<SysResourceTreeEntity> roleResource(@RequestParam Long roleId) throws Exception {
        return adminService.queryRoleResourceTree(roleId);
    }

    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRole(Model model, @RequestBody SysRoleEntity role) throws Exception {
        try {
            adminService.doSaveRole(role);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/role/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyRole(@RequestBody SysRoleEntity role) throws Exception {
        try {
            adminService.doUpdateRole(role);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/role/resource/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveRoleResource(@RequestParam Long roleId, @RequestParam String resourceIds) throws Exception {
        try {
            SysRoleResourceEntity roleResource = new SysRoleResourceEntity();
            roleResource.setRoleId(roleId);
            roleResource.setResourceIds(resourceIds);
            adminService.doSaveRoleResource(roleResource);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/role/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delRole(@RequestParam Long id) throws Exception {
        try {
            adminService.doDeleteRole(id);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存role异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/resource")
    public String resourceList(Model model,
                               @RequestParam(required = false, defaultValue = "0") Long pid) throws Exception {
        List<SysResourceTreeEntity> resourceTreeList = adminService.queryAllResources();
        model.addAttribute("resources", resourceTreeList);
        return "admin/resource";
    }

    @RequestMapping(value = "/resource/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addResource(Model model, @RequestBody SysResourceEntity resource) throws Exception {
        try {
            adminService.doSaveResource(resource);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存resource异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/resource/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyResource(@RequestBody SysResourceEntity resource) throws Exception {
        try {
            adminService.doUpdateResource(resource);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存resource异常:{}", e);
            throw e;
        }
    }

    @RequestMapping(value = "/resource/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delResource(@RequestParam Long id) throws Exception {
        try {
            adminService.doDeleteResource(id);
            return CommonUtils.mapReturn(true);
        } catch (CustomerException e) {
            throw ApiException.build(e);
        } catch (Exception e) {
            log.error("保存resource异常:{}", e);
            throw e;
        }
    }

}
