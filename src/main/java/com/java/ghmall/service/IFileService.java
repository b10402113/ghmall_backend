package com.java.ghmall.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface IFileService {
    public String upload(MultipartFile file, String path);
}
