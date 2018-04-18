package com.yang.project.controller;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.yang.project.base.BaseModel;
import com.yang.project.model.Follow;
import com.yang.project.model.Letter;
import com.yang.project.model.User;
import com.yang.project.service.UserService;
import com.yang.project.utils.JpushUtils;
import com.yang.project.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.yang.project.utils.JpushUtils.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    private BaseModel baseModel = new BaseModel();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseModel login(@RequestParam("code") String code,
                      @RequestParam("pwd") String pwd,
                           HttpSession session){
        User user = null;
        try {
            user = service.Login(code, Util.md5(pwd));
            if(user != null){
                session.setAttribute(session.getId(), user.getUserId());
                System.out.println(session.getAttribute(session.getId()));
                user.setLoginTime(System.currentTimeMillis() + "");
                Long number = service.update(user);
                baseModel.setStatus(0);
                baseModel.setData(user);
                baseModel.setMsg("登录成功");
            }else{
                baseModel.setStatus(2);
                baseModel.setData(user);
                baseModel.setMsg("账号或密码错误");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(1);
            baseModel.setData(user);
            baseModel.setMsg("登录失败");
        }
        return baseModel;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseModel register(@RequestParam("code") String code,
                           @RequestParam("pwd") String pwd){
        try {
            Integer nextId = service.findMaxId();
            String pwdMd = Util.md5(pwd);
            Long register = service.register(code, pwdMd, System.currentTimeMillis()+"");
            if(register != null && register > 0){
                baseModel.setStatus(0);
                baseModel.setData(nextId+1);
                baseModel.setMsg("注册成功");
            }else{
                baseModel.setStatus(1);
                baseModel.setData(null);
                baseModel.setMsg("注册失败");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(2);
            baseModel.setData(null);
            baseModel.setMsg("用户已存在");
        }
        return baseModel;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseModel modify(HttpServletRequest request,
                               HttpSession session){
        String pwd = request.getParameter("pwd");
        String nickName = request.getParameter("nickName");
        String headImg = request.getParameter("headImg");
        String se = request.getParameter("sex");
        Integer sex = null;
        if(se != null && se.length() > 0) {
           sex = Integer.parseInt(request.getParameter("sex"));
        }
        String background = request.getParameter("background");
        System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                User user = new User();
                user.setUserId(id);
                if(!Util.isEmpty(pwd)){
                    user.setPwd(Util.md5(pwd));
                }
                if(!Util.isEmpty(background)) {
                    user.setBackground(background);
                }
                if(!Util.isEmpty(nickName)) {
                    user.setNickName(nickName);
                }
                if(!Util.isEmpty(headImg)) {
                    user.setHeadImg(headImg);
                }
                if(sex != null) {
                    user.setSex(sex);
                }
                Long update = service.update(user);
                if (update != null && update > 0) {
                    System.out.println("345");
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("修改成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("修改失败");
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
            baseModel.setMsg("修改失败");
        }
        return baseModel;
    }

    @RequestMapping(value = "/attention", method = RequestMethod.POST)
    public BaseModel attention(HttpServletRequest request,
                            HttpSession session){
        String followId = request.getParameter("userId");
        //System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                Follow follow = new Follow();
                follow.setCreateTime(System.currentTimeMillis() + "");
                follow.setUserId(id);
                follow.setFollowId(followId);
                Long lo = service.attention(follow);
                if (lo != null && lo > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("关注成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("关注失败");
                }

            } else {
                baseModel.setStatus(10);
                baseModel.setData(null);
                baseModel.setMsg("登录失效");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(1);
            baseModel.setData(e.toString());
            baseModel.setMsg("关注失败");
        }
        return baseModel;
    }

    @RequestMapping(value = "/cancelAttention", method = RequestMethod.POST)
    public BaseModel cancelAttention(HttpServletRequest request,
                               HttpSession session){
        String followId = request.getParameter("userId");
        //System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                Follow follow = new Follow();
                follow.setCreateTime(System.currentTimeMillis() + "");
                follow.setUserId(id);
                follow.setFollowId(followId);
                follow.setStatus(1);
                Long lo = service.cancelAttention(follow);
                if (lo != null && lo > 0) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("取消成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("取消失败");
                }

            } else {
                baseModel.setStatus(10);
                baseModel.setData(null);
                baseModel.setMsg("登录失效");
            }
        }catch (Exception e){
            System.out.println(e.toString());
            baseModel.setStatus(1);
            baseModel.setData(e.toString());
            baseModel.setMsg("取消失败");
        }
        return baseModel;
    }

    @RequestMapping(value = "/getAttention", method = RequestMethod.GET)
    public BaseModel getAttention(HttpServletRequest request,
                                     HttpSession session){

        System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        List<User> attentionList = null;
        try {
            if (id != null) {
                attentionList = service.getAttention(id);
                if (attentionList != null) {
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(attentionList);
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
            baseModel.setStatus(1);
            baseModel.setData(e.toString());
            baseModel.setMsg("请求失败");
        }
        return baseModel;
    }


    @RequestMapping(value = "/sendLetter", method = RequestMethod.POST)
    public BaseModel sendLetter(HttpServletRequest request,
                            HttpSession session){
        String info = request.getParameter("info");
        String otherId = request.getParameter("otherId");
        System.out.println(session.getAttribute(session.getId()));
        String id = (String) session.getAttribute(session.getId());
        try {
            if (id != null) {
                Letter letter = new Letter();
                letter.setUserId(id);
                letter.setCreateTime(System.currentTimeMillis() + "");
                letter.setOtherId(otherId);
                letter.setInfo(info);

                Long send = service.sendLetter(letter);
                if (send != null && send > 0) {
                    System.out.println("345");
                    JpushUtils.sendAll("ppppp");
                    baseModel.setStatus(0);
                    baseModel.setData(null);
                    baseModel.setMsg("发送成功");
                } else {
                    baseModel.setStatus(1);
                    baseModel.setData(null);
                    baseModel.setMsg("发送失败");
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
            baseModel.setMsg("发送失败");
        }
        return baseModel;
    }


}
