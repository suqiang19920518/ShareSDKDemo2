package com.thinkive.bank.sharesdklibrary.util;


import android.content.Context;

import com.thinkive.bank.sharesdklibrary.onekeyshare.OnekeyShare;

import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * @author: sq
 * @date: 2017/9/4
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 分享封装类【支持QQ、QQ空间、微信、微信朋友圈、微信收藏夹】
 */
public class ShareUtil {

    public static final String KEY_TITLE = "title";
    public static final String KEY_TITLE_URL = "titleUrl";
    public static final String KEY_TEXT = "text";
    public static final String KEY_IMAGE_UR = "imageUrl";
    public static final String KEY_IMAGE_PATH = "imagePath";
    public static final String KEY_URL = "url";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_SITE = "site";
    public static final String KEY_SITE_URL = "siteUrl";

    /**
     * 分享图文
     *
     * @param platform
     * @param paramsToShare
     * @param context
     */
    public static void share(String platform, Map<String, String> paramsToShare, Context context) {

        String title = paramsToShare.get(KEY_TITLE);
        String titleUrl = paramsToShare.get(KEY_TITLE_URL);
        String text = paramsToShare.get(KEY_TEXT);
        String imageUrl = paramsToShare.get(KEY_IMAGE_UR);
        String url = paramsToShare.get(KEY_URL);
        String comment = paramsToShare.get(KEY_COMMENT);
        String site = paramsToShare.get(KEY_SITE);
        String siteUrl = paramsToShare.get(KEY_SITE_URL);

        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        if (title != null) {
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
            oks.setTitle(title);
        }
        if (titleUrl != null) {
            // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
            oks.setTitleUrl(titleUrl);
        }
        if (text != null) {
            // text是分享文本，所有平台都需要这个字段
            oks.setText(text);
        }
        if (imageUrl != null) {
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            oks.setImageUrl(imageUrl);
        }
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        if (url != null) {
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl(url);
        }
        if (comment != null) {
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment(comment);
        }
        if (site != null) {
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(site);
        }
        if (siteUrl != null) {
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl(siteUrl);
        }

        //启动分享
        oks.show(context);
    }

    /**
     * 分享图片(支持QQ、QQ空间、微信、微信朋友圈、微信收藏夹)
     *
     * @param platform
     * @param paramsToShare
     */
    public static void shareImage(Platform platform, Map<String, String> paramsToShare) {

        String title = paramsToShare.get(KEY_TITLE);
        String text = paramsToShare.get(KEY_TEXT);
        String imagePath = paramsToShare.get(KEY_IMAGE_PATH);

        Platform.ShareParams sp = null;
        if (platform.getName().equalsIgnoreCase(Wechat.NAME)) {
           sp = new Wechat.ShareParams();
        }else if (platform.getName().equalsIgnoreCase(WechatMoments.NAME)){
            sp = new WechatMoments.ShareParams();
        } else if (platform.getName().equalsIgnoreCase(WechatFavorite.NAME)){
            sp = new WechatFavorite.ShareParams();
        } else if (platform.getName().equalsIgnoreCase(QQ.NAME)){
            sp = new QQ.ShareParams();
        } else if (platform.getName().equalsIgnoreCase(QZone.NAME)){
            sp = new QZone.ShareParams();
        }
        if (sp == null){
            return;
        }

        //任何分享类型都需要title和text
        //the two params of title and text are required in every share type
        if (title != null) {
            sp.setTitle(title);
        }
        if (text != null) {
            sp.setText(text);
        }
        if (imagePath != null) {
            sp.setImagePath(imagePath);
        }
        sp.setShareType(Platform.SHARE_IMAGE);
        platform.share(sp);
    }
}
