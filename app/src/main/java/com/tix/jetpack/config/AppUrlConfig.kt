package com.tix.jetpack.config

/**
 * Created by ztcao on 2018/1/17.
 */
const val BASE_URL = "http://jsjr1.3tichina.com:80/jinshangjinrong/jinshangjinrong/"
const val BASE_IMAGE_URL = "http://jsjr1.3tichina.com/jinshangjinrong/upload/"
const val BASE_AUDIO_URL = "http://jsjr1.3tichina.com/jinshangjinrong/upload/"
const val BASE_VEDIO_URL = "http://jsjr1file.3tichina.com/"
//定义URL
//用户登录
const val USER_LOGIN_URL = "nologin/login"
//修改密码
const val USER_CHANGE_PASSWORD = "nologin/updateOldPassword"
//校验旧密码密码
const val USER_VERIFY_OLD_PASSWORD = "nologin/verifyOldPassword"
//重置密码
const val USER_RESET_PASSWORD = "nologin/resetNewPwdByCode"

//获取用户的基本信息 UserRespostory
//const val USER_BASE_INFO = "/employee/findEmployeeById"
//获取验证码
const val SMS_GET_URL = "nologin/getRegistCode"

//修改或完善用户信息
const val CAR_INFO_UPDATE = "consumer/updateConsumer"

//意见反馈
const val USER_OPINION_URL = "feedBack/saveFeedBack"

/**************************** 消息接口*****************/
//查询所有消息表
const val MESSAGE_FIND_LIST = "message/findMessageList"

//查询消息详情
const val MESSAGE_DETAIL = "message/findMessageDetail"

//查询消息数量
const val MESSAGE_COUNT = "messageRecevier/findUnreadMessageCount"

//*******************首页*********************
const val HOME_URL = "course/findHomePageDataList"
//积分列表
const val SCORE_LIST_URL = "employee/findEmployeeListByScore"

//******************课程*********************
//课程分类
const val COURSE_CATEGORY_URL = "courseCategory/findCourseCategory"
//查询分类下的课程信息
const val COURSE_LIST_BY_CATEGORY_URL  = "course/findCourseListByCategory"
//根据课程名称查询所有课程列表
const val COURSE_LIST_BY_NAME_URL  = "course/findCourseListByName"
//根据tid查询课程
const val COURSE_BY_ID  = "course/findCourseById"
//根据课程对应的试题
const val COURSE_LIST_TEST_FIAL_URL    =  "courseanswer/findCourseanswerList"
//测试不通过 返回的练习题
const val  COURSE_LIST_TEST_URL     =   "courseExercise/findCourseExerciseList "
//收藏课程
const val COURSE_COLLECT_URL      =  "collection/saveCollection"
//取消收藏课程
const val COURSE_CANCEL_COLLECT_URL      =  "collection/cancelCollectionById"
//收藏列表
const val COURSE_COLLECT_LIST      =  "collection/findCollectionList"
//扫一扫
const val COURSE_QRCODE_SCAN_URL  =  "qrcode/findQrCodeScan"
//测试题提交接口
const val COURSE_TEST_SUBMIT_URL      = "courseanswer/submitCourseAnswers"
//查询打错的列习题
//const val COURSE_INCORRECT_ANSWER_LIST_URL   =  "courseanswer/findCourseIncorrectAnswerList"
const val COURSE_INCORRECT_ANSWER_LIST_URL   =  "courseanswer/findCourseAnswerList"
//学习记录
const val COURSE_STUDY_RECORD_LIST_URL      =  "studyRecord/findStudyRecordList"
const val COURSE_STUDY_RECORD_URL      =  "studyRecord/saveStudyRecord"
const val COURSE_START_STUDY_RECORD_URL      =  "studyRecord/startStudyRecord"
const val COURSE_FINISH_STUDY_RECORD_URL      =  "studyRecord/finishStudyRecord"
//******************评论****************
//获取评论列表
const val COMMENT_LIST_URL = "comment/findCommentList"
//发布评论
const val COMMENT_SAVE_URL = "comment/saveComment"
//回复评论
const val COMMENT_REPLY_URL = "commentReply/saveCommentReply"

//******************考试**************

//试卷列表接口
const val EXAM_LIST_URL = "examination/findMyExaminationList"
//试卷详情接口
const val EXAM_DETAIL_URL = "examQuestionsRel/findExamQuestionsRelList"
//试卷提交接口
const val EXAM_SUBMIT_URL = "examAnswers/submitExamAnswers"
//试卷剩余时间
const val EXAM_REMAINDER_URL = "examJoinEmployee/findExamTime"
//******************资讯**************
const val INFO_LIST_URL = "information/findInformationList"
//资讯详情
const val INFO_DETAIL_URL = "information/findInformationById"

//******************意见反馈**************
const val FEED_BACK_URL = "feedback/saveFeedback"
//启动页URL
const val START_PAGE_URL  = "startPage/findLastestStartPage"
//积分
const val SCORE_COURSE_URL  = "poIntDetail/savePoIntDetail"
 //打开关闭推送
const val PUSH_FLAG_URL = "employee/setPushMessage"
//自己的积分
const val MINE_SCORE_URL  = "employee/findMyScore"
