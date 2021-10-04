package com.java.ghmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.java.ghmall.common.Const;
import com.java.ghmall.common.ResponseCode;
import com.java.ghmall.common.ServerResponse;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.CateAddForm;
import com.java.ghmall.form.CateUpdateForm;
import com.java.ghmall.pojo.Category;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.ICategoryService;
import com.java.ghmall.service.IUserService;
import com.java.ghmall.vo.CategoryVo;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("")
    public ResponseVo addCategory(HttpSession session,@RequestBody CateAddForm cateAddForm) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);

        //校验一下是否是管理员
        if (userService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //增加我们处理分类的逻辑
            return categoryService.addCategory(cateAddForm);

        } else {
            return ResponseVo.error(ResponseEnum.NO_RIGHT, "權限不足");
        }
    }

    @PutMapping("")
    public ResponseVo updateCategory(HttpSession session,@RequestBody CateUpdateForm categoryForm) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (userService.checkAdminRole(user).isSuccess()) {
            //更新categoryName

//            return ResponseVo.successByMsg("成功");
            return categoryService.updateCategory(categoryForm);
        } else {
            return ResponseVo.error(ResponseEnum.ERROR, "无权限操作,需要管理员权限");
        }
    }
    @DeleteMapping("")
    public ResponseVo deleteCategory(HttpSession session, @RequestParam Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (userService.checkAdminRole(user).isSuccess()) {
            //更新categoryName

//            return ResponseVo.successByMsg("成功");
            return categoryService.deleteCategoryById(categoryId);
        } else {
            return ResponseVo.error(ResponseEnum.ERROR, "无权限操作,需要管理员权限");
        }
    }

    @GetMapping("")
    public ResponseVo<List<CategoryVo>>  selectAll(HttpSession session) {

        User user = (User) session.getAttribute(Const.CURRENT_USER);

        //校验一下是否是管理员
        if (userService.checkAdminRole(user).isSuccess()) {
            //是管理员
            //增加我们处理分类的逻辑
            return categoryService.selectAllByManage();

        } else {
            return ResponseVo.error(ResponseEnum.NO_RIGHT, "權限不足");
        }
    }

//    @GetMapping("/list")
//    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
//                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
//                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
//                                     HttpSession session) {
//        User user = (User) session.getAttribute(Const.CURRENT_USER);
//
//        if (userService.checkAdminRole(user).isSuccess()) {
//            return productManageService.list(categoryId, pageNum, pageSize);
//        } else {
//            return ResponseVo.error(ResponseEnum.ERROR, "无权限操作");
//        }
//    }
}
