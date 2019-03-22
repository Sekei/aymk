package com.live.tv.bean;

import java.util.List;

/**
 * @author Created by stone
 * @since 2018/1/26
 */

public class TestRecordBean {


    /**
     * test_id : 36
     * member_id : 29
     * health_record_id : 7
     * yinxu_num : 9
     * yangxu_num : 8
     * qixu_num : 12
     * tanshi_num : 7
     * shire_num : 7
     * qiyu_num : 5
     * xueyu_num : 5
     * tebing_num : 6
     * pinghe_num : 5
     * done_ques_num : 64
     * sum_ques_num : 64
     * question_ids : 10,55,64,20,5,19,32,46,35,17,6,1,41,58,23,51,27,7,54,3,18,33,57,4,36,28,63,48,49,9,43,59,52,45,11,30,61,40,42,50,47,37,2,8,14,26,60,44,12,31,38,56,16,24,53,25,29,34,39,22,62,13,21,15
     * test_result : 阴虚,阳虚,气虚,痰湿,湿热,气郁,血瘀,特禀,平和
     * done_ques_time : 2018-01-26 17:43:30
     * done_factors_time : 2018-01-26 17:43:46
     * is_done_ques : 1
     * is_done_factors : 1
     * is_buy : 0
     * is_sure_ques : 1
     * lead_factors_ids : 36,29,34,26,3,30,40,11,51,50,42,45,6,12,31,2,1,4,37,16,49,27,14,44,43,32,19,9,15,28,28,7,46,46,23,24,24,35,13,48,48,10,25,41,5,22,20,18,8,38,39
     * sum_factors_num : 51
     * done_factors_num : 51
     * factors_ids_checked : 36,36,29,34,26,3,30,40,11,51,50,42,45,6,12,31,2,1,4,37,16,49,27,14,44,43,32,19,9,15,28,28,7,46,46,23,24,24,35,13,48,48,10,25,41,5,22,20,18,8,38,39
     * real_name :
     * plan_title :
     * is_sure_factors : 1
     * create_time : 2018-01-25 10:28:08
     * update_time : 2018-01-26 17:43:46
     * is_delete : 0
     * leadFactorsBeans : [{"lead_factors_id":1,"lead_factors_title":"是否有高血压，常吃利尿药物？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":2,"lead_factors_title":"是否经常工作繁忙很疲劳？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":3,"lead_factors_title":"是否经常发热或高强度运动导致大量出汗？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":4,"lead_factors_title":"是否常吃辛辣、煎烤、易发热等食物？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":5,"lead_factors_title":"是否长抽烟、喝酒？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":6,"lead_factors_title":"是否经常11点以后才睡？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":7,"lead_factors_title":"是否处在更年期？（女性）","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":8,"lead_factors_title":"是否经常频繁行房事（男性）？","physical_type":"阴虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阴虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":9,"lead_factors_title":"是否经常服用清热解毒这些药物或静脉使用寒凉药物？","physical_type":"阳虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阳虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":10,"lead_factors_title":"是否常喝凉茶或常吃冷饮？","physical_type":"阳虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阳虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":11,"lead_factors_title":"是否长期接触冷水、冰的物品？","physical_type":"阳虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阳虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":12,"lead_factors_title":"是否长期在空调温度过低的房间滞留？","physical_type":"阳虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阳虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":13,"lead_factors_title":"是否生活环境偏于阴冷潮湿？","physical_type":"阳虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阳虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":14,"lead_factors_title":"是否经常穿衣过少，损耗阳气？","physical_type":"阳虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"阳虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":15,"lead_factors_title":"是否有手术史或者慢性消耗性疾病？","physical_type":"气虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"气虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":16,"lead_factors_title":"是否长期过度用脑？","physical_type":"气虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"气虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":18,"lead_factors_title":"是否过度节食？","physical_type":"气虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"气虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":19,"lead_factors_title":"是否长期缺乏运动？","physical_type":"气虚","create_time":"2018-01-18 14:23:50","update_time":"","type":"气虚","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":20,"lead_factors_title":"是否久居潮湿的地方？","physical_type":"痰湿","create_time":"2018-01-18 14:23:50","update_time":"","type":"痰湿","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":22,"lead_factors_title":"是否口味比较重，喝水很多？","physical_type":"痰湿","create_time":"2018-01-18 14:23:50","update_time":"","type":"痰湿","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":23,"lead_factors_title":"是否过多得吃冷饮？","physical_type":"痰湿","create_time":"2018-01-18 14:23:50","update_time":"","type":"痰湿","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":24,"lead_factors_title":"是否经常熬夜或11点后睡？","physical_type":"痰湿","create_time":"2018-01-18 14:23:50","update_time":"","type":"痰湿","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":25,"lead_factors_title":"是否缺乏运动？","physical_type":"痰湿","create_time":"2018-01-18 14:23:50","update_time":"","type":"痰湿","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":26,"lead_factors_title":"是否长期抽烟、喝酒？","physical_type":"湿热","create_time":"2018-01-18 14:23:50","update_time":"","type":"湿热","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":27,"lead_factors_title":"是否长期熬夜或晚于11点睡？","physical_type":"湿热","create_time":"2018-01-18 14:23:50","update_time":"","type":"湿热","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":28,"lead_factors_title":"是否过多服用滋补品？","physical_type":"湿热","create_time":"2018-01-18 14:23:50","update_time":"","type":"湿热","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":29,"lead_factors_title":"是否长期长期情绪压抑？","physical_type":"湿热","create_time":"2018-01-18 14:23:50","update_time":"","type":"湿热","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":30,"lead_factors_title":"是否携带肝炎病毒？","physical_type":"湿热","create_time":"2018-01-18 14:23:50","update_time":"","type":"湿热","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":31,"lead_factors_title":"是否长期生活在湿热环境下，比如沿海地区？","physical_type":"湿热","create_time":"2018-01-18 14:23:50","update_time":"","type":"湿热","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":32,"lead_factors_title":"是否长期工作压力大？","physical_type":"气郁","create_time":"2018-01-18 14:23:50","update_time":"","type":"气郁","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":34,"lead_factors_title":"是否过度要求完美，不仅对自己，而且对别人？","physical_type":"气郁","create_time":"2018-01-18 14:23:50","update_time":"","type":"气郁","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":35,"lead_factors_title":"是否长期闷闷不乐、忧郁寡欢？","physical_type":"气郁","create_time":"2018-01-18 14:23:50","update_time":"","type":"气郁","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":36,"lead_factors_title":"是否抑郁、压抑，长期不能舒展，性格敏感？","physical_type":"血瘀","create_time":"2018-01-18 14:23:50","update_time":"","type":"血瘀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":37,"lead_factors_title":"是否曾经有过严重的外伤？","physical_type":"血瘀","create_time":"2018-01-18 14:23:50","update_time":"","type":"血瘀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":38,"lead_factors_title":"是否有慢性病，需长期服药？","physical_type":"血瘀","create_time":"2018-01-18 14:23:50","update_time":"","type":"血瘀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":39,"lead_factors_title":"是否长期在寒冷的环境中生活工作？","physical_type":"血瘀","create_time":"2018-01-18 14:23:50","update_time":"","type":"血瘀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":40,"lead_factors_title":"是否经常吃寒凉的食物？","physical_type":"血瘀","create_time":"2018-01-18 14:23:50","update_time":"","type":"血瘀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":41,"lead_factors_title":"是否是遗传？","physical_type":"特禀","create_time":"2018-01-18 14:23:50","update_time":"","type":"特禀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":42,"lead_factors_title":"是否是胎传？","physical_type":"特禀","create_time":"2018-01-18 14:23:50","update_time":"","type":"特禀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":43,"lead_factors_title":"是否是过敏体质？","physical_type":"特禀","create_time":"2018-01-18 14:23:50","update_time":"","type":"特禀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":44,"lead_factors_title":"是否长期劳累导致免疫力下降？","physical_type":"特禀","create_time":"2018-01-18 14:23:50","update_time":"","type":"特禀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":45,"lead_factors_title":"是否长期生活在空气状况不佳的环境中？","physical_type":"特禀","create_time":"2018-01-18 14:23:50","update_time":"","type":"特禀","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":46,"lead_factors_title":"是否饮食有节制，不要常吃过冷过热、辛辣油腻、不干净的食物，粗细粮食要合理搭配？","physical_type":"平和","create_time":"2018-01-18 14:23:50","update_time":"","type":"平和","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":48,"lead_factors_title":"是否平时注意劳逸结合，坚持锻炼？","physical_type":"平和","create_time":"2018-01-18 14:23:50","update_time":"","type":"平和","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":49,"lead_factors_title":"是否心态平和，规律起居，睡眠充足？","physical_type":"平和","create_time":"2018-01-18 14:23:50","update_time":"","type":"平和","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":50,"lead_factors_title":"是否开朗乐观，积极进取？","physical_type":"平和","create_time":"2018-01-18 14:23:50","update_time":"","type":"平和","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0},{"lead_factors_id":51,"lead_factors_title":"是否不吸烟、过量饮酒？","physical_type":"平和","create_time":"2018-01-18 14:23:50","update_time":"","type":"平和","is_delete":"0","is_checked":"","count":0,"leadFactors_number":0,"sum_num":0}]
     * dreportBeans : [{"report_id":1,"report_desc":"阴虚是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"report_id":2,"report_desc":"阳虚是否经常工作繁忙很疲劳？是否经常工作繁忙很疲劳？是否经常工作繁忙很疲劳？是否经常工作繁忙很疲劳？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"report_id":3,"report_desc":"是否经常发热或高强度运动导致大量出汗？是否经常发热或高强度运动导致大量出汗？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"气虚","type":"气虚","is_delete":"0"},{"report_id":4,"report_desc":"是否常吃辛辣、煎烤、易发热等食物？是否常吃辛辣、煎烤、易发热等食物？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"report_id":5,"report_desc":"是否长抽烟、喝酒？是否长抽烟、喝酒？是否长抽烟、喝酒？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"report_id":6,"report_desc":"是否经常11点以后才睡？是否经常11点以后才睡？是否经常11点以后才睡？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"气郁","type":"气郁","is_delete":"0"},{"report_id":7,"report_desc":"是否处在更年期？（女性）是否处在更年期？（女性）是否处在更年期？（女性）","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"血瘀","type":"血瘀","is_delete":"0"},{"report_id":8,"report_desc":"是否经常频繁行房事（男性）？是否经常频繁行房事（男性）？是否经常频繁行房事（男性）？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"特禀","type":"特禀","is_delete":"0"},{"report_id":9,"report_desc":"是否经常服用清热解毒这些药物或静脉使用寒凉药物？是否经常服用清热解毒这些药物或静脉使用寒凉药物？","create_time":"2018-01-18 14:42:37","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"}]
     * leadResultBeans : [{"result_id":1,"result_title":"是否有高血压，常吃利尿药物？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":2,"result_title":"是否经常工作繁忙很疲劳？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":3,"result_title":"是否经常发热或高强度运动导致大量出汗？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":4,"result_title":"是否常吃辛辣、煎烤、易发热等食物？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":5,"result_title":"是否长抽烟、喝酒？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":6,"result_title":"是否经常11点以后才睡？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":7,"result_title":"是否处在更年期？（女性）","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":8,"result_title":"是否经常频繁行房事（男性）？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阴虚","type":"阴虚","is_delete":"0"},{"result_id":9,"result_title":"是否经常服用清热解毒这些药物或静脉使用寒凉药物？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"result_id":10,"result_title":"是否常喝凉茶或常吃冷饮？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"result_id":11,"result_title":"是否长期接触冷水、冰的物品？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"result_id":12,"result_title":"是否长期在空调温度过低的房间滞留？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"result_id":13,"result_title":"是否生活环境偏于阴冷潮湿？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"result_id":14,"result_title":"是否经常穿衣过少，损耗阳气？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"阳虚","type":"阳虚","is_delete":"0"},{"result_id":15,"result_title":"是否有手术史或者慢性消耗性疾病？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气虚","type":"气虚","is_delete":"0"},{"result_id":16,"result_title":"是否长期过度用脑？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气虚","type":"气虚","is_delete":"0"},{"result_id":17,"result_title":"是否过度劳累？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气虚","type":"气虚","is_delete":"0"},{"result_id":18,"result_title":"是否过度节食？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气虚","type":"气虚","is_delete":"0"},{"result_id":19,"result_title":"是否长期缺乏运动？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气虚","type":"气虚","is_delete":"0"},{"result_id":20,"result_title":"是否久居潮湿的地方？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"result_id":21,"result_title":"是否常暴饮暴食、过食肥甘醇酒厚味？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"result_id":22,"result_title":"是否口味比较重，喝水很多？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"result_id":23,"result_title":"是否过多得吃冷饮？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"result_id":24,"result_title":"是否经常熬夜或11点后睡？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"result_id":25,"result_title":"是否缺乏运动？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"痰湿","type":"痰湿","is_delete":"0"},{"result_id":26,"result_title":"是否长期抽烟、喝酒？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"result_id":27,"result_title":"是否长期熬夜或晚于11点睡？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"result_id":28,"result_title":"是否过多服用滋补品？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"result_id":29,"result_title":"是否长期长期情绪压抑？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"result_id":30,"result_title":"是否携带肝炎病毒？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"result_id":31,"result_title":"是否长期生活在湿热环境下，比如沿海地区？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"湿热","type":"湿热","is_delete":"0"},{"result_id":32,"result_title":"是否长期工作压力大？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气郁","type":"气郁","is_delete":"0"},{"result_id":33,"result_title":"是否经常失眠？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气郁","type":"气郁","is_delete":"0"},{"result_id":34,"result_title":"是否过度要求完美，不仅对自己，而且对别人？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气郁","type":"气郁","is_delete":"0"},{"result_id":35,"result_title":"是否长期闷闷不乐、忧郁寡欢？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"气郁","type":"气郁","is_delete":"0"},{"result_id":36,"result_title":"是否抑郁、压抑，长期不能舒展，性格敏感？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"血瘀","type":"血瘀","is_delete":"0"},{"result_id":37,"result_title":"是否曾经有过严重的外伤？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"血瘀","type":"血瘀","is_delete":"0"},{"result_id":38,"result_title":"是否有慢性病，需长期服药？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"血瘀","type":"血瘀","is_delete":"0"},{"result_id":39,"result_title":"是否长期在寒冷的环境中生活工作？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"血瘀","type":"血瘀","is_delete":"0"},{"result_id":40,"result_title":"是否经常吃寒凉的食物？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"血瘀","type":"血瘀","is_delete":"0"},{"result_id":41,"result_title":"是否是遗传？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"特禀","type":"特禀","is_delete":"0"},{"result_id":42,"result_title":"是否是胎传？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"特禀","type":"特禀","is_delete":"0"},{"result_id":43,"result_title":"是否是过敏体质？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"特禀","type":"特禀","is_delete":"0"},{"result_id":44,"result_title":"是否长期劳累导致免疫力下降？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"特禀","type":"特禀","is_delete":"0"},{"result_id":45,"result_title":"是否长期生活在空气状况不佳的环境中？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"特禀","type":"特禀","is_delete":"0"},{"result_id":46,"result_title":"是否饮食有节制，不要常吃过冷过热、辛辣油腻、不干净的食物，粗细粮食要合理搭配？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"},{"result_id":47,"result_title":"是否多吃蔬菜、水果？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"},{"result_id":48,"result_title":"是否平时注意劳逸结合，坚持锻炼？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"},{"result_id":49,"result_title":"是否心态平和，规律起居，睡眠充足？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"},{"result_id":50,"result_title":"是否开朗乐观，积极进取？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"},{"result_id":51,"result_title":"是否不吸烟、过量饮酒？","create_time":"2018-01-18 16:40:14","update_time":"","physical_type":"平和","type":"平和","is_delete":"0"}]
     * planBeans : []
     */

    private int test_id;
    private int member_id;
    private int health_record_id;
    private int yinxu_num;
    private int yangxu_num;
    private int qixu_num;
    private int tanshi_num;
    private int shire_num;
    private int qiyu_num;
    private int xueyu_num;
    private int tebing_num;
    private int pinghe_num;
    private int done_ques_num;
    private int sum_ques_num;
    private String question_ids;
    private String test_result;
    private String done_ques_time;
    private String done_factors_time;
    private String is_done_ques;
    private String is_done_factors;
    private String is_buy;
    private String is_sure_ques;
    private String lead_factors_ids;
    private int sum_factors_num;
    private int done_factors_num;
    private String factors_ids_checked;
    private String real_name;
    private String plan_title;
    private String is_sure_factors;
    private String create_time;
    private String update_time;
    private String is_delete;
    private List<LeadFactorsBeansBean> leadFactorsBeans;
    private List<DreportBeansBean> dreportBeans;
    private List<LeadResultBeansBean> leadResultBeans;
    private List<?> planBeans;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getHealth_record_id() {
        return health_record_id;
    }

    public void setHealth_record_id(int health_record_id) {
        this.health_record_id = health_record_id;
    }

    public int getYinxu_num() {
        return yinxu_num;
    }

    public void setYinxu_num(int yinxu_num) {
        this.yinxu_num = yinxu_num;
    }

    public int getYangxu_num() {
        return yangxu_num;
    }

    public void setYangxu_num(int yangxu_num) {
        this.yangxu_num = yangxu_num;
    }

    public int getQixu_num() {
        return qixu_num;
    }

    public void setQixu_num(int qixu_num) {
        this.qixu_num = qixu_num;
    }

    public int getTanshi_num() {
        return tanshi_num;
    }

    public void setTanshi_num(int tanshi_num) {
        this.tanshi_num = tanshi_num;
    }

    public int getShire_num() {
        return shire_num;
    }

    public void setShire_num(int shire_num) {
        this.shire_num = shire_num;
    }

    public int getQiyu_num() {
        return qiyu_num;
    }

    public void setQiyu_num(int qiyu_num) {
        this.qiyu_num = qiyu_num;
    }

    public int getXueyu_num() {
        return xueyu_num;
    }

    public void setXueyu_num(int xueyu_num) {
        this.xueyu_num = xueyu_num;
    }

    public int getTebing_num() {
        return tebing_num;
    }

    public void setTebing_num(int tebing_num) {
        this.tebing_num = tebing_num;
    }

    public int getPinghe_num() {
        return pinghe_num;
    }

    public void setPinghe_num(int pinghe_num) {
        this.pinghe_num = pinghe_num;
    }

    public int getDone_ques_num() {
        return done_ques_num;
    }

    public void setDone_ques_num(int done_ques_num) {
        this.done_ques_num = done_ques_num;
    }

    public int getSum_ques_num() {
        return sum_ques_num;
    }

    public void setSum_ques_num(int sum_ques_num) {
        this.sum_ques_num = sum_ques_num;
    }

    public String getQuestion_ids() {
        return question_ids;
    }

    public void setQuestion_ids(String question_ids) {
        this.question_ids = question_ids;
    }

    public String getTest_result() {
        return test_result;
    }

    public void setTest_result(String test_result) {
        this.test_result = test_result;
    }

    public String getDone_ques_time() {
        return done_ques_time;
    }

    public void setDone_ques_time(String done_ques_time) {
        this.done_ques_time = done_ques_time;
    }

    public String getDone_factors_time() {
        return done_factors_time;
    }

    public void setDone_factors_time(String done_factors_time) {
        this.done_factors_time = done_factors_time;
    }

    public String getIs_done_ques() {
        return is_done_ques;
    }

    public void setIs_done_ques(String is_done_ques) {
        this.is_done_ques = is_done_ques;
    }

    public String getIs_done_factors() {
        return is_done_factors;
    }

    public void setIs_done_factors(String is_done_factors) {
        this.is_done_factors = is_done_factors;
    }

    public String getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(String is_buy) {
        this.is_buy = is_buy;
    }

    public String getIs_sure_ques() {
        return is_sure_ques;
    }

    public void setIs_sure_ques(String is_sure_ques) {
        this.is_sure_ques = is_sure_ques;
    }

    public String getLead_factors_ids() {
        return lead_factors_ids;
    }

    public void setLead_factors_ids(String lead_factors_ids) {
        this.lead_factors_ids = lead_factors_ids;
    }

    public int getSum_factors_num() {
        return sum_factors_num;
    }

    public void setSum_factors_num(int sum_factors_num) {
        this.sum_factors_num = sum_factors_num;
    }

    public int getDone_factors_num() {
        return done_factors_num;
    }

    public void setDone_factors_num(int done_factors_num) {
        this.done_factors_num = done_factors_num;
    }

    public String getFactors_ids_checked() {
        return factors_ids_checked;
    }

    public void setFactors_ids_checked(String factors_ids_checked) {
        this.factors_ids_checked = factors_ids_checked;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
    }

    public String getIs_sure_factors() {
        return is_sure_factors;
    }

    public void setIs_sure_factors(String is_sure_factors) {
        this.is_sure_factors = is_sure_factors;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public List<LeadFactorsBeansBean> getLeadFactorsBeans() {
        return leadFactorsBeans;
    }

    public void setLeadFactorsBeans(List<LeadFactorsBeansBean> leadFactorsBeans) {
        this.leadFactorsBeans = leadFactorsBeans;
    }

    public List<DreportBeansBean> getDreportBeans() {
        return dreportBeans;
    }

    public void setDreportBeans(List<DreportBeansBean> dreportBeans) {
        this.dreportBeans = dreportBeans;
    }

    public List<LeadResultBeansBean> getLeadResultBeans() {
        return leadResultBeans;
    }

    public void setLeadResultBeans(List<LeadResultBeansBean> leadResultBeans) {
        this.leadResultBeans = leadResultBeans;
    }

    public List<?> getPlanBeans() {
        return planBeans;
    }

    public void setPlanBeans(List<?> planBeans) {
        this.planBeans = planBeans;
    }

    public static class LeadFactorsBeansBean {
        /**
         * lead_factors_id : 1
         * lead_factors_title : 是否有高血压，常吃利尿药物？
         * physical_type : 阴虚
         * create_time : 2018-01-18 14:23:50
         * update_time :
         * type : 阴虚
         * is_delete : 0
         * is_checked :
         * count : 0
         * leadFactors_number : 0
         * sum_num : 0
         */

        private int lead_factors_id;
        private String lead_factors_title;
        private String physical_type;
        private String create_time;
        private String update_time;
        private String type;
        private String is_delete;
        private String is_checked;
        private int count;
        private int leadFactors_number;
        private int sum_num;

        public int getLead_factors_id() {
            return lead_factors_id;
        }

        public void setLead_factors_id(int lead_factors_id) {
            this.lead_factors_id = lead_factors_id;
        }

        public String getLead_factors_title() {
            return lead_factors_title;
        }

        public void setLead_factors_title(String lead_factors_title) {
            this.lead_factors_title = lead_factors_title;
        }

        public String getPhysical_type() {
            return physical_type;
        }

        public void setPhysical_type(String physical_type) {
            this.physical_type = physical_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getIs_checked() {
            return is_checked;
        }

        public void setIs_checked(String is_checked) {
            this.is_checked = is_checked;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getLeadFactors_number() {
            return leadFactors_number;
        }

        public void setLeadFactors_number(int leadFactors_number) {
            this.leadFactors_number = leadFactors_number;
        }

        public int getSum_num() {
            return sum_num;
        }

        public void setSum_num(int sum_num) {
            this.sum_num = sum_num;
        }
    }

    public static class DreportBeansBean {
        /**
         * report_id : 1
         * report_desc : 阴虚是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？是否有高血压，常吃利尿药物？
         * create_time : 2018-01-18 14:42:37
         * update_time :
         * physical_type : 阴虚
         * type : 阴虚
         * is_delete : 0
         */

        private int report_id;
        private String report_desc;
        private String create_time;
        private String update_time;
        private String physical_type;
        private String type;
        private String is_delete;

        public int getReport_id() {
            return report_id;
        }

        public void setReport_id(int report_id) {
            this.report_id = report_id;
        }

        public String getReport_desc() {
            return report_desc;
        }

        public void setReport_desc(String report_desc) {
            this.report_desc = report_desc;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getPhysical_type() {
            return physical_type;
        }

        public void setPhysical_type(String physical_type) {
            this.physical_type = physical_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }
    }

    public static class LeadResultBeansBean {
        /**
         * result_id : 1
         * result_title : 是否有高血压，常吃利尿药物？
         * create_time : 2018-01-18 16:40:14
         * update_time :
         * physical_type : 阴虚
         * type : 阴虚
         * is_delete : 0
         */

        private int result_id;
        private String result_title;
        private String create_time;
        private String update_time;
        private String physical_type;
        private String type;
        private String is_delete;

        public int getResult_id() {
            return result_id;
        }

        public void setResult_id(int result_id) {
            this.result_id = result_id;
        }

        public String getResult_title() {
            return result_title;
        }

        public void setResult_title(String result_title) {
            this.result_title = result_title;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getPhysical_type() {
            return physical_type;
        }

        public void setPhysical_type(String physical_type) {
            this.physical_type = physical_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }
    }
}
