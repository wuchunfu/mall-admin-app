package com.mall.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.admin.common.CommonPage;
import com.mall.admin.common.CommonResult;
import com.mall.admin.model.param.PasswordParam;
import com.mall.admin.model.param.UserInfoParam;
import com.mall.admin.model.vo.UserInfoVo;
import com.mall.admin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/update")
    public CommonResult<Integer> updateUser(UserInfoParam userInfoParam){
        int count = userService.update(userInfoParam);
        if (count > 0){
            return CommonResult.success(count, "更新成功！");
        }
        return CommonResult.failed();
    }

    @PostMapping("/pass")
    public CommonResult<Integer> updatePassword(PasswordParam passwordParam, Principal principal){
        int count = userService.updatePwd(passwordParam, principal);
        if (count > 0){
            return CommonResult.success(count, "更新成功！");
        }
        return CommonResult.failed("旧密码错误，更新失败！");
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/delete")
    public CommonResult<Integer> deleteUser(@RequestParam(value = "id") Long id){
        int count = userService.delete(id);
        if (count > 0){
            return CommonResult.success(count, "删除成功！");
        }
        return CommonResult.failed();
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public CommonResult<CommonPage<UserInfoVo>> selectUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        IPage<UserInfoVo> infoListPage = userService.getList(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(infoListPage));
    }
}
