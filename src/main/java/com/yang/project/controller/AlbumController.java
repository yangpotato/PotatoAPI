package com.yang.project.controller;

import com.yang.project.base.BaseModel;
import com.yang.project.model.Album;
import com.yang.project.model.User;
import com.yang.project.model.Video;
import com.yang.project.service.AlbumService;
import com.yang.project.utils.JpushUtils;
import com.yang.project.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    private BaseModel baseModel = new BaseModel();

    @RequestMapping(value = "/uploadAlbum", method = RequestMethod.POST)
    public BaseModel uploadAlbum(HttpServletRequest request,
                            HttpSession session){
        String title = request.getParameter("title");
        String img = request.getParameter("img");
        String info = request.getParameter("info");
        System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                Album album = new Album();
                album.setUserId(id);
                album.setImg(img);
                album.setInfo(info);
                album.setTitle(title);
                album.setCreateTime(System.currentTimeMillis() + "");
                album.setZan(0);
                album.setCollecti(0);
                album.setComment(0);
                Long update = albumService.uploadAlbum(album);
                if (update != null && update > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("上传成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("上传失败");
                }

            } else {
                baseModel.setStatus(10);
                baseModel.setData(null);
                baseModel.setMsg("登录失效");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(2);
            baseModel.setData(e.toString());
            baseModel.setMsg("上传失败");
        }
        return baseModel;
    }

    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
    public BaseModel uploadVideo(HttpServletRequest request,
                                 HttpSession session){
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        String info = request.getParameter("info");
        System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                Video album = new Video();
                album.setUserId(id);
                album.setVideoUrl(url);
                album.setInfo(info);
                album.setTitle(title);
                album.setCreateTime(System.currentTimeMillis() + "");
                album.setZan(0);
                album.setCollecti(0);
                album.setComment(0);
                Long update = albumService.uploadVideo(album);
                if (update != null && update > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("上传成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("上传失败");
                }

            } else {
                baseModel.setStatus(10);
                baseModel.setData(null);
                baseModel.setMsg("登录失效");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(2);
            baseModel.setData(e.toString());
            baseModel.setMsg("上传失败");
        }
        return baseModel;
    }

    @RequestMapping(value = "/selectAlbum", method = RequestMethod.POST)
    public BaseModel selectAlbum(HttpServletRequest request,
                                 HttpSession session){
        System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                Album album = albumService.selectAlbum(Integer.parseInt(id));
                if (album != null ) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(album);
                    baseModel.setMsg("成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("失败");
                }

            } else {
                baseModel.setStatus(10);
                baseModel.setData(null);
                baseModel.setMsg("登录失效");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(2);
            baseModel.setData(e.toString());
            baseModel.setMsg("失败");
        }
        return baseModel;
    }
}
