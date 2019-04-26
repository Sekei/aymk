package com.live.tv;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/8
 */

public final class Constants {


    public static final String BASE_URL = "http://meikang.zhongfeigou.com/";
    //public static final String BASE_URL = "http://192.168.2.18:8080/shop_iemk/";

    public static final String KEY_FRAGMENT = "key_fragment";

    public static final String KEY_TITLE = "key_title";

    public static final String KEY_IS_TAB_LIVE = "key_is_tab_live";

    public static final String KEY_UID = "key_uid";

    public static final String KEY_SLUG = "key_slug";

    public static final String KEY_URL = "key_url";

    public static final String KEY_COVER = "key_cover";

    public static final String USER_BEAN = "userbean";

    public static final String GOODS_ID = "goods_id";

    public static final String ADDRESS_ID = "address_id";

    public static final String ORDER_ID = "order_id";

    public static final String SEND_DOCTOR_NAME = "sendDoctorName";
    public static final String HEALTH_RECORD_ID = "health_record_id";
    public static final String RECORD_NAME = "record_name";
    public static final String RELATION = "relation";
    public static final String TEST_ID = "test_id";

    public static final String RESULT = "result";
    public static final String STEP = "step";
    public static final String ISNEXTAUTO = "is_next_auto";


    public static final String ORDER_NO = "order_no";

    public static final String MONEY = "money";

    public static final String ADDRESS_BEAN = "address_bean";

    public static final String STATE = "state";
    public static final String STATE2 = "state2";

    public static final String TYPE = "type";
    public static final String TAG="tag";
    public static final String OPENID="openid";
    public static final String PAY_TYPE = "pay_type";
    public static final String PRICE = "price";
    public static final String THREE = "three";
    public static final String FOUR = "four";
    public static final String FIVE = "five";
    public static final String DOCTOR_ID = "doctor_id";
    public static final String DOCTOR_NAME = "doctor_name";
    public static final String LONGITUDE="longitude";//经度
    public static final String LATITUDE="latitude";//纬度
    public static final String LOCALITY="locality";//所在地
    public static final String PLATELISTBEAN="platelistbean";//对象





    public static final String IMG = "img";

    public static final String TIME = "time";

    public static final String TEMPLE_ID = "temple_id";

    public static final String TEMPLE_BEAN = "temple_bean";
    public static final String HOTSJ_BEAN = "HotSJListBean";


    public static final String SJ_FL = "SJ_FL";

    public static final String SJ_BT = "SJ_BT";
    public static final String RECORD_BEAN = "RECORD_BEAN";
    public static final String GoodsByStandardBean = "GoodsByStandardBean";

    public static final String WEIXIN_SHARE_ID = "wxc57f59a0f4eac3b4";
    public static final String WEIXIN_SHARE_SECRECT = "ff2120adc4842d62cea329be035a14fc";
    public static final String WEXAPPID = "wxc57f59a0f4eac3b4";
    public static final String SINA_WEIBO_SHARE_ID = "";
    public static final String SINA_WEIBO_SHARE_SECRECT = "";
    public static final String SINA_WEIBO_SHARE_REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
    /*public static final String QQZONE_SHARE_ID = "";//
    public static final String QQZONE_SHARE_SECRECT = "";*/

    public static final String QQZONE_SHARE_ID = "1106558951";
    public static final String QQZONE_SHARE_SECRECT = "TMjaEAce3ZbO8QFn";


    public static final String SHOPPINGCART_LIST = "shoppingcart_list";
    /**
     * showing
     */
    public static final String SHOWING = "showing";

    public static final String IS_ZL = "IS_ZL";
    public static final String PLATE_ID="plate_id";//帖子的id


    public static final String SEX = "sex";
    public static final String USERNAME = "USERNAME";
    public static final String AUTOGRAPH = "autograph";
    public static final String USERCENTERBEAN = "usercenterbean";
    public static final String STARTLIVEBEAN = "startlivebaen";
    public static final String LOOKLIVEBEAN = "lookliveBean";
    public static final String GIFTLIST = "gift_list";
    public static final String ACCOUNTS = "ACCOUNTS";
    public static final String POIITEM = "poiItem";


    public static final String TIPS_MSG_STOP_PUSH = "当前正在直播，是否退出直播？";
    //直播端错误信息
    public static final String ERROR_MSG_CREATE_GROUP_FAILED = "创建直播房间失败,Error:";
    public static final String ERROR_MSG_GET_PUSH_URL_FAILED = "拉取直播推流地址失败,Error:";
    public static final String ERROR_MSG_OPEN_CAMERA_FAIL = "无法打开摄像头，需要摄像头权限";
    public static final String ERROR_MSG_OPEN_MIC_FAIL = "无法打开麦克风，需要麦克风权限";
    public static final String ERROR_MSG_RECORD_PERMISSION_FAIL = "无法进行录屏,需要录屏权限";
    public static final String ERROR_MSG_NO_LOGIN_CACHE = "您的帐号已在其它地方登录";

    public static final String ERROR_PUBLIS_ADDR = "无效的推流地址 !";

    //环信
    public static final String ARG_ANCHOR = "anchor";
    public static final String ARG_IS_NORMAL = "isNormalStyle";
    public static final String ARG_IS_ADMIN = "is_admin";
    public static final String EXTRA_CHAT_TYPE = "chatType";
    public static final String EXTRA_USER_ID = "userId";
    public static final String EXTRA_USER_NAME = "username";
    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;

    public static final String WANG_HONG_ID = "wang_hong_id";
    public static final String LIVE_ID = "LIVE_ID";
    public static final String CITY = "city";
    /**
     * IM 互动消息类型
     */
    public static final int IMCMD_PAILN_TEXT = 1;   // 文本消息
    public static final int IMCMD_ENTER_LIVE = 2;   // 用户加入直播
    public static final int IMCMD_EXIT_LIVE = 3;   // 用户退出直播
    public static final int IMCMD_PRAISE = 4;   // 点赞消息
    public static final int IMCMD_DANMU = 5;   // 弹幕消息
    public static final int IMCMD_EXIT_ZHUBO = 6;   // 主播退出直播
    public static final int IMCMD_GIFT = 7;   // 礼物消息
    public static final int IMCMD_DASHANG = 8;   // 打赏

    //直播端右下角listview显示type
    public static final int TEXT_TYPE = 0;
    public static final int MEMBER_ENTER = 1;
    public static final int MEMBER_EXIT = 2;
    public static final int PRAISE = 3;

    //网络类型
    public static final int NETTYPE_WIFI = 0;
    public static final int NETTYPE_NONE = 1;
    public static final int NETTYPE_2G = 2;
    public static final int NETTYPE_3G = 3;
    public static final int NETTYPE_4G = 4;

    // 区分主播和副主播
    public static final String IS_ROLE_ANCHOR = "IS_ROLE_ANCHOR";
    public static final int RTC_ROLE_ANCHOR = 0X49;
    public static final int RTC_ROLE_VICE_ANCHOR = 0X48;
    public static final int MESSAGE_ID_RECONNECTING = 0x47;
    public static final boolean IS_USING_STREAMING_JSON = false;

    public static final String anchorStopLive = "anchorStopLive";
    public static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量


    public static final int WRITE_PERMISSION_REQ_CODE = 2;
    public static final int TOKEN_EXPRIED = 0X98;
    public static final int HEALTH_ID = 1025;
    public static final int REQUESTCODE = 1;
    public static final int INTENT_TEST_DATA = 1000;//测试诊断报告 intent
    public static final int INTENT_HEALTH_FILE = 1001;//健康档案intent
    public static final int INTENT_MEDICAL_RECORD = 1002;//电子病历intent
    public static final int INTENT_PAY_SUCCESS = 2001;//支付成功
    //-----------------------------------------
    public static final int ROOM_FRAGMENT = 0X01;
    public static final int LIVE_FRAGMENT = 0X02;
    public static final int WEB_FRAGMENT = 0X03;
    public static final int DOCTOR_LIST = 0X04;
    public static final int DOCTOR_DETAIL = 0X05;
    public static final int TEST = 0X06;
    public static final int REQUEST_ONE = 0X07;
    public static final int CHOOSE_TIME = 0X08;
    public static final int COMMON_TEST = 0X09;
    public static final int PEROFESSIONAL_TEST = 0X10;
    public static final int SEND_HEART = 0X11;
    public static final int HEALTH_FILE = 0X12;
    public static final int MORE_FILE = 0X13;
    public static final int PAY_SERVER = 0X14;
    public static final int PAY_SUCCESS = 0X15;
    public static final int HEALTH_MANAGE = 0X16;
    public static final int TEST_HOMEPAGE = 0X17;
    public static final int TEST_HISTORY = 0X18;
    public static final int TEST_DESCRIPTION = 0X19;
    public static final int TEST_COMPLETE = 0X20;
    public static final int TEST_DIAGNOSE_REPORT = 0X21;
    public static final int TEST_RESULT_TAB = 0X22;
    public static final int TEST_RESULT_TAB_NEXT = 0X23;
    public static final int TEST_RESULT_TAB_PLAN = 0X24;
    public static final int TEST_COMMON_RESULT = 0X25;
    public static final int MEDICAL_RECORD = 0X26;
    public static final int MEDICAL_RECORD_MONTH = 0X27;
    public static final int MEDICAL_RECORD_DETAIL = 0X28;
    public static final int HEALTH_DATA = 0X29;
    public static final int HEALTH_DATA_DETAIL = 0X30;
    public static final int SEARCH_CITY = 0X31;
    public static final int GROUP = 0X32;
    public static final int COMMUNITY = 0X33;
    public static final int VERIFIED = 0X34;
    public static final int VERIFIED_SUCCESS = 0X35;
    public static final int VERIFIED_IMG = 0X36;
    public static final int JOIN = 0X37;
    public static final int JOIN_SHOP = 0X38;
    public static final int JOIN_DOCTOR = 0X39;
    public static final int JOIN_HOUSE = 0X40;
    public static final int gg = 0X41;
    public static final int j = 0X42;
    public static final int y = 0X43;
    public static final int hh = 0X44;
    public static final int mm = 0X45;
    public static final int nj = 0X46;
    public static final int yh = 0X47;
    public static final int o = 0X48;
    public static final int rr = 0X49;
    public static final int vg = 0X50;

    public static final int SZ = 0X51;
    public static final int PERSONAL_INFO = 0X52;
    public static final int FOCUS = 0X53;
    public static final int FANS = 0X54;
    public static final int MY_COLLECTION = 0X55;
    public static final int HEALTH_STEP = 0X56;
    public static final int HEALTH_STEP_DETAIL = 0X58;
    public static final int ADD_FILE = 0X59;
    public static final int MY_ACDVISORY = 0X60;
    public static final int COMMUNTITY_LIST = 0X61;
    public static final int LOGIN_FRAGMENT = 0X62;
    public static final int REGISTER = 0X63;
    public static final int TEST_PHYSIQUE = 0X64;
    public static final int TEST_RESULT_TAB_PLAN_PROFESSION = 0X65;
    public static final int TEST_PLAN_PROFESSION = 0X66;
    public static final int MSG = 0X67;
    public static final int PERSONAL_HOME_PAGE = 0X68;
    public static final int DOCTORFILE_FRAGMENT = 0X69;
    public static final int IMGAGEEDIT_FRAGMENT = 0X70;
    public static final int PHONEEEDIT_FRAGMENT = 0X71;
    public static final int TIMESETTING_FRAGMENT = 0X72;//时段设置
    public static final int TIMESETTING_FRAGMENT2 = 0X73;//时段设置
    public static final int VIDEOEEDIT_FRAGMENT = 0X74;//视频咨询设置
    public static final int SERVICETYPE_FRAGMENT = 0X75;//服务类型设置
    public static final int SERVICETYPESETTING_FRAGMENT = 0X76;//体验类型设置
    public static final int DOCTORINFO_FRAGMENT = 0X77;//医生个人资料
    public static final int HEALTHUSERLIST_FRAGMENT = 0X78;//健康用户列表
    public static final int HEALTHUSERDETAIL_FRAGMENT = 0X79;//健康用户详情
    public static final int DOCTORCONSULTATION_FRAGMENT = 0X80;//医生， 咨询列表
    public static final int JOINDOCTORSUCCESS_FRAGMENT = 0X81;//医生入驻 成功
    public static final int BUYCONSULTVIDEO_FRAGMENT = 0X82;//购买语音／视频
    public static final int BUYCONSULTIMAGE_FRAGMENT = 0X83;//购买图文咨询
    public static final int BUYCONSULTHEALTH_FRAGMENT = 0X84;//购买健康管理
    public static final int MORECONSULTANT_FRAGMENT = 0X85;//修改咨询人
    public static final int UPDATEPAYPWD_FRAGMENT = 0X86;//修改支付密码
    public static final int UPDATELOGINPWD_FRAGMENT = 0X87;//修改登录密码
    public static final int ACCOUNT_SECURITY_FRAGMENT = 0X88;//账号安全
    public static final int UPLOAD_CASE_FRAGMENT = 0X89;//上传病历
    public static final int MY_ACCOUNT_FRAGMENT = 0X90;//我的账户
    public static final int CHONGZHI_FRAGMENT = 0X91;//充值
    public static final int DETAIL_FRAGMENT = 0X92;//明细
    public static final int MY_PROFIT_FRAGMENT = 0X93;//我的收益
    public static final int MY_ORDER_FRAGMENT = 0X94;//我的订单
    public static final int MY_DOCTOR_FRAGMENT = 0X95;//我的医生
    public static final int GIVE_HEART_FRAGMENT = 0X96;//支付送心意
    public static final int History_Advisory_Fragment = 0X97;//历史咨询
    public static final int BUY_PLAN_FRAGMENT = 0X98;//购买调理方案
    public static final int MY_VIP_FRAGMENT = 0X99;//我的会员
    public static final int VOICE_VIDEO_CALLFRAGMENT = 0X100;//语音和视频的拨打界面
    public static final int Message_Remind_Fragment = 0X101;//消息提醒
    public static final int ADDRESS_FRAGMENT = 0X102;//地址管理
    public static final int VIP_DETAIL_FRAGMENT = 0X103;//会员详情
    public static final int BUY_VIP_FRAGMENT = 0X104;//购买会员
    public static final int UPDATE_FILE_FRAGMENT = 0X105;//修改健康档案
    public static final int EDIT_PERSONALINFO_FRAGMENT = 0X106;//编辑个人介绍
    public static final int CLASS_GOODS_LIST_FRAGMENT = 0X107;//分类商品列表
    public static final int ALL_CLASS_FRAGMENT = 0X108;//所有分类
    public static final int FORGETLOGINPWD_NEXT_FRAGMENT = 0X109;//找回密码下一步
    public static final int FORGETLOGINPWD_FRAGMENT = 0X110;//找回密码
    public static final int DOCTOR_PERSONAL_INFO_FRAGMENT = 0X111;//医生个人介绍
    public static final int BIND_ACCOUNT_FRAGMENT = 0X112;//绑定账户列表
    public static final int BIND_ALIPAY_FRAGMENT = 0X113;//绑定支付宝
    public static final int BIND_WX_FRAGMENT = 0X114;//绑定微信
    public static final int TIXIAN_FRAGMENT = 0X115;//提现
    public static final int VERIFY_PHONE_FRAGMENT = 0X116;//验证手机号
    public static final int GOOD_DETAIL_FRAGMENT = 0X117;//商品详情
    public static final int CART_FRAGMENT = 0X118;//购物车
    public static final int GOOD_ORDER_DETAIL_FRAGMENT = 0X119;//商品订单详情
    public static final int EDIT_ALLERGY_CONTENT_FRAGMENT = 0X120;//编辑过敏史内容
    public static final int TESTDATA_HUANXIN_FRAGMENT = 0X121;//聊天中查看测试结果
    public static final int ADD_PLAN_FRAGMENT = 0X122;//添加方案
    public static final int SEND_HEALTH_FILE_FRAGMENT = 0X123;//发送健康档案
    public static final int HUANXIN_HEALTH_STEP_FRAGMENT = 0X124;//环信查看发送的健康档案
    public static final int FANKUI_FRAGMENT = 0X125;//意见反馈
    public static final int ABOUTUS = 0X225;//意见反馈
    public static final int ASSESSMENT_DOCTOR_FRAGMENT = 0X126;//评价医生
    public static final int CONFIRM_GOODORDER_FRAGMENT = 0X127;//提交订单
    public static final int ADD_ADDRESS_FRAGMENT = 0X128;//添加地址
    public static final int BUY_GOOD_FRAGMENT = 0X129;//购买商品支付
    public static final int SERVICE_GOOD_DETAIL_FRAGMENT = 0X130;//服务商品详情
    public static final int CONFIRM_SERVICEGOODORDER_FRAGMENT = 0X131;//提交服务商品订单
    public static final int SERVICE_GOODORDER_DETAILFRAGMENT = 0X132;//服务订单详情
    public static final int CONFIEM_ORDER_VERIFYPHONE_FRAGMENT = 0X133;//提交订单修改手机号
    public static final int MY_LIVELIST_FRAGMENT = 0X134;//我的直播列表
    public static final int ASSESSMENT_ORDER_FRAGMENT = 0X135;//评价订单
    public static final int APPLY_AFTER_SALE_FRAGMENT = 0X136;//申请售后
    public static final int AFTER_SALE_ORDER_LIST_FRAGMENT = 0X137;//申请售后
    public static final int AFTER_SALE_ORDER_DETAIL_FRAGMENT = 0X138;//售后订单详情
    public static final int MORE_ASSESSMENT_LIST_FRAGMENT = 0X139;//更多评价
    public static final int Merchants_Detail_Fragment = 0X140;//商家详情
    public static final int Famous_Doctor_LiveList_Fragment = 0X141;//名医直播列表
    public static final int MORE_NEARBY_SHOPLIST_FRAGMENT = 0X142;//更多附近门店
    public static final int HOT_GOODSLIST_FRAGMENT = 0X143;//更多热卖商品
    public static final int Start_Live_Fragment = 0X144;//开始直播
    public static final int MERCHANTS_MAP_FRAGMENT = 0X145;//门店详情
    public static final int SEARCH_FRAGMENT = 0X146;//搜索总页面
    public static final int SEARCH_HISTORY_FRAGMENT = 0X147;//搜索历史
    public static final int MORESEARCH_DOCTOR_FRAGMENT = 0X148;//搜索更多医生
    public static final int MORESEARCH_MERCHANTS_FRAGMENT = 0X149;//搜索更多商家
    public static final int MORESEARCH_GOOD_FRAGMENT = 0X150;//搜索更多商品
    public static final int HOUSEKEEPING_FRAGMENT = 0X151;//家政服务
    public static final int HOUSEKEEPING_DETAIL_FRAGMENT = 0X152;//家政服务详情
    public static final int MEDICAL_RECORDS_DETAIL_HX_FRAGMENT = 0X153;//环信 电子病历详情
    public static final int MEDICAL_RECORDS_HX_FRAGMENT = 0X154;//环信 电子病历列表
    public static final int RELEASE_HOUSEKEEP_FRAGMENT = 0X155;//发布家政信息
    public static final int COMMUNITYDETAILS = 0X156;//帖子详情
    public static final int POSTEDFRGAMENT = 0X157;//帖子详情
    public static final int POSTEDListFRGAMENT = 0X158;//帖子(医生发帖界面)
    public static final int POSTEDETAILS = 0X159;//健康详情界面
    public static final int MERCHANTFORMFRAGMENT = 0X160;//商家订单
    public static final int SERVICEDETILS = 0X161;//服务详情
    public static final int COMPLAINTFRAGMENT = 0X162;//投诉
    public static final int MERCHANTMESSAGE = 0X163;//商家消息
    public static final int MERCHANTABOUT = 0X164;//商家简介
    public static final int SERVICECOPE = 0X165;//服务范围
    public static final int EDITSERVICE = 0X166;//编辑服务
    public static final int MERCHANTMOREADDRESS = 0X167;//商家更多地址
    public static final int ADDADDRESS = 0X168;//商家添加地址
    public static final int SERVICEAPPRAISE = 0X169;//服务评价
    public static final int SERVICEDRAWBACK = 0X170;//服务申请退款
    public static final int SERVICEMOREASSESSMENT = 0X171;//服务更多评论列表
    public static final int SHOPFRAGMENT = 0X172;//商城界面
    public static final int ADDHEALTHMANAGE = 0X173;//添加健康管理
    public static final int COMMUNICATEFRAGMENT = 0X174;//交流社区
    public static final int MESSAGELIST = 0X204;//消息列表
    public static final int MESSAGESEND = 0X205;//消息发送
/***
 * 字体颜色
 */
//灰色
  public static final int COLOR_GRAY = 0xffa9b7b7;
    public static final String KEY_COLOR_GRAY = "color:#a9b7b7;";
    //深蓝
    public static final int COLOR_BLACKGRAY = 0xff33475f;
    public static final String KEY_COLOR_BLACKGRAY = "color:#33475f;";
    //白色
    public static final int COLOR_WHITE = 0xffecf0f1;
    public static final String KEY_COLOR_WHITE = "color:#ecf0f1;";
    //蓝色
    public static final int COLOR_BLUE = 0xff56abe4;
    public static final String KEY_COLOR_BLUE = "color:#56abe4;";
    //绿色
    public static final int COLOR_GREEN = 0xff11cd6e;
    public static final String KEY_COLOR_GREEN = "color:#11cd6e;";
    //黄色
    public static final int COLOR_YELLOW = 0xfff4c600;
    public static final String KEY_COLOR_YELLOW = "color:#f4c600;";
    //紫色
    public static final int COLOR_VOILET = 0xff9d55b8;
    public static final String KEY_COLOR_VOILET = "color:#9d55b8;";
    //红色
    public static final int COLOR_RED = 0xffeb4f38;
    public static final String KEY_COLOR_RED = "color:#eb4f38;";
    //黑色
    public static final int COLOR_BLACK = 0xff272636;
    public static final String KEY_COLOR_BLACK = "color:#272636;";
//字体大小
    //最大
    public static final int SIZE_18 = 18;
    public static final String KEY_SIZE_18 = "font-size:18px;";
    //默认
    public static final int SIZE_16 = 16;
    public static final String KEY_SIZE_16 = "font-size:16px;";
    //最小
    public static final int SIZE_14 = 14;
    public static final String KEY_SIZE_14 = "font-size:14px;";

}
