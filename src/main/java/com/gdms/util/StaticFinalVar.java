package com.gdms.util;

/**
 * 所有静态变量，包括返回的文字说明
 */
public class StaticFinalVar {
//    public static final String WEB_URL = "http://admin.openkit.cc";  // 在线测试地址
    public static final String WEB_URL = "http://localhost:8080";  // 本地测试地址
    public static final String YAN = "kit20180205";  // 这里是密码加密，混淆密文，这个我们尽量不要去修改
    public static final String IMG_FILES = "uplodefiles/image/";  // 这里是保存图片的根目录，必须保证有读写权限
    public static final String FILE = "uplodefiles/file/";
    public static final String USERNAME_OR_PWD_ERR = "密码输入错误";
    public static final String ADD_OK = "添加成功";
    public static final String ADD_ERR = "添加失败，请稍后再试";
    public static final String DEL_OK = "删除成功";
    public static final String DEL_ERR = "删除失败，请稍后再试";
    public static final String UPDATE_OK = "修改成功";
    public static final String UPDATE_ERR = "修改失败，请稍后再试";
    public static final String DEL_SOME_ZILEI = "该栏目下面存在子栏目，请先删除子栏目之后再来删除"; // 删除栏目时候如果有子类无法删除
    public static final String RELOG_IN = "，部分数据请重新登陆后加载！";
    public static final String NO_GROUP_MSG = "您没有此权限";
    public static final String CALL_GROUP = "，请联系总管理员！";
    public static final String IS_NOT_NULL = "该数据已经存在！";
    public static final String DEL_GROUP_MSG = "该分组下存在管理用户，请先删除管理用户之后再来删除！";
    public static final String PWD_NOT_EQUERY = "两次密码输入不一致！";
    public static final String DEL_ADMIN_BUT_IS_LOGIN = "不能删除当前登录的用户";
    public static final String DEL_ADMIN_BUT_IS_NULL = "没有找到该条数据";
    public static final String HAVE_PHONE = "该手机号码已经被注册";
    public static final String HAVE_NOT_PHONE = "该手机号码尚未注册";
    public static final String HAVE_EMAIL = "该邮箱已经被注册";
    public static final String HAVE_NOT_EMAIL = "该邮箱尚未注册";
    public static final String HAVE_USER = "该用户以及被注册";
    public static final String HAVE_NOT_USER = "该用户尚未注册";
    public static final String HAVE_RECOMMEND = "推荐码已经存在";
    public static final String HAVE_NOT_RECOMMEND = "不存在该推荐人";
    public static final String LOGIN_ERR = "登录失败";
    public static final String SEEK_PWD_ERR = "修改密码失败";
    public static  final String  SEEK_PWD_OK="修改密码成功";

    public static final String REGISTER_OK = "用户注册成功";
    public static final String REGISTER_ERR = "用户注册成功";

    public static final String SEARCH_ERR = "查询失败";
    public static final String SEARCH_OK = "查询成功";

    public static final String APPLYTTEACHER_OK_ = "已申请";
    public static final String APPLY_OK_ = "申请成功";
    public static final String APPLY_ERR_ = "申请失败";
    public static final String APPLYTNUMBER_ERR ="该教师申请人数已达到上限";

    public static final String RETREAT_EOR ="退选失败";
    public static final String RETREAT_OK ="退选成功";

    public static final String COEEECT_OK ="批准";
    public static final String COEEECT_ERR ="不批准";

    public static final String OOPTION_OK ="操作成功";
    public static final String OOPTION_ERR ="操作失败";

    public static final String LimitNumber_ERR ="限制人数小于现有人数,请从新设置";
    public static final String APPROVAL_ERR ="人数已超过限制";
    public static final String USERHAVE_ERR ="该用户已存在";

    public static final String MAJOR_ERR ="请选择与院部相对应的专业";
    public static final String DOWNLOAD_OK ="下载成功";

    public static final String ALLOT_OK ="分配成功";
    public static final String ALLOT_ERR ="人数超过限制";
    public static final String LOGINIDNTITY_ERR ="用户名或身份输入错误";
    public static final String LOGINSTUDENT_ERR ="用户名、密码或身份输入错误";
    public static final String LOGINAGAIN_OK ="请重新登录";
    public static final String DIDANDMID_ERR ="请输入与院部相对应的专业";
    public static final String UPLOAD_ERR ="请上传课题附件";
    public static final String TOPICSTAY_ERR ="该课题已存在，请从输入新的课题名";

}
