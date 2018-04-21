package com.yang.project.controller;

import com.yang.project.base.BaseModel;
import com.yang.project.model.CollectionZan;
import com.yang.project.model.Comment;
import com.yang.project.service.InteractionService;
import com.yang.project.utils.JpushUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @Autowired
    InteractionService service;

    private BaseModel baseModel = new BaseModel();

    //评论
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public BaseModel comment(HttpServletRequest request,
                                 HttpSession session){
        String userId = (String) session.getAttribute(session.getId());
        String type = request.getParameter("type");//0相册  1视频
        String id = request.getParameter("id");
        String info = request.getParameter("info");
        System.out.println(session.getAttribute(session.getId()));

        try {
            if (userId != null) {
                Comment comment = new Comment();
                if("0".equals(type))
                    comment.setAlbumId(id);
                else
                    comment.setVideoId(id);
                comment.setUserId(userId);
                comment.setCreateTime(System.currentTimeMillis() + "");
                comment.setInfo(info);
                Long number = service.comment(comment);
                if (number != null && number > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("评论成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("评论失败");
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
            baseModel.setMsg("评论失败");
        }
        return baseModel;
    }

    //删除评论
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public BaseModel deleteComment(HttpServletRequest request,
                             HttpSession session){
        String userId = (String) session.getAttribute(session.getId());
        String id = request.getParameter("id");
        System.out.println(session.getAttribute(session.getId()));
        try {
            if (userId != null) {
                Long number = service.deleteComment(id);
                if (number != null && number > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("删除成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("删除失败");
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
            baseModel.setMsg("删除失败");
        }
        return baseModel;
    }


    //点赞收藏
    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public BaseModel collection(HttpServletRequest request,
                             HttpSession session){
        String userId = (String) session.getAttribute(session.getId());
        String type = request.getParameter("type");//0图片 2视频
        String aType = request.getParameter("aType");//0点赞  1收藏
        String id = request.getParameter("id");
        System.out.println(session.getAttribute(session.getId()));
        try {
            if (userId != null) {
                CollectionZan collection = new CollectionZan();
                if("0".equals(type))
                    collection.setAlbumId(id);
                else
                    collection.setVideoId(id);
                collection.setUserId(userId);
                collection.setCreateTime(System.currentTimeMillis() + "");
                collection.setType(aType);
                Long number = service.collection(collection);
                if (number != null && number > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("请求成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("请求失败");
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
            baseModel.setMsg("请求失败");
        }
        return baseModel;
    }

}
