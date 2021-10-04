package com.java.ghmall.controller.backend;

import com.google.common.collect.Maps;
import com.java.ghmall.consts.MallConst;
import com.java.ghmall.enums.ResponseEnum;
import com.java.ghmall.pojo.User;
import com.java.ghmall.service.IFileService;
import com.java.ghmall.service.IUserService;
import com.java.ghmall.utils.PropertiesUtil;
import com.java.ghmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/manage/file")
public class FileManageController {
    @Autowired
    private IFileService fileService;

    @Autowired
    private IUserService userService;
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseVo upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = fileService.up load(file,path);
            String targetFileName = fileService.upload(file,MallConst.FILE_UPLOAD_DIR);
            //Todo url改成ftp服務器的
//            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
//
//            Map fileMap = Maps.newHashMap();
//            fileMap.put("uri",targetFileName);
//            fileMap.put("url",url);
            return ResponseVo.success(targetFileName);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }

    @RequestMapping("/upload_img")
    @ResponseBody
    public ResponseVo uploadWangEditor(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        if(userService.checkAdminRole(user).isSuccess()){
            String path = request.getSession().getServletContext().getRealPath("upload");
//            String targetFileName = fileService.up load(file,path);
            String targetFileName = fileService.upload(file,MallConst.FILE_UPLOAD_DIR);
            //Todo url改成ftp服務器的
//            String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
//
//            Map fileMap = Maps.newHashMap();
//            fileMap.put("uri",targetFileName);
//            fileMap.put("url",url);
            return ResponseVo.success(targetFileName);
        }else{
            return ResponseVo.error(ResponseEnum.ERROR,"无权限操作");
        }
    }



}
