package com.thinkive.bank.im;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mob.MobSDK;
import com.thinkive.bank.sharesdklibrary.onekeyshare.OnekeyShare;
import com.thinkive.bank.sharesdklibrary.util.ShareUtil;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnQQ;
    private Button mBtnQZone;
    private Button mBtnQQImage;
    private Button mBtnQZoneImage;
    private Button mBtnWeChat;
    private Button mBtnWeChatFriend;
    private Button mBtnWeChatFavorite;
    private Button mBtnWeChatImage;
    private Button mBtnWeChatFriendImage;
    private Button mBtnWeChatFavoriteImage;
    private Button mBtnShareGUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(this, "208e963b3f50d", "073a6672f9510cfa53f333fefb37b73b");
        initViews();
        initEvent();
    }

    private void initViews() {
        mBtnShareGUI = ((Button) findViewById(R.id.btn_share_gui));
        mBtnQQ = ((Button) findViewById(R.id.btn_qq));
        mBtnQZone = ((Button) findViewById(R.id.btn_qqzone));
        mBtnWeChat = (Button) findViewById(R.id.btn_wechat);
        mBtnWeChatFriend = ((Button) findViewById(R.id.btn_wechat_friend));
        mBtnWeChatFavorite = ((Button) findViewById(R.id.btn_wechat_favorite));
        mBtnQQImage = ((Button) findViewById(R.id.btn_qq_image));
        mBtnQZoneImage = ((Button) findViewById(R.id.btn_qqzone_image));
        mBtnWeChatImage = ((Button) findViewById(R.id.btn_wechat_image));
        mBtnWeChatFriendImage = ((Button) findViewById(R.id.btn_wechat_friend_image));
        mBtnWeChatFavoriteImage = ((Button) findViewById(R.id.btn_wechat_favorite_image));
    }

    private void initEvent() {
        mBtnShareGUI.setOnClickListener(this);
        mBtnQQ.setOnClickListener(this);
        mBtnQZone.setOnClickListener(this);
        mBtnWeChat.setOnClickListener(this);
        mBtnWeChatFriend.setOnClickListener(this);
        mBtnWeChatFavorite.setOnClickListener(this);
        mBtnQQImage.setOnClickListener(this);
        mBtnQZoneImage.setOnClickListener(this);
        mBtnWeChatImage.setOnClickListener(this);
        mBtnWeChatFriendImage.setOnClickListener(this);
        mBtnWeChatFavoriteImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_gui:
                showShare();
                break;
            case R.id.btn_qq:
                //比如分享到QQ，其他平台则只需要更换平台类名，例如Wechat.NAME则是微信
                shareToQQ();
                break;
            case R.id.btn_qqzone:
                shareToQZone();
                break;
            case R.id.btn_wechat:
                shareToWeChat();
                break;
            case R.id.btn_wechat_friend:
                shareToWeChatFriend();
                break;
            case R.id.btn_wechat_favorite:
                shareToWeChatFavorite();
                break;
            case R.id.btn_qq_image:
                shareImageToQQ();
                break;
            case R.id.btn_qqzone_image:
                shareImageToQZone();
                break;
            case R.id.btn_wechat_image:
                shareImageToWeChat();
                break;
            case R.id.btn_wechat_friend_image:
                shareImageToWeChatFriend();
                break;
            case R.id.btn_wechat_favorite_image:
                shareImageToWeChatFavorite();
                break;
        }
    }

    /**
     * 显示分享界面
     */
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();

        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        // 定义图标的标签
        String label = "你好";
        // 图标点击后会通过Toast提示消息
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };
        oks.setCustomerLogo(logo, label, listener);
        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 分享到QQ，平台类名——QQ.NAME
     */
    private void shareToQQ() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform QQPlat = ShareSDK.getPlatform(QQ.NAME);
        paramsToShare.put(ShareUtil.KEY_TITLE, "标题");
        paramsToShare.put(ShareUtil.KEY_TEXT, "我是分享文本");
        paramsToShare.put(ShareUtil.KEY_IMAGE_UR, "https://www.baidu.com/img/bd_logo1.png");
        paramsToShare.put(ShareUtil.KEY_TITLE_URL, "http://sharesdk.cn");
        ShareUtil.share(QQPlat.getName(), paramsToShare, this);
    }

    /**
     * 分享到QQ空间，平台类名——QZone.NAME
     */
    private void shareToQZone() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform QZonePlat = ShareSDK.getPlatform(QZone.NAME);
        paramsToShare.put(ShareUtil.KEY_TITLE, "标题");
        paramsToShare.put(ShareUtil.KEY_TEXT, "我是分享文本");
        paramsToShare.put(ShareUtil.KEY_IMAGE_UR, "https://www.baidu.com/img/bd_logo1.png");
        paramsToShare.put(ShareUtil.KEY_TITLE_URL, "http://sharesdk.cn");
        paramsToShare.put(ShareUtil.KEY_COMMENT, "我是测试评论文本");
        paramsToShare.put(ShareUtil.KEY_SITE, "ShareSDK");
        paramsToShare.put(ShareUtil.KEY_SITE_URL, "http://sharesdk.cn");
        ShareUtil.share(QZonePlat.getName(), paramsToShare, this);
    }

    /**
     * 分享到微信好友，平台类名——Wechat.NAME
     */
    private void shareToWeChat() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform weChatPlat = ShareSDK.getPlatform(Wechat.NAME);
        paramsToShare.put(ShareUtil.KEY_TITLE, "标题");
        paramsToShare.put(ShareUtil.KEY_TEXT, "我是分享文本");
        paramsToShare.put(ShareUtil.KEY_IMAGE_UR, "https://www.baidu.com/img/bd_logo1.png");
        paramsToShare.put(ShareUtil.KEY_URL, "http://sharesdk.cn");
        ShareUtil.share(weChatPlat.getName(), paramsToShare, this);
    }

    /**
     * 分享到微信朋友圈，平台类名——WechatMoments.NAME
     */
    private void shareToWeChatFriend() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform weChatFriendPlat = ShareSDK.getPlatform(WechatMoments.NAME);
        paramsToShare.put(ShareUtil.KEY_TITLE, "标题");
        paramsToShare.put(ShareUtil.KEY_TEXT, "我是分享文本");
        paramsToShare.put(ShareUtil.KEY_IMAGE_UR, "https://www.baidu.com/img/bd_logo1.png");
        paramsToShare.put(ShareUtil.KEY_URL, "http://sharesdk.cn");
        ShareUtil.share(weChatFriendPlat.getName(), paramsToShare, this);
    }

    /**
     * 分享到微信收藏夹，平台类名——WechatFavorite.NAME
     */
    private void shareToWeChatFavorite() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform weChatFavoritePlat = ShareSDK.getPlatform(WechatFavorite.NAME);
        paramsToShare.put(ShareUtil.KEY_TITLE, "标题");
        paramsToShare.put(ShareUtil.KEY_TEXT, "我是分享文本");
        paramsToShare.put(ShareUtil.KEY_IMAGE_UR, "https://www.baidu.com/img/bd_logo1.png");
        paramsToShare.put(ShareUtil.KEY_URL, "http://sharesdk.cn");
        ShareUtil.share(weChatFavoritePlat.getName(), paramsToShare, this);
    }

    /**
     * 分享图片到QQ
     */
    private void shareImageToQQ() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform QQImagePlat = ShareSDK.getPlatform(QQ.NAME);
        paramsToShare.put(ShareUtil.KEY_IMAGE_PATH, "/storage/A283-CC03/DCIM/Camera/2.jpg");
        ShareUtil.shareImage(QQImagePlat, paramsToShare);
    }

    /**
     * 分享图片到QQ空间
     */
    private void shareImageToQZone() {
        Map<String, String> paramsToShare = new HashMap<>();
        Platform QZoneImagePlat = ShareSDK.getPlatform(QZone.NAME);
        paramsToShare.put(ShareUtil.KEY_IMAGE_PATH, "/storage/A283-CC03/DCIM/Camera/2.jpg");
        ShareUtil.shareImage(QZoneImagePlat, paramsToShare);
    }

    /**
     * 分享图片到微信好友
     */
    private void shareImageToWeChat() {
        Map<String, String> paramsToShare = new HashMap<>();
        paramsToShare.put(ShareUtil.KEY_IMAGE_PATH, "/storage/A283-CC03/DCIM/Camera/1.jpg");
        Platform weChatImagePlat = ShareSDK.getPlatform(Wechat.NAME);
        ShareUtil.shareImage(weChatImagePlat, paramsToShare);
    }

    /**
     * 分享图片到微信朋友圈
     */
    private void shareImageToWeChatFriend() {
        Map<String, String> paramsToShare = new HashMap<>();
        paramsToShare.put(ShareUtil.KEY_IMAGE_PATH, "/storage/A283-CC03/DCIM/Camera/2.jpg");
        Platform weChatFriendImagePlat = ShareSDK.getPlatform(WechatMoments.NAME);
        ShareUtil.shareImage(weChatFriendImagePlat, paramsToShare);
    }

    /**
     * 分享图片到微信收藏夹
     */
    private void shareImageToWeChatFavorite() {
        Map<String, String> paramsToShare = new HashMap<>();
        paramsToShare.put(ShareUtil.KEY_IMAGE_PATH, "/storage/A283-CC03/DCIM/Camera/2.jpg");
        Platform weChatFavoriteImagePlat = ShareSDK.getPlatform(WechatFavorite.NAME);
        ShareUtil.shareImage(weChatFavoriteImagePlat, paramsToShare);
    }

}
