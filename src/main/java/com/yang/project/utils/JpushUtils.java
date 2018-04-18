package com.yang.project.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.GroupPushClient;
import cn.jpush.api.push.model.notification.*;
/*//import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;*/
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

public class JpushUtils {

    public static String MASTER_SECRET = "d5d254167ebd0bb380ee9285";
    public static String APP_KEY = "5952952f6ec53373d16246d9";
    private static JPushClient jpushClient = null;

    /**
     * 得到JPushClient实例
     */
    public static JPushClient getJPushClientInstance() {
        if (jpushClient == null) {
//            jpushClient = new JPushClient("d5d254167ebd0bb380ee9285",
//                    "5952952f6ec53373d16246d9", 5);
            jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        }
        return jpushClient;
    }

    public static void pushAndIos2(String alias, String alert, String content) {

        JPushClient jPushClient = JpushUtils.getJPushClientInstance();
        PushPayload ipayload = JpushUtils.pushAndroidDeviceByAlias(alias,
                alert, content);
        PushPayload ipayloadIOS = JpushUtils.pushIOSDeviceByAlias(alias, alert,
                FormatUtil.toInteger(content));
        System.out.println("----------------------------------------------pushAndIos2");
        // -----------------------------------------------------------------
        try {
            PushResult sendPush = jPushClient.sendPush(ipayload);
            System.out.println("---------------------------------------------------------PushResultpushAndIos2"+sendPush);
            System.out.println("Android推送发出");
            System.out.println("Got result - ios:");
        } catch (APIConnectionException e) {
            System.out.println("Connection error, should retry later" + e);
        } catch (APIRequestException e) {
            System.out.println("Should review the error, and fix the request"
                    + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }

        try {
            jPushClient.sendPush(ipayloadIOS);

            System.out.println("IOS推送发出");
            System.out.println("Got result - android:");
        } catch (APIConnectionException e) {
            System.out.println("Connection error, should retry later" + e);
        } catch (APIRequestException e) {
            System.out.println("Should review the error, and fix the request"
                    + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }

    }

    public static void sendAll(String str){
        JPushClient client = JpushUtils.getJPushClientInstance();
        PushPayload pushPayload = JpushUtils.buildPushObject_all_all_alert(str);
        try {
            PushResult result = jpushClient.sendPush(pushPayload);
            System.out.println("Got result - " + result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            System.out.println("Connection error, should retry later"+ e);
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            System.out.println("Should review the error, and fix the request"+ e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }
    }


    public static void pushAndIos(String alias, String alert, Integer taskId,
                                  Integer userId, String content) {

        JPushClient jPushClient = JpushUtils.getJPushClientInstance();
        PushPayload ipayload = JpushUtils.pushAndroidDeviceByAlias(alias,
                alert, taskId, userId, content);
        PushPayload ipayloadIOS = JpushUtils.pushIOSDeviceByAlias(alias, alert,
                taskId, userId, FormatUtil.toInteger(content));
        System.out.println("--------------------------------------taskid"+taskId);
        // -----------------------------------------------------------------
        try {
            PushResult sendPush = jPushClient.sendPush(ipayload);
            System.out.println("---------------------------------------------------------PushResultpushAndIos"+sendPush);
            System.out.println("Android推送发出");
            System.out.println("Got result - ios:");
        } catch (APIConnectionException e) {
            System.out.println("Connection error, should retry later" + e);
        } catch (APIRequestException e) {
            System.out.println("Should review the error, and fix the request"
                    + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }

        try {
            jPushClient.sendPush(ipayloadIOS);

            System.out.println("IOS推送发出");
            System.out.println("Got result - android:");
        } catch (APIConnectionException e) {
            System.out.println("Connection error, should retry later" + e);
        } catch (APIRequestException e) {
            System.out.println("Should review the error, and fix the request"
                    + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }

    }

	/*
	 * public static void pushOne(String alias,String alert) { JPushClient
	 * jPushClient = JpushUtils.getJPushClientInstance(); PushPayload ipayload =
	 * JpushUtils.pushAndroidDeviceByAlias( alias, alert, "安卓推送"); PushPayload
	 * ipayloadIOS = JpushUtils.pushIOSDeviceByAlias( alias, alert);
	 *
	 * // ----------------------------------------------------------------- try
	 * { jPushClient.sendPush(ipayload);
	 *
	 * System.out.println("推送发出"); System.out.println("Got result - ios:" +
	 * "result1"); } catch (APIConnectionException e) {
	 * System.out.println("Connection error, should retry later" + e); } catch
	 * (APIRequestException e) {
	 * System.out.println("Should review the error, and fix the request" + e);
	 * System.out.println("HTTP Status: " + e.getStatus());
	 * System.out.println("Error Code: " + e.getErrorCode());
	 * System.out.println("Error Message: " + e.getErrorMessage()); }
	 *
	 * try { jPushClient.sendPush(ipayloadIOS);
	 *
	 * System.out.println("推送发出"); System.out.println("Got result - android:" +
	 * "```result"); } catch (APIConnectionException e) {
	 * System.out.println("Connection error, should retry later" + e); } catch
	 * (APIRequestException e) {
	 * System.out.println("Should review the error, and fix the request" + e);
	 * System.out.println("HTTP Status: " + e.getStatus());
	 * System.out.println("Error Code: " + e.getErrorCode());
	 * System.out.println("Error Message: " + e.getErrorMessage()); }
	 *
	 * }
	 */

    /**
     * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
     *
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert(String ALERT) {
        return PushPayload.alertAll(ALERT);
    }

    /**
     * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
     *
     * @return
     */
    public static PushPayload buildPushObject_all_alias_alert(String alias,
                                                              String alert, int marketId, int userId, String content) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))

                .setNotification(Notification.alert(alert))
                .setMessage(
                        Message.newBuilder().setMsgContent(content)
                                .addExtra("task_id", marketId)
                                .addExtra("user_id", userId).build()).build();
    }

    /**
     * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
     *
     * @return
     */
    public static PushPayload pushAllDeviceByAliases(List<String> alias,
                                                     String alert, int marketId, int userId, String content) {

        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))

                .setNotification(Notification.alert(alert))
                .setMessage(
                        Message.newBuilder().setMsgContent(content)
                                .addExtra("task_id", marketId)
                                .addExtra("user_id", userId).build()).build();
    }

    public void test() {
        JPushClient jpushClient = new JPushClient("604e656ed7c7f863843403ca",
                "0d2e05cc975c41e3d0f6a712", 5);
        PushPayload payload = pushAndroidDeviceByAlias("18815276433", "alert",
                150, 107, "活动");

        PushResult result;
        try {
            result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * ios推送
     *
     * @param alias
     * @param alert
     * @param marketId
     * @param userId
     * @param isOrder
     *            是否是邀约
     * @return
     */
	/*
	 * public static PushPayload pushIOSDeviceByAlias(String alias, String
	 * alert, int marketId, int userId, boolean isOrder) { return PushPayload
	 * .newBuilder() .setPlatform(Platform.all())
	 * .setAudience(Audience.alias(alias))
	 *
	 * .setNotification( Notification .newBuilder() .addPlatformNotification(
	 * IosNotification.newBuilder() .setAlert(alert).setBadge(5)
	 * .setSound("happy.caf") .addExtra("task_id", marketId)
	 * .addExtra("user_id", userId) .addExtra("isOrder", isOrder)
	 * .build()).build()) // .setMessage(Message.content("content")) .build(); }
	 */
    /**
     * ios推送
     *
     * @param alias
     * @param alert
     * @param marketId
     * @param userId
     * @param isOrder
     *            是否是邀约
     * @return
     */
    public static PushPayload pushIOSDeviceByAlias(String alias, String alert,
                                                   int marketId, int userId, Integer type) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(
                        Notification
                                .newBuilder()
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .setAlert(alert).setBadge(0)
                                                .setSound("happy.caf")
                                                .addExtra("task_id", marketId)
                                                .addExtra("user_id", userId)
                                                .addExtra("type", type).build())
                                .build())
                // .setMessage(Message.content("content"))
                .build();
    }

    /**
     * ios推送
     *
     * @param alias
     * @param alert
     * @param marketId
     * @param userId
     * @param isOrder
     *            是否是邀约
     * @return
     */
    public static PushPayload pushIOSDeviceByAlias(String alias, String alert,
                                                   Integer type) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(
                        Notification
                                .newBuilder()
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .setAlert(alert).setBadge(0)
                                                .setSound("happy.caf")
                                                .addExtra("type", type).build())
                                .build())
                // .setMessage(Message.content("content"))
                .build();
    }

    /**
     * ios推送
     *
     * @param alias
     * @param alert
     * @param marketId
     * @param userId
     * @param isOrder
     *            是否是邀约
     * @return
     */
    public static PushPayload pushIOSDeviceByAlias(List<String> alias,
                                                   String alert, int marketId, int userId, boolean isOrder) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))

                .setNotification(
                        Notification
                                .newBuilder()
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .setAlert(alert).setBadge(0)
                                                .setSound("happy.caf")
                                                .addExtra("task_id", marketId)
                                                .addExtra("user_id", userId)
                                                .addExtra("isOrder", isOrder)
                                                .build()).build())
                // .setMessage(Message.content("content"))
                .build();
    }

    /**
     * 安卓推送
     *
     * @return
     */
    public static PushPayload pushAndroidDeviceByAlias(String alias,
                                                       String alert, int marketId, int userId, String content) {
        System.out.println("-----------------------------------------userId"+userId);
        System.out.println("-----------------------------------------------marketId"+marketId);
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))

                .setNotification(Notification.alert(alert))
                .setMessage(
                        Message.newBuilder().setMsgContent(content)
                                .addExtra("task_id", marketId)
                                .addExtra("user_id", userId).build()).build();
    }

    /**
     * 安卓推送
     *
     * @return
     */
    public static PushPayload pushAndroidDeviceByAlias(String alias,
                                                       String alert, String content) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .setMessage(Message.newBuilder().setMsgContent(content).build())
                .build();
    }

    /**
     * 安卓推送
     *
     * @return
     */
    public static PushPayload pushAndroidDeviceByAlias(List<String> alias,
                                                       String alert, int marketId, int userId, String content) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))

                .setNotification(Notification.alert(alert))
                .setMessage(
                        Message.newBuilder().setMsgContent(content)
                                .addExtra("task_id", marketId)
                                .addExtra("user_id", userId).build()).build();
    }

    /**
     * 构建推送对象：所有平台，推送多个用户
     *
     * @param alias
     * @param alert
     * @param title
     * @return
     */
    public static PushPayload buildPushObject_all_alias_alert(
            List<String> alias, String alert, String title) {
        return PushPayload.newBuilder().setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(alert, title, null))
                .build();
    }

    /**
     * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
     *
     * @return
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(
            String tag, String alert, String title) {
        return PushPayload.newBuilder().setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(alert, title, null))
                .build();
    }

	/*
	 * public static void push(String alias) { JPushClient jPushClient =
	 * JpushUtils.getJPushClientInstance(); PushPayload apayload =
	 * JpushUtils.pushAndroidDeviceByAlias(alias, "您的账号在其他地方登录!", "顶号");
	 * PushPayload ipayload = JpushUtils.pushIOSDeviceByAlias(alias,
	 * "您的账号在其他地方登录!");
	 *
	 * // ----------------------------------------------------------------- try
	 * { PushResult result1 = jPushClient.sendPush(ipayload);
	 *
	 * System.out.println("推送发出"); System.out.println("Got result - ios:" +
	 * result1); } catch (APIConnectionException e) {
	 * System.out.println("Connection error, should retry later" + e); } catch
	 * (APIRequestException e) {
	 * System.out.println("Should review the error, and fix the request" + e);
	 * System.out.println("HTTP Status: " + e.getStatus());
	 * System.out.println("Error Code: " + e.getErrorCode());
	 * System.out.println("Error Message: " + e.getErrorMessage()); }
	 *
	 * try { PushResult result = jPushClient.sendPush(apayload);
	 *
	 * System.out.println("推送发出"); System.out.println("Got result - android:" +
	 * result); } catch (APIConnectionException e) {
	 * System.out.println("Connection error, should retry later" + e); } catch
	 * (APIRequestException e) {
	 * System.out.println("Should review the error, and fix the request" + e);
	 * System.out.println("HTTP Status: " + e.getStatus());
	 * System.out.println("Error Code: " + e.getErrorCode());
	 * System.out.println("Error Message: " + e.getErrorMessage()); } }
	 */

}
