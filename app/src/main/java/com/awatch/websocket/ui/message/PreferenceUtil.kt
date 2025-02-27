package com.awatch.websocket.ui.message

import com.tencent.mmkv.MMKV

object PreferenceUtil {

    val p: MMKV by lazy {
        MMKV.defaultMMKV()!!
    }

    /**
     * 是否显示引导界面
     *
     * @return
     */
    fun isShowGuide(): Boolean {
        return getBoolean(SHOW_GUIDE, true)
    }

    /**
     * 设置是否显示引导界面
     *
     * @param value
     */
    fun setShowGuide(value: Boolean) {
        putBoolean(SHOW_GUIDE, value)
    }


    /**
     * 用户是否同意了隐私协议
     * 默认没用同意 在登录页面 用户勾选了Checkbox 同意隐私协议 才会设置为true
     *
     * @return
     */
    fun isAgreePrivacy(): Boolean {
        return getBoolean(privacy, false)
    }

    /**
     * 用户是否同意了隐私协议
     *
     * @param value
     */
    fun setAgreePrivacy(value: Boolean) {
        putBoolean(privacy, value)
    }

    /**
     * 是否登录了
     *
     * @return
     */
    fun isLogin(): Boolean {

        return true
    }

    /**
     * 设置用户Id
     *
     * @param value
     */
    fun setUserId(value: String?) {
        p.encode(USER_ID, value)
    }

    /**
     * 获取用户Id
     *
     * @return
     */
    fun getUserId(): String {
        return p.decodeString(USER_ID, "Constant.ANONYMOUS")!!
    }

    /**
     * 设置用户Token
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setToken(value: String?) {
        p.encode(TOKEN, value)
    }

    /**
     * 获取用户Token
     *
     * @return
     */
    fun getToken(): String? {
        return p.decodeString(TOKEN)
    }


    /**
     * 保存极光推送的ID
     */
    fun setJPushID(value: String?) {
        p.encode("registrationID", value)
    }

    /**
     * 获取极光推送的ID
     */
    fun getJPushIDn(): String? {
        return p.decodeString("registrationID")
    }


    /**
     * 设置用户Chat Token
     *
     * @param value
     */
    fun setChatToken(value: String) {
        p.encode(CHAT_TOKEN, value)
    }

    /**
     * 获取用户Chat Token
     *
     * @return
     */
    fun getChatToken(): String {
        return p.decodeString(CHAT_TOKEN)!!
    }


    /**
     * 设置省市区三级列表
     *
     * @param value
     */
    fun setCityList(value: String) {
        p.encode(CityList, value)
    }

    /**
     * 获取省市区三级列表
     *
     * @return
     */
    fun getCityList(): String {
        return p.decodeString(CityList)!!
    }



    //region 辅助方法
    private fun getString(key: String): String? {
        return p.decodeString(key, null)
    }

    private fun putString(key: String, value: String) {
        p.edit().putString(key, value).apply()
    }

    private fun delete(data: String) {
        p.edit().remove(data).commit()
    }


    /**
     * 获取boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    private fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return p.getBoolean(key, defaultValue)
    }

    /**
     * 保存boolean
     *
     * @param key
     * @param value
     */
    private fun putBoolean(key: String, value: Boolean) {
        p.edit().putBoolean(key, value).apply()
    }

    //endregion


    /**
     * 设置店铺ID
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setShopId(value: String?) {
        p.encode(SHOP_ID, value)
    }

    /**
     * 获取店铺ID
     *
     * @return
     */
    fun getShopId(): String? {
        return p.decodeString(SHOP_ID)
    }


    /**
     * 设置是否经营者
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setAdmin(value: String?) {
        p.encode(ADMIN, value)
    }

    /**
     * 获取是否经营者
     *
     * @return
     */
    fun getAdmin(): String? {
        return p.decodeString(ADMIN)
    }


    /**
     * 设置极光id
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setRegistrationId(value: String?) {
        p.encode(RegistrationId, value)
    }

    /**
     * 获取极光id
     *
     * @return
     */
    fun getRegistrationId(): String? {
        return p.decodeString(RegistrationId)
    }


    /**
     * 保存用户登录手机号
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setUserLoginPhone(value: String?) {
        p.encode(UserLoginPhone, value)
    }

    /**
     * 获取保存用户登录手机号
     *
     * @return
     */
    fun getUserLoginPhone(): String? {
        return p.decodeString(UserLoginPhone)
    }

    /**
     * 退出
     */
    fun logout() {
        //p.removeValueForKey(USER_ID)
        //p.removeValueForKey(TOKEN)
        //p.removeValueForKey(CHAT_TOKEN)

        //退出登录 直接清除掉所有的缓存数据
        //保留是否是第一次进入App这个字段 首次进入App会有隐私权限弹窗
        //保留上一次 是否同意隐私协议的状态 如果同意了 下次退出登录到登录页面 直接选中就行
        val isFirst = isShowGuide()
        val mRegistrationId = getRegistrationId()
        p.clear()
        setShowGuide(isFirst)
        //退出登录 保留极光id 极光id跟随的是设备id 不跟随账户切户改变
        setRegistrationId(mRegistrationId)
    }


    private const val HOME_SHOW = "HOME_SHOW"

    /**
     * 首页 收起隐藏功能
     *
     * 可以加密后存储，防止泄露
     * @param value
     */
    fun setHome(value: String?) {
        p.encode(HOME_SHOW, value)
    }

    /**
     * 首页 收起隐藏功能
     *
     * @return
     */
    fun getHome(): String? {
        return p.decodeString(HOME_SHOW)
    }


    /**
     * 锁单开单凭证  0 关 1 开
     */
    fun setOrderLockSwitch(value: Int) {
        p.encode(OrderLockSwitch, value)
    }

    /**
     * 锁单开单凭证 0 关 1 开
     */
    fun getOrderLockSwitch(): Int {
        return p.decodeInt(OrderLockSwitch, 0)
    }


    /**
     * 保存搜索历史
     */
    fun saveSearchHistory(value: String?) {
        p.encode(SearchHistory, value)
    }


    /**
     * 获取搜索历史
     */
    fun getSearchHistory(): String? {
        return p.decodeString(SearchHistory, "[]")
    }


    /**
     * 是否显示引导界面key
     */
    private const val UserLoginPhone = "UserLoginPhone"
    private const val CityList = "CityList"
    private const val RegistrationId = "RegistrationId"
    private const val ADMIN = "ADMIN"
    private const val SHOP_ID = "SHOP_ID"
    private const val SHOW_GUIDE = "SHOW_GUIDE"
    private const val USER_ID = "user"
    private const val TOKEN = "token"
    private const val CHAT_TOKEN = "CHAT_TOKEN"
    private const val SPLASH_AD = "SPLASH_AD"
    private const val KEY_DEVICE_ID = "KEY_DEVICE_ID"
    private const val privacy = "privacy"

    //用户昵称
    private const val username = "username"

    //用户头像
    private const val userLogo = "userLogo"

    //用户身份
    private const val roleName = "roleName"

    //注册时间
    private const val createdAt = "createdAt"

    //是否经营者：1是，0否
    private const val userAdmin = "userAdmin"

    //实名认证状态 0未认证 1已认证
    private const val verifyStatus = "verifyStatus"

    //是否线上展会VIP 1是 0否
    private const val exhibitVip = "exhibitVip"

    //员工id
    private const val staffId = "staffId"

    //员工管理列表
    private const val staffPermissionList = "staffPermissionList"

    //锁单开单凭证上传 设置开关
    private const val OrderLockSwitch = "OrderLockSwitch"

    //当前店铺id
    private const val CurrentShopId = "CurrentShopId"

    //搜索历史
    private const val SearchHistory = "SearchHistory"

    //当前店铺名称
    private const val CurrentShopName = "CurrentShopName"

    //小程序封面
    private const val ShopCover = "ShopCover"


    /**
     * 保存 当前店铺logo
     */

    /**
     * 保存 是否线上展会VIP 1是 0否
     */
    fun setExhibitVip(value: Int) {
        p.encode(exhibitVip, value)
    }

    /**
     * 获取 是否线上展会VIP 1是 0否
     */
    fun getExhibitVip(): Int {
        return p.decodeInt(exhibitVip, -1)
    }


    /**
     * 保存 实名认证状态 0未认证 1已认证
     */
    fun setVerifyStatus(value: Int) {
        p.encode(verifyStatus, value)
    }

    /**
     * 获取 实名认证状态 0未认证 1已认证
     */
    fun getVerifyStatus(): Int {
        return p.decodeInt(verifyStatus, -1)
    }


    /**
     * 保存是否经营者：1是，0否
     */
    fun setUserAdmin(value: Int) {
        p.encode(userAdmin, value)
    }

    /**
     * 获取是否经营者：1是，0否
     */
    fun getUserAdmin(): Int {
        return p.decodeInt(userAdmin, -1)
    }


    /**
     * 保存员工id
     */
    fun setStaffId(value: Int) {
        p.encode(staffId, value)
    }

    /**
     * 获取员工id
     */
    fun getStaffId(): Int {
        return p.decodeInt(staffId, -1)
    }


    /**
     * 保存员工管理列表
     */
    fun setStaffPermissionList(value: String?) {
        p.encode(staffPermissionList, value)
    }

    /**
     * 获取员工管理列表
     */
    fun getStaffPermissionList(): String? {
        return p.decodeString(staffPermissionList)
    }


    /**
     * 保存注册时间
     */
    fun setCreatedAt(value: String?) {
        p.encode(createdAt, value)
    }

    /**
     * 获取注册时间
     */
    fun getCreatedAt(): String? {
        return p.decodeString(createdAt)
    }


    /**
     * 保存用户身份
     */
    fun setRoleName(value: String?) {
        p.encode(roleName, value)
    }

    /**
     * 获取用户身份
     */
    fun getRoleName(): String? {
        return p.decodeString(roleName)
    }


    /**
     * 保存用户头像
     */
    fun setUserLogo(value: String?) {
        p.encode(userLogo, value)
    }

    /**
     * 获取用户头像
     */
    fun getUserLogo(): String? {
        return p.decodeString(userLogo)
    }


    /**
     * 保存用户昵称
     */
    fun setUserName(value: String?) {
        p.encode(username, value)
    }

    /**
     * 获取用户昵称
     */
    fun getUserName(): String? {
        return p.decodeString(username)
    }


    /**
     * 保存当前店铺名称
     */
    fun setShopName(value: String) {
        p.encode(CurrentShopName, value)

    }

    /**
     * 获取当前店铺名称
     */
    fun getShopName(): String {
        return p.decodeString(CurrentShopName) ?: ""
    }


    /**
     * 店铺小程序封面 用于分享使用
     */
    fun setShopCover(value: String) {
        p.encode(ShopCover, value)

    }

    /**
     * 店铺小程序封面 用于分享使用
     */
    fun getShopCover(): String {
        return p.decodeString(ShopCover) ?: ""
    }




}