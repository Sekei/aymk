package com.live.tv.http;

import com.live.tv.bean.AddressBean;
import com.live.tv.bean.AfterSaleOrderBean;
import com.live.tv.bean.AllDoctorAssessesBean;
import com.live.tv.bean.AppStart;
import com.live.tv.bean.AssessmentBean;
import com.live.tv.bean.AssociatorBean;
import com.live.tv.bean.BalanceBean;
import com.live.tv.bean.BannerBean;
import com.live.tv.bean.CartBean;
import com.live.tv.bean.CheckLeadFactorsBean;
import com.live.tv.bean.CheckQuestionBean;
import com.live.tv.bean.ClassBean;
import com.live.tv.bean.CollectionBean;
import com.live.tv.bean.CommAllBean;
import com.live.tv.bean.CommunityBannerBean;
import com.live.tv.bean.ConsultBean;
import com.live.tv.bean.ConsultTimesBean;
import com.live.tv.bean.DepartmentLevelOneBean;
import com.live.tv.bean.DoctorDetailBean;
import com.live.tv.bean.DoctorTitleBean;
import com.live.tv.bean.FetureDateBeans;
import com.live.tv.bean.GiftBean;
import com.live.tv.bean.GoodsBean;
import com.live.tv.bean.GoodsSpecificationBeans;
import com.live.tv.bean.HealthDataBean;
import com.live.tv.bean.HealthFileBean;
import com.live.tv.bean.HealthManagerBeans;
import com.live.tv.bean.HealthPlanBean;
import com.live.tv.bean.HealthRecordDetailBean;
import com.live.tv.bean.HeartBean;
import com.live.tv.bean.HomeButtonBean;
import com.live.tv.bean.HomeHealthDetail;
import com.live.tv.bean.HomeImgsBean;
import com.live.tv.bean.HomeMessageBean;
import com.live.tv.bean.HouseKeepTypeBean;
import com.live.tv.bean.HousekeepBean;
import com.live.tv.bean.HtmlBean;
import com.live.tv.bean.LeadFactorsBean;
import com.live.tv.bean.LiveBean;
import com.live.tv.bean.MedicalClassBean;
import com.live.tv.bean.MedicalListBean;
import com.live.tv.bean.MemberMsgsBean;
import com.live.tv.bean.MerchantHotBean;
import com.live.tv.bean.MerchantsBean;
import com.live.tv.bean.NewsBean;
import com.live.tv.bean.OrderBean;
import com.live.tv.bean.PlanDetailBean;
import com.live.tv.bean.PlanPriceBean;
import com.live.tv.bean.PlateListBean;
import com.live.tv.bean.PriceBean;
import com.live.tv.bean.ProfitBean;
import com.live.tv.bean.QuestionBean;
import com.live.tv.bean.RefundsReasons;
import com.live.tv.bean.RelationsBean;
import com.live.tv.bean.ReportBean;
import com.live.tv.bean.ReportHistoryBean;
import com.live.tv.bean.SearchBean;
import com.live.tv.bean.ServiceCommentBean;
import com.live.tv.bean.ServiceCopeBean;
import com.live.tv.bean.ShaixuanBean;
import com.live.tv.bean.SimplePlansBean;
import com.live.tv.bean.StartTestBean;
import com.live.tv.bean.SystemHtmlBean;
import com.live.tv.bean.TestRecordBean;
import com.live.tv.bean.TextandImgBean;
import com.live.tv.bean.TypeServiceBean;
import com.live.tv.bean.UserBean;
import com.live.tv.bean.WxPayMode;
import com.live.tv.mvp.activity.live.bean.MemberAvoidBean;
import com.live.tv.mvp.fragment.home.ConsultWeek;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * @author zz
 * @since 2017/2/13
 */

/**
 * Created by zz on 2017/1/11.
 *
 * @GET 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 */
public interface APIService {

    /**
     * 获取App启动页信息
     *
     * @return
     */
    @GET("json/page/app-data/info.json?v=3.0.1&os=1&ver=4")
    Observable<AppStart> getAppStartInfo();


    /**
     * 用户注册
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?registerMember")
    Observable<HttpResult<UserBean>> registerMember(@FieldMap Map<String, String> parmer);

    /**
     * 修改登录密码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?memberForgetPassword")
    Observable<HttpResult<String>> UpdateLoginPwd(@FieldMap Map<String, String> parmer);

    /**
     * 修改支付密码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateMemberPayPassword")
    Observable<HttpResult<String>> UpdatePayPwd(@FieldMap Map<String, String> parmer);


    /**
     * 发送验证码
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?sendCode")
    Observable<HttpResult<String>> sendCode(@FieldMap Map<String, String> parmer);

    /**
     * 验证手机号是否正确
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?getCode")
    Observable<HttpResult<String>> VerifyPhone(@FieldMap Map<String, String> parmer);


    /**
     * 验证手机号是否正确
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateMember")
    Observable<HttpResult<String>> updateMember(@FieldMap Map<String, String> parmer);


    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?loginMember")
    Observable<HttpResult<UserBean>> loginMember(@FieldMap Map<String, String> parmer);

    /**
     * 绑定支付宝
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?bindingAlipay")
    Observable<HttpResult<String>> bindingAlipay(@FieldMap Map<String, String> parmer);

    /**
     * 绑定微信
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?bindingWx")
    Observable<HttpResult<String>> bindingWx(@FieldMap Map<String, String> parmer);


    /**
     * 用户详情
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getMemberDetail")
    Observable<HttpResult<UserBean>> getMemberDetail(@FieldMap Map<String, String> parmer);

    /**
     * 医生档案详情
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?getMemberDoctorDetail")
    Observable<HttpResult<UserBean>> getMemberDoctorDetail(@FieldMap Map<String, String> parmer);


    /**
     * 获取首页轮播
     *
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?getAllBanners")
    Observable<HttpResult<List<BannerBean>>> getBanner(@FieldMap Map<String, String> parmer);

    /**
     * 首页6个按钮
     *
     * @return
     */
    @FormUrlEncoded
    @POST("homeButtonInterfaces.api?getHomeButton")
    Observable<HttpResult<List<HomeButtonBean>>> getHomeButton(@FieldMap Map<String, String> parmer);

    /**
     * 医师列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?getDoctorList")
    Observable<HttpResult<List<DoctorDetailBean>>> getDoctorList(@FieldMap Map<String, String> parmer);

    /**
     * 轮播消息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("ww")
    Observable<HttpResult<List<NewsBean>>> getMessageList(@FieldMap Map<String, String> parmer);


    /**
     * 首页三张图片
     *
     * @return
     */
    @POST("settingInterfaces.api?getHomeImgs")
    Observable<HttpResult<List<HomeImgsBean>>> getHomeImgs();

    /**
     * 电子病历分类
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getMedicalClassBeans")
    Observable<HttpResult<List<MedicalClassBean>>> getMedicalClassBeans(@FieldMap Map<String, String> parmer);

    /**
     * 电子病历分类
     *
     * @return
     */
    @POST("memberInterfaces.api?getMedicalClassBeans")
    Observable<HttpResult<List<MedicalClassBean>>> getMedicalClass();


    /**
     * 电子病历列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?medicalList")
    Observable<HttpResult<List<MedicalListBean>>> medicalList(@FieldMap Map<String, String> parmer);

    /**
     * 健康数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?healthDtatList")
    Observable<HttpResult<List<HealthDataBean>>> healthDtatList(@FieldMap Map<String, String> parmer);


    /**
     * 图文咨询
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?textConsult")
    Observable<HttpResult<String>> textConsult(@FieldMap Map<String, String> parmer);

    /**
     * 结束 图文咨询
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateConsultState")
    Observable<HttpResult<String>> updateConsultState(@FieldMap Map<String, String> parmer);


    /**
     * 医生入住
     * 申请医生
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?applyDoctor")
    Observable<HttpResult<String>> GetapplyDoctor(@FieldMap Map<String, String> parmer);


    /**
     * 申请家政
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?applyHouseService")
    Observable<HttpResult<String>> applyHousekeep(@FieldMap Map<String, String> parmer);


    /**
     * 商家入住
     * 申请医生
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?applyMerchants")
    Observable<HttpResult<String>> applyMerchants(@FieldMap Map<String, String> parmer);


    /**
     * 上传病例
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?insertMedical")
    Observable<HttpResult<String>> InsertMedical(@FieldMap Map<String, String> parmer);

    /**
     * 评价医生
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?assessmentDoctor")
    Observable<HttpResult<String>> assessmentDoctor(@FieldMap Map<String, String> parmer);

    /**
     * 评价商品订单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?assessmentOrder")
    Observable<HttpResult<String>> assessmentOrder(@FieldMap Map<String, String> parmer);


    /**
     * 上传多张图片
     *
     * @return
     */
    @POST("settingInterfaces.api?uploadImgs")
    Observable<HttpResult<String[]>> uploadImgs(@Body RequestBody Body);


    /**
     * 上传图片
     * settingInterfaces.api?uploadImgs
     *
     * @return
     */


    @Multipart
    @POST("settingInterfaces.api?uploadImgs")
    Observable<HttpResult<String[]>> uploadImg(@Part List<MultipartBody.Part> cover);

    /**
     * 上传图片
     * settingInterfaces.api?uploadImgs
     *
     * @return
     */


    @Multipart
    @POST("settingInterfaces.api?uploadImgs")
    Observable<HttpResult<List<String>>> uploadImgList(@Part List<MultipartBody.Part> cover);


    /**
     * 健康档案列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?recordList")
    Observable<HttpResult<List<HealthFileBean>>> recordList(@FieldMap Map<String, String> parmer);

    /**
     * 添加健康档案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?insertRecord")
    Observable<HttpResult<String>> insertRecord(@FieldMap Map<String, String> parmer);
    /**
     * 添加地址(家政)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?insertHouseAddress")
    Observable<HttpResult<String>> getaddaddress(@FieldMap Map<String, String> parmer);


    /**
     * 删除健康档案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?deleteRecord")
    Observable<HttpResult<String>> DelRecord(@FieldMap Map<String, String> parmer);
    /**
     * 服务范围
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getHouseServices")
    Observable<HttpResult<List<ServiceCopeBean>>> getserviceCope(@FieldMap Map<String, String> parmer);

    /**
     * 删除病历
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?deleteMedicalImg")
    Observable<HttpResult<String>> deleteMedicalImg(@FieldMap Map<String, String> parmer);

    /**
     * 病历详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?medicalDetail")
    Observable<HttpResult<MedicalListBean>> medicalDetail(@FieldMap Map<String, String> parmer);

    /**
     * 修改健康档案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?updateRecord")
    Observable<HttpResult<String>> UpdateRecord(@FieldMap Map<String, String> parmer);


    /**
     * 健康档案详情
     * healthRecordInterfaces.api?getHealthStage
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?healthRecordDetail")
    Observable<HttpResult<HealthRecordDetailBean>> healthRecordDetail(@FieldMap Map<String, String> parmer);

    /**
     * 我的健康档案详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?myHealthRecordDetail")
    Observable<HttpResult<HealthRecordDetailBean>> myHealthRecordDetail(@FieldMap Map<String, String> parmer);

    /**
     * 未发送的方案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?getHealthPlan")
    Observable<HttpResult<HealthPlanBean>> getHealthPlan(@FieldMap Map<String, String> parmer);

    /**
     * 添加方案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?insertHealthPlan")
    Observable<HttpResult<String>> insertHealthPlan(@FieldMap Map<String, String> parmer);


    /**
     * 发布方案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?insertAdvice")
    Observable<HttpResult<String>> insertAdvice(@FieldMap Map<String, String> parmer);

    /**
     * 发送电子病历
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?sendMedical")
    Observable<HttpResult<String>> sendMedical(@FieldMap Map<String, String> parmer);


    /**
     * 发送测试数据
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?sendTestRecord")
    Observable<HttpResult<String>> sendTestRecord(@FieldMap Map<String, String> parmer);


    /**
     * 发送健康档案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?sendHealthPlan")
    Observable<HttpResult<String>> sendHealthPlan(@FieldMap Map<String, String> parmer);


    /**
     * 开启测试
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?startTest")
    Observable<HttpResult<StartTestBean>> startTest(@FieldMap Map<String, String> parmer);

    /**
     * 测试问题题目
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getQuestionBean")
    Observable<HttpResult<QuestionBean>> getQuestionBean(@FieldMap Map<String, String> parmer);


    /**
     * 测试问题
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?checkQuestion")
    Observable<HttpResult<CheckQuestionBean>> checkQuestion(@FieldMap Map<String, String> parmer);

    /**
     * 确认结论
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?checkResult")
    Observable<HttpResult<String>> checkResult(@FieldMap Map<String, String> parmer);


    /**
     * 导致因素题目
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getLeadFactorsBean")
    Observable<HttpResult<LeadFactorsBean>> getLeadFactorsBean(@FieldMap Map<String, String> parmer);


    /**
     * 测试导致因素
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?checkLeadFactors")
    Observable<HttpResult<CheckLeadFactorsBean>> checkLeadFactors(@FieldMap Map<String, String> parmer);


    /**
     * 提交测试
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?checkFactors")
    Observable<HttpResult<String>> checkFactors(@FieldMap Map<String, String> parmer);


    /**
     * 诊断报告
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getReport")
    Observable<HttpResult<ReportBean>> getReport(@FieldMap Map<String, String> parmer);


    /**
     * 上传单张图片
     *
     * @return
     */
    @POST("settingInterfaces.api?uploadImg")
    Observable<HttpResult<String>> uploadImg(@Body RequestBody Body);


    /**
     * （添加档案）关系列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getRelations")
    Observable<HttpResult<List<RelationsBean>>> getRelations(@FieldMap Map<String, String> parmer);


    /**
     * 家政列表
     *
     * @return memberInterfaces.api?getHouseKeeps
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getHouseServiceList")
    Observable<HttpResult<List<HousekeepBean>>> getHouseKeeps(@FieldMap Map<String, String> parmer);

    /**
     * 家政详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getHouseServiceDeail")
    Observable<HttpResult<HousekeepBean>> getHouseKeepsDetail(@FieldMap Map<String, String> parmer);


    /**
     * 发布家政
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?releaseHouse")
    Observable<HttpResult<String>> releaseHouse(@FieldMap Map<String, String> parmer);


    /**
     * 家政类型
     *
     * @return
     */
    @POST("memberInterfaces.api?getServiceTypes")
    Observable<HttpResult<List<HouseKeepTypeBean>>> getServiceTypes();


    /**
     * 历史诊断报告列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getReportList")
    Observable<HttpResult<List<ReportHistoryBean>>> getReportList(@FieldMap Map<String, String> parmer);


    /**
     * 医师详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?getDoctorDetail")
    Observable<HttpResult<DoctorDetailBean>> getDoctorDetail(@FieldMap Map<String, String> parmer);


    /**
     * 获取咨询时间
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getConsultTimes")
    Observable<HttpResult<List<ConsultTimesBean>>> getConsultTimes(@FieldMap Map<String, String> parmer);

    /**
     * 时段设置页面
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?consultTimePage")
    Observable<HttpResult<List<ConsultWeek>>> consultTimePage(@FieldMap Map<String, String> parmer);


    /**
     * 简易调理方案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getSimplePlans")
    Observable<HttpResult<List<SimplePlansBean>>> getSimplePlans(@FieldMap Map<String, String> parmer);


    /**
     * 调理方案价格
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getPlanPrice")
    Observable<HttpResult<PlanPriceBean>> getPlanPrice(@FieldMap Map<String, String> parmer);

    /**
     * 购买调理方案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?buyPlan")
    Observable<HttpResult<String>> buyPlan(@FieldMap Map<String, String> parmer);

    /**
     * 购买调理方案
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?buyAssociator")
    Observable<HttpResult<String>> buyAssociator(@FieldMap Map<String, String> parmer);

    /**
     * 购买商品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?payRealOrders")
    Observable<HttpResult<String>> buyGood(@FieldMap Map<String, String> parmer);
    /**
     * 购买家政
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?buyHouseService")
    Observable<HttpResult<String>> buyHouseService(@FieldMap Map<String, String> parmer);


    /**
     * 调理方案详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?getPlanDetail")
    Observable<HttpResult<List<PlanDetailBean>>> getPlanDetail(@FieldMap Map<String, String> parmer);

    /**
     * 测试记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?testRecord")
    Observable<HttpResult<TestRecordBean>> testRecord(@FieldMap Map<String, String> parmer);

    /**
     * 重新测试
     *
     * @return
     */
    @FormUrlEncoded
    @POST("testInterfaces.api?checkQuestionAgain")
    Observable<HttpResult<String>> checkQuestionAgain(@FieldMap Map<String, String> parmer);

    /**
     * 送心意列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getHeartList")
    Observable<HttpResult<List<HeartBean>>> getHeartList(@FieldMap Map<String, String> parmer);

    /**
     * 送心意
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?sendHeart")
    Observable<HttpResult<String>> sendHeart(@FieldMap Map<String, String> parmer);


    /**
     * 医生评价列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?getAllDoctorAssesses")
    Observable<HttpResult<List<AllDoctorAssessesBean>>> getAllDoctorAssesses(@FieldMap Map<String, String> parmer);


    /**
     * 科室列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?getDepartments")
    Observable<HttpResult<List<DepartmentLevelOneBean>>> getDepartments(@FieldMap Map<String, String> parmer);

    /**
     * 科室列表
     *
     * @return
     */
    @POST("memberInterfaces.api?getDoctorTitle")
    Observable<HttpResult<List<DoctorTitleBean>>> getDoctorTitle();


    /**
     * 用户消息列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getMemberMsgs")
    Observable<HttpResult<List<MemberMsgsBean>>> getMemberMsgs(@FieldMap Map<String, String> parmer);


    /**
     * 医生筛选
     * doctorIntefaces.api?getSelectBeans
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?getSelectBeans")
    Observable<HttpResult<ShaixuanBean>> getSelectBeans(@FieldMap Map<String, String> parmer);

    /**
     * 购买电话(视频)咨询
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?buyConsult")
    Observable<HttpResult<String>> buyConsult(@FieldMap Map<String, String> parmer);

    /**
     * 购买图文咨询(生成订单)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?buyTextConsult")
    Observable<HttpResult<TextandImgBean>> buyTextConsult(@FieldMap Map<String, String> parmer);
    /**
     * 购买图文咨询(直接支付)
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?payOrders")
    Observable<HttpResult<String>> paybuyTextConsult(@FieldMap Map<String, String> parmer);

    /**
     * 购买健康管理
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?buyService")
    Observable<HttpResult<String>> buyService(@FieldMap Map<String, String> parmer);


    /**
     * 设置咨询服务价格
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?setConsultPrice")
    Observable<HttpResult<String>> setConsultPrice(@FieldMap Map<String, String> parmer);

    /**
     * 开启或者关闭预约时间段
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?setOpenTime")
    Observable<HttpResult<String>> setOpenTime(@FieldMap Map<String, String> parmer);

    /**
     * 设置咨询时间
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?setConsultTime")
    Observable<HttpResult<String>> setConsultTime(@FieldMap Map<String, String> parmer);

    /**
     * 健康管理设置
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getHealthManager")
    Observable<HttpResult<List<HealthManagerBeans>>> getHealthManager(@FieldMap Map<String, String> parmer);


    /**
     * 修改健康管理
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateHealthManager")
    Observable<HttpResult<String>> updateHealthManager(@FieldMap Map<String, String> parmer);


    /**
     * 修改用户详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateMemberDetail")
    Observable<HttpResult<String>> updateMemberDetail(@FieldMap Map<String, String> parmer);


    /**
     * 修改医生个人信息
     * <p>
     * ll
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?updateDoctor")
    Observable<HttpResult<String>> updateDoctorInfo(@FieldMap Map<String, String> parmer);

    /**
     * 健康用户列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?recordsByDoctor")
    Observable<HttpResult<List<HealthRecordDetailBean>>> recordsByDoctor(@FieldMap Map<String, String> parmer);

    /**
     * 医生端 咨询列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getDoctorConsultList")
    Observable<HttpResult<List<ConsultBean>>> getDoctorConsultList(@FieldMap Map<String, String> parmer);

    /**
     * 我的咨询列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getConsultList")
    Observable<HttpResult<List<ConsultBean>>> getMyConsultList(@FieldMap Map<String, String> parmer);

    /***
     *
     *服务类型列表
     *
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getServiceTypes")
    Observable<HttpResult<List<TypeServiceBean>>> gettypeserviceList(@FieldMap Map<String, String> parmer);


    /**
     * 充值
     * orderInterfaces.api?wx
     * orderInterfaces.api?recharge
     * orderInterfaces.api?alipay
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?recharge")
    Observable<HttpResult<String>> getRecharge(@FieldMap Map<String, String> parmer);

    /**
     * 充值
     * orderInterfaces.api?wx
     * orderInterfaces.api?wx
     * orderInterfaces.api?recharge
     * orderInterfaces.api?alipay
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?wx")
    Observable<HttpResult<WxPayMode>> getRechargeWX(@FieldMap Map<String, String> parmer);


    /**
     *获取未来七天日期
     *
     *
     * @return
     */

    /**
     * 我的医生
     *
     * @return
     */
    @FormUrlEncoded
    @POST("doctorIntefaces.api?myDoctorBeans")
    Observable<HttpResult<List<DoctorDetailBean>>> myDoctorBeans(@FieldMap Map<String, String> parmer);

    /**
     * 健康阶段
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?getHealthStage")
    Observable<HttpResult<DoctorDetailBean>> getHealthStage(@FieldMap Map<String, String> parmer);

    /**
     * 打卡
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?playCard")
    Observable<HttpResult<String>> playCard(@FieldMap Map<String, String> parmer);

    /**
     * 健康阶段方案详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?getHealthPlanBean")
    Observable<HttpResult<HealthPlanBean>> getHealthPlanBean(@FieldMap Map<String, String> parmer);


    /**
     * 获取未来七天日期
     *
     * @return
     */
    @POST("memberInterfaces.api?getFetureDay")
    Observable<HttpResult<List<FetureDateBeans>>> getFetureDay();

    /**
     * 获取默认地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?getDefaultAddress")
    Observable<HttpResult<AddressBean>> DefaultAddress(@FieldMap Map<String, String> params);

    /**
     * 收货地址列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?getMemberAddress")
    Observable<HttpResult<List<AddressBean>>> AddressList(@FieldMap Map<String, String> params);


    /**
     * 修改收货地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?updateAddress")
    Observable<HttpResult<String>> UpdateAddress(@FieldMap Map<String, String> params);


    /**
     * 删除收货地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?deleteAddress")
    Observable<HttpResult<String>> DelAddress(@FieldMap Map<String, String> params);


    /**
     * 设置默认收货地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?setDefaultAddress")
    Observable<HttpResult<String>> SetDefaultAddress(@FieldMap Map<String, String> params);


    /**
     * 添加收货地址
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addressInterfaces.api?insertAddress")
    Observable<HttpResult<String>> AddAddress(@FieldMap Map<String, String> params);

    /**
     * 语音和视频准备拨打页面
     *
     * @return
     */
    @FormUrlEncoded
    @POST("")
    Observable<HttpResult<String>> TestData(@FieldMap Map<String, String> params);


    /**
     * 添加订单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?insertOrder")
    Observable<HttpResult<PriceBean>> InsertOrder(@FieldMap Map<String, String> params);


    /**
     * 添加订单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?insertServeOrder")
    Observable<HttpResult<PriceBean>> insertServeOrder(@FieldMap Map<String, String> params);


    /**
     * 计算价格
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getOrderPrice")
    Observable<HttpResult<PriceBean>> getOrderPrice(@FieldMap Map<String, String> params);


    /**
     * 签到
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?sign")
    Observable<HttpResult<String>> sign(@FieldMap Map<String, String> params);


    /**
     * 会员列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getAssociators")
    Observable<HttpResult<List<AssociatorBean>>> getAssociators(@FieldMap Map<String, String> params);


    /**
     * 撤销 商家  医生 家政  申请
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?cancelApply")
    Observable<HttpResult<String>> cancelApplyMearchants(@FieldMap Map<String, String> params);

    /**
     * 商城分类
     *
     * @return
     */
    @FormUrlEncoded
    @POST("goodsInterfaces.api?getGoodsClass")
    Observable<HttpResult<List<ClassBean>>> getGoodsClass(@FieldMap Map<String, String> params);


    /**
     * 商品列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("goodsInterfaces.api?goodsList")
    Observable<HttpResult<List<GoodsBean>>> getGoodsList(@FieldMap Map<String, String> params);

    /**
     * 商品详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("goodsInterfaces.api?getGoodsDetail")
    Observable<HttpResult<GoodsBean>> getGoodsDetail(@FieldMap Map<String, String> params);

    /**
     * 商品订单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getOrderDetail")
    Observable<HttpResult<OrderBean>> getGoodsOrderDetail(@FieldMap Map<String, String> params);


    /**
     * 添加购物车
     *
     * @return
     */
    @FormUrlEncoded
    @POST("shopCarInterfaces.api?insertShopCar")
    Observable<HttpResult<String>> insertShopCar(@FieldMap Map<String, String> params);

    /**
     * 删除购物车商品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("shopCarInterfaces.api?deleteShopCar")
    Observable<HttpResult<String>> deleteShopCar(@FieldMap Map<String, String> params);


    /**
     * 购物车列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("shopCarInterfaces.api?getShopCars")
    Observable<HttpResult<List<CartBean>>> getShopCars(@FieldMap Map<String, String> params);

    /**
     * 修改购物车商品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("shopCarInterfaces.api?updateShopCarsDetail")
    Observable<HttpResult<String>> updateShopCarDetail(@FieldMap Map<String, String> params);


    /**
     * 商品----商品规格详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("goodsInterfaces.api?getGoodsSpecificationDetail")
    Observable<HttpResult<GoodsSpecificationBeans>> getGoodsSpecificationDetail(@FieldMap Map<String, String> params);


    /**
     * 商城分类二级分类
     *
     * @return
     */

    @POST("goodsInterfaces.api?getSecondGoodsClass")
    Observable<HttpResult<List<ClassBean>>> getSecondGoodsClass();


    /**
     * 热销商品
     *
     * @return
     */

    @POST("goodsInterfaces.api?hotGoodsList")
    Observable<HttpResult<List<GoodsBean>>> hotGoodsList();

    /**
     * 推荐商品
     *
     * @return
     */

    @POST("goodsInterfaces.api?recommendGoods")
    Observable<HttpResult<List<GoodsBean>>> recommendGoods();

    /**
     * 推荐商品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("goodsInterfaces.api?recommendGoods")
    Observable<HttpResult<List<GoodsBean>>> NewrecommendGoods(@FieldMap Map<String, String> params);


    /**
     * 商品订单列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getOrders")
    Observable<HttpResult<List<OrderBean>>> getGoodsOrderList(@FieldMap Map<String, String> params);


    /**
     * 取消订单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?cancelOrder")
    Observable<HttpResult<String>> CancelOrder(@FieldMap Map<String, String> params);

    /**
     * 删除订单
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?deleteOrder")
    Observable<HttpResult<String>> DelOrder(@FieldMap Map<String, String> params);


    /**
     * 申请提现
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?applyWithdrawals")
    Observable<HttpResult<String>> applyWithdrawals(@FieldMap Map<String, String> params);

    /**
     * 申请收益提现
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?applyProfitWithdrawals")
    Observable<HttpResult<String>> applyProfitWithdrawals(@FieldMap Map<String, String> params);


    /**
     * 我的收益列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?myProfit")
    Observable<HttpResult<List<ProfitBean>>> myProfit(@FieldMap Map<String, String> params);


    /**
     * 余额明细
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?balanceDetail")
    Observable<HttpResult<List<BalanceBean>>> balanceDetail(@FieldMap Map<String, String> params);


    /**
     * 我的收益列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?aboutUs")
    Observable<HttpResult<HtmlBean>> aboutUs(@FieldMap Map<String, String> params);


    /**
     * 实名认证
     *
     * @return
     */
    @FormUrlEncoded
    @POST("healthRecordInterfaces.api?certificationRecord")
    Observable<HttpResult<String>> certificationRecord(@FieldMap Map<String, String> params);


    /**
     * 评价列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("goodsInterfaces.api?assessmentGoods")
    Observable<HttpResult<List<AssessmentBean>>> assessmentGoods(@FieldMap Map<String, String> params);

    /**
     * 退款原因
     *
     * @return
     */
    @POST("orderInterfaces.api?getRefundsReasons")
    Observable<HttpResult<List<RefundsReasons>>> getRefundsReasons();


    /**
     * 申请退款
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?refundOrder")
    Observable<HttpResult<String>> refundOrder(@FieldMap Map<String, String> params);

    /**
     * 售后列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getRefundOrders")
    Observable<HttpResult<List<AfterSaleOrderBean>>> getRefundOrders(@FieldMap Map<String, String> params);

    /**
     * 售后订单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?getRefundOrderDetail")
    Observable<HttpResult<AfterSaleOrderBean>> getRefundOrderDetail(@FieldMap Map<String, String> params);

    /**
     * 售后订单详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?cancelRefundOrder")
    Observable<HttpResult<String>> cancelRefundOrder(@FieldMap Map<String, String> params);

    /**
     * 关注的人
     *
     * @return
     */
    @FormUrlEncoded
    @POST("")
    Observable<HttpResult<List<DoctorDetailBean>>> GetFocusList(@FieldMap Map<String, String> params);

    /**
     * 关注我的人
     *
     * @return
     */
    @FormUrlEncoded
    @POST("collectionInterfaces.api?getFansList")
    Observable<HttpResult<List<UserBean>>> GetFansList(@FieldMap Map<String, String> params);

    /**
     * 收藏
     *
     * @return
     */
    @FormUrlEncoded
    @POST("collectionInterfaces.api?getCollections")
    Observable<HttpResult<List<CollectionBean>>> GetCollectionList(@FieldMap Map<String, String> params);


    /**
     * 添加收藏
     *
     * @return
     */
    @FormUrlEncoded
    @POST("collectionInterfaces.api?insertCollection")
    Observable<HttpResult<String>> insertCollection(@FieldMap Map<String, String> params);

    /**
     * 商家详情
     *
     * @return
     */
    @FormUrlEncoded
    @POST("merchantsInterfaces.api?getMerchantsDetail")
    Observable<HttpResult<MerchantsBean>> getMerchantsDetail(@FieldMap Map<String, String> params);

    /**
     * 取消收藏
     *
     * @return
     */
    @FormUrlEncoded
    @POST("collectionInterfaces.api?cancelCollection")
    Observable<HttpResult<String>> cancelCollection(@FieldMap Map<String, String> params);


    /**
     * 开始直播
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?openQiNiuLive")
    Observable<HttpResult<LiveBean>> OpenLive(@FieldMap Map<String, String> params);

    /**
     * 关闭直播
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?closeQiNiuLive")
    Observable<HttpResult<String>> closeQiNiuLive(@FieldMap Map<String, String> params);

    /**
     * 判断直播是否结束
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?getLiveState")
    Observable<HttpResult<String>> IsLiveEnd(@FieldMap Map<String, String> params);


    /**
     * 用户退出直播间
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?outTheLiveRoom")
    Observable<HttpResult<String>> outTheLiveRoom(@FieldMap Map<String, String> params);


    /**
     * 用户进入直播间
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?inTheLiveRoom")
    Observable<HttpResult<String>> inTheLiveRoom(@FieldMap Map<String, String> params);


    /**
     * 直播列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?getLiveList")
    Observable<HttpResult<List<LiveBean>>> getLiveList(@FieldMap Map<String, String> params);


    /**
     * 直播信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?getLiveInfo")
    Observable<HttpResult<LiveBean>> getLiveInfo(@FieldMap Map<String, String> params);

    /**
     * 礼物列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?getLiveGift")
    Observable<HttpResult<List<GiftBean>>> getLiveGift(@FieldMap Map<String, String> params);

    /**
     * 送礼物验证支付密码
     *
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getAvoidPassword")
    Observable<HttpResult<MemberAvoidBean>> getAvoidPassword(@FieldMap Map<String, String> params);

    /**
     * 送礼物
     *
     * @return
     */
    @FormUrlEncoded
    @POST("liveInterfaces.api?sendGift")
    Observable<HttpResult<String>> sendGift(@FieldMap Map<String, String> params);

    /**
     * 打赏
     *
     * @return
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?reward")
    Observable<HttpResult<String>> getReward(@FieldMap Map<String, String> params);

    /**
     * 搜索医生
     *
     * @return
     */
    @FormUrlEncoded
    @POST("homeInterfaces.api?homeTypeSearch")
    Observable<HttpResult<List<DoctorDetailBean>>> getSearchDoctor(@FieldMap Map<String, String> params);

    /**
     * 搜索门店
     *
     * @return
     */
    @FormUrlEncoded
    @POST("homeInterfaces.api?homeTypeSearch")
    Observable<HttpResult<List<MerchantsBean>>> getSearchMerchants(@FieldMap Map<String, String> params);

    /**
     * 搜索商品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("homeInterfaces.api?homeTypeSearch")
    Observable<HttpResult<List<GoodsBean>>> getSearchGood(@FieldMap Map<String, String> params);


    /**
     * 搜索总页面
     *
     * @return
     */
    @FormUrlEncoded
    @POST("homeInterfaces.api?homeSearch")
    Observable<HttpResult<SearchBean>> gethomeSearch(@FieldMap Map<String, String> params);


    /***
     * 交流社区热门模块（banner）
     */

    @FormUrlEncoded
    @POST("plateInterfaces.api?getHotPlate")
    Observable<HttpResult<List<CommunityBannerBean>>> getCommunityBanner(@FieldMap Map<String, String> params);

    /**
     * 交流社区全部模块
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?getCommunityPlate")
    Observable<HttpResult<List<CommAllBean>>> getAllData(@FieldMap Map<String, String> params);

    /***
     * 首页养生知识
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?getHomePlate")
    Observable<HttpResult<HomeHealthDetail>> getHomeHealthDetail(@FieldMap Map<String, String> params);

    /**
     * 首页养生更多
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?getPosts")
    Observable<HttpResult<List<PlateListBean>>> getHomeHealthMore(@FieldMap Map<String, String> params);

    /*
    热门商家
    */
    @FormUrlEncoded
    @POST("merchantsInterfaces.api?getHotMerchants")
    Observable<HttpResult<List<MerchantHotBean>>> getMerchanthotdata(@FieldMap Map<String, String> params);

    /***
     * 首页养生知识详情
     */

    @FormUrlEncoded
    @POST("plateInterfaces.api?getPostDetail")
    Observable<HttpResult<PlateListBean>> getPlateDetail(@FieldMap Map<String, String> params);

    /***
     * 第三方登录
     *
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?thridLoginMember")
    Observable<HttpResult<UserBean>> getThridLogin(@FieldMap Map<String, String> params);


    /***
     * 第三方登录(绑定手机号)
     *
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?thridRegisteMember")
    Observable<HttpResult<UserBean>> getBundleNumber(@FieldMap Map<String, String> params);

    /***
     * 医生发贴
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?publishPost")
    Observable<HttpResult<String>> getpost(@FieldMap Map<String, String> parmer);

    /***
     * 帖子详请
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?getPostDetail")
    Observable<HttpResult<PlateListBean>> getpostDetails(@FieldMap Map<String, String> parmer);

    /***
     * 帖子详请
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?praisePost")
    Observable<HttpResult<String>> getpraisePost(@FieldMap Map<String, String> parmer);/***
     * 帖子评论
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?commentPost")
    Observable<HttpResult<String>> getpcommentPost(@FieldMap Map<String, String> parmer);


    /**
     * 编辑服务
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?insertServiceRange")
    Observable<HttpResult<String>> geteditService(@FieldMap Map<String, String> parmer);
    /**
     * 删除家政地址
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?deleteHouseAddress")
    Observable<HttpResult<String>> getdeleteHouseAddress(@FieldMap Map<String, String> parmer);


    /**
     * 家政评论列表
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getAssessmentHouseService")
    Observable<HttpResult<List<ServiceCommentBean>>> getAssessmentList(@FieldMap Map<String, String> parmer);
    /**
     * 修改商家信息
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateHouseServiceDetail")
    Observable<HttpResult<String>> getChangemessage(@FieldMap Map<String, String> parmer); /**
     * 投诉
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?insertComplains")
    Observable<HttpResult<String>> getinsertComplains(@FieldMap Map<String, String> parmer);

    /**
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?assessmentHouseService")
    Observable<HttpResult<String>> getpingLun(@FieldMap Map<String, String> parmer);

    /**
     * @param parmer
     * @return
     * 个人中心我的帖子
     */
    @FormUrlEncoded
    @POST("plateInterfaces.api?getMyPosts")
    Observable<HttpResult<List<PlateListBean>>> getPostList(@FieldMap Map<String, String> parmer);/**

    /**
     * @param parmer
     * @return
     * 健康管理服务列表
     *
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getHealthManager")
    Observable<HttpResult<List<HealthManagerBeans>>> getservicetypeList(@FieldMap Map<String, String> parmer);/**

     * @param parmer
     * @return
     * 修改健康管理服务
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateHealthManager")
    Observable<HttpResult<String>>getupdata(@FieldMap Map<String, String> parmer);

    /**
     *
     * @param parmer
     * @return
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?insertHealthManager")
    Observable<HttpResult<String>>getaddhealth(@FieldMap Map<String, String> parmer);
    /**
     *
     * @param parmer
     * @return
     * 确认收货
     */
    @FormUrlEncoded
    @POST("orderInterfaces.api?receiveOrder")
    Observable<HttpResult<String>>getreceiveOrder(@FieldMap Map<String, String> parmer);
    /**
     *
     * 请求选择
     */
    @GET("settingInterfaces.api?getSystemHtmls")
    Observable<HttpResult<List<SystemHtmlBean>>>gethtmllist();
    /**
     *
     * 首页推送消息
     */
    @FormUrlEncoded
    @POST("settingInterfaces.api?getSMessages")
    Observable<HttpResult<List<HomeMessageBean>>>getmessagelist(@FieldMap Map<String, String> parmer);/**
     *
     * 修改家政服务
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?updateServiceRange")
    Observable<HttpResult<String>>getupdateServiceRange(@FieldMap Map<String, String> parmer);
    /**
     *
     * you推送绑定
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?getDevice_token")
    Observable<HttpResult<String>>getDeviceTokens(@FieldMap Map<String, String> parmer);
    /**
     *
     * you推送注销
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?logoutDeviceToken")

    Observable<HttpResult<String>>logoutDeviceTokens(@FieldMap Map<String, String> parmer);
    /**
     *
     *环信推送
     */
    @FormUrlEncoded
    @POST("memberInterfaces.api?pushConsultMessage")
    Observable<HttpResult<String>>DeviceTokensHuanXin(@FieldMap Map<String, String> parmer);
}
