package com.java.ghmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.java.ghmall.common.Const;
import com.java.ghmall.common.ResponseCode;
import com.java.ghmall.common.ServerResponse;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.form.ProductForm;
import com.java.ghmall.pojo.Product;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IFileService;
import com.java.ghmall.service.IProductService;
import com.java.ghmall.service.IUserService;
import com.java.ghmall.service.impl.manage.IProductManageService;
import com.java.ghmall.utils.PropertiesUtil;
import com.java.ghmall.vo.ResponseVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/manage/product")
public class ProductManageController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductManageService productManageService;

    @Autowired
    private IUserService userService;

    //    新增或更改產品
    @PostMapping("/")
    @ResponseBody
    public ResponseVo productSave(HttpSession session, @Valid @RequestBody ProductForm productForm){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return productService.saveOrUpdateProduct(productForm);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }

    // 修改產品狀態
    @PutMapping("/status")
    @ResponseBody
    public ResponseVo setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            return productService.setSaleStatus(productId,status);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseVo getDetail(HttpSession session, Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(userService.checkAdminRole(user).isSuccess()){
            //填充业务
            return productService.manageProductDetail(productId);

        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseVo deleteProduct(HttpSession session,@RequestParam Integer productId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(userService.checkAdminRole(user).isSuccess()){
            //填充业务
            return productService.deleteProduct(productId);

        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }


    @GetMapping("/list")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(required = false, defaultValue = "") String query,
                                     HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            return productManageService.list(categoryId, pageNum, pageSize,query);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }








    @Autowired
    private IFileService fileService;
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVo upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(file,path);
            //Todo url改成ftp服務器的
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri",targetFileName);
//            fileMap.put("url",url);
            return ResponseVo.success(fileMap);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }


}
