package com.cnpeng.bus.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.cnpeng.bus.R
import com.cnpeng.bus.main.CusApplication
import java.lang.reflect.Field
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus


/**
 * 作者：CnPeng

 * 时间：2018/2/2:上午10:17

 * 说明：通用工具类
 */
object CommonUtils {


    /**
     * 获取当前版本名称： versionName
     *
     * @return
     */
    fun getVersionName(context: Context): String {
        var versionName = ""
        val packageManager = context.packageManager
        try {
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return versionName
    }

    /**
     * 获取APP名称和版本号名称的拼接字符串，二者用换行符间隔
     */
    fun getAppNameAndVersionName(context: Context): String {
        return context.resources.getString(R.string.app_name).plus("\n").plus("版本号：").plus(getVersionName(context))
    }

    /**
     * 备选颜色的数组
     */
    var colors = intArrayOf(R.color.blue_14a5ef, R.color.green_02bd89, R.color.red_f6584f, R.color
            .orange_eb9e21)
    /**
     * 备选颜色的数组2
     */
    var colors2 = intArrayOf(R.color.blue_14a5ef, R.color.green_02bd89, R.color.red_f6584f, R.color
            .orange_eb9e21, R.color.blue_14a5ef, R.color.orange_eb9e21, R.color.red_f6584f)

    /**
     * 获取完全随机的颜色
     *
     * @return int行的rgb颜色值
     */
    fun randomColor(): Int {
        val random = Random()
        val red = random.nextInt(220)//0-190	,如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        val green = random.nextInt(220)//0-190, 值越大，生成的颜色越鲜艳
        val blue = random.nextInt(220)//0-190
        return Color.rgb(red, green, blue)//使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
    }


    /**
     * 获取指定颜色中的随机色
     *
     * @return int型颜色值的ID
     */
    fun getRandomColor(colors: IntArray): Int {
        //        int[] colors = {R.color.blue_14a5ef, R.color.red_f6584f, R.color.green_02bd89, R.color.orange_ea9e21};
        val random = Random()
        val position = random.nextInt(colors.size)     //生成<4 的随机数, 不包含4 ,具体取值 0-3

        return colors[position]    //返回指定颜色中的随机色的ID
    }

    /**
     * 生成圆角图片--有边线无填充色，对应的xml中的shape标签 (代码创建shape)
     *
     * @param radiu 单位是px
     * @param rgb   需要是 ContextCompat.getColor（）得到的int
     * @return 返回drawable类型的图片
     */
    fun generateStrokeDrawable(rgb: Int, radiu: Float): Drawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE//设置为矩形，默认就是矩形
        //        drawable.setColor(rgb);  //传入的是一个int 类型的RGB颜色值,这是设置背景颜色
        drawable.cornerRadius = radiu
        drawable.setStroke(2, rgb)     //边框
        return drawable
    }

    /**
     * 生成圆角图片，只有填充色，没有边线
     */
    fun generateSolid_RadiusDrawable(solidColor: Int, radiu: Float): Drawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE//设置为矩形，默认就是矩形
        drawable.setColor(solidColor)  //传入的是一个int 类型的RGB颜色值,这是设置背景颜色
        drawable.cornerRadius = radiu
        return drawable
    }

    /**
     * 自定义方法生成左侧矩形右侧半圆的图片，对应的xml中的shape标签
     *
     * @param rgb    int型颜色
     * @param radius 半径的数组 8个参数 如{8,8,16,16,8,8,16,16},两个一组,按顺序分别控制 左上角,右上角,右下角,左下角的弧度
     * @return 返回drawable类型的图片
     */
    fun generateHalfCircleDrawable(rgb: Int, radius: FloatArray): Drawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE//设置为矩形，默认就是矩形
        //        drawable.setColor(rgb);  //传入的是一个int 类型的RGB颜色值,这是设置背景颜色
        drawable.cornerRadii = radius
        drawable.setStroke(1, rgb)     //边框
        return drawable
    }

    /**
     * 自定义方法生成左侧矩形右侧半圆的并带有填充色的¬图片，对应的xml中的shape标签
     *
     * @param rgb    int型颜色
     * @param radius 半径的数组
     * @return 返回drawable类型的图片
     */
    fun generateHalfCircleWithSolidDrawable(rgb: Int, radius: FloatArray): Drawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE//设置为矩形，默认就是矩形
        drawable.setColor(rgb)  //传入的是一个int 类型的RGB颜色值,这是设置背景颜色
        drawable.cornerRadii = radius
        //        drawable.setStroke(1, rgb);     //边框
        return drawable
    }


    /**
     * 自定义方法生成圆形的图片，对应的xml中的shape标签
     *
     * @param rgb   int型颜色
     * @param radiu 半径
     * @return 返回drawable类型的图片
     */
    fun generateCircleDrawable(rgb: Int, radiu: Float): Drawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.OVAL//椭圆
        drawable.setColor(ContextCompat.getColor(CusApplication().mContext, R.color.bg_f3f3f3))
        //传入的是一个int 类型的RGB颜色值,这是设置背景颜色
        drawable.cornerRadius = radiu
        drawable.setStroke(1, rgb)     //边框
        return drawable
    }

    fun generateCircleDrawable(): Drawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.OVAL//椭圆
        drawable.setSize(32, 32)
        drawable.setColor(ContextCompat.getColor(CusApplication().mContext, R.color.major_ffc000))
        return drawable
    }

    /**
     * Set集合转换成字符串
     *
     * @param list 需要转换的list集合
     * @return 返回字符串
     */
    fun List2String(list: List<String>?): String {
        var result = ""//最终的拼接结果,初始化为""

        if (list == null) {
            return result
        }
        for (i in list.indices) {
            val str = list[i]
            if (i == 0) {       //如果是第一个元素，不拼接逗号
                result = str
            } else {
                result = result + "," + str
            }
            //            Log.e("abd", "拼接集合元素：" + result);
        }
        return result
    }

    //每个字符都加上双引号,因为数据库查询时,如果是字符串,必须加双引号,否则查询不出来
    fun List2StringStr(list: List<String>?): String {
        var result = ""//最终的拼接结果,初始化为""

        if (list == null) {
            return result
        }
        for (i in list.indices) {
            val str = list[i]
            if (i == 0) {       //如果是第一个元素，不拼接逗号
                result = "\"" + str + "\""
            } else {
                result = result + "," + "\"" + str + "\""
            }
            //            Log.e("abd", "拼接集合元素：" + result);
        }
        return result
    }

    /**
     * sqlite 用where in查询 排序
     *
     * @param list       需要排序的list
     * @param columnName 排序的列的名字
     * @param orderType  升序降序
     */
    fun ListForQuery(list: List<String>?, columnName: String, orderType: String): String {
        var result = ""//最终的拼接结果,初始化为""
        if (list == null) {
            return result
        }
        for (i in list.indices) {
            val str = list[i]
            val orderStr = "$columnName=$str $orderType"
            if (i == 0) {       //如果是第一个元素，不拼接逗号
                result = orderStr
            } else {
                result = result + "," + orderStr
            }
        }
        return result
    }

    fun ListForQueryStr(list: List<String>?, columnName: String, orderType: String): String {
        var result = ""//最终的拼接结果,初始化为""
        if (list == null) {
            return result
        }
        for (i in list.indices) {
            val str = list[i]
            val orderStr = "$columnName=\"$str\" $orderType"
            if (i == 0) {       //如果是第一个元素，不拼接逗号
                result = orderStr
            } else {
                result = result + "," + orderStr
            }
        }
        return result
    }

    /**
     * 字符串转集合 将从服务器获取到的个性标签的字符串通过正则切割转成数组，然后转成集合
     *
     * @param str 从服务器获取到的个性标签组成的字符串
     * @return 已有的个性标签的List集合
     */
    fun String2List(str: String): List<String> {
        val list = ArrayList<String>()  //预定义集合
        val lables = str.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()       //以逗号为正则进行切割得到字符串数组
        Collections.addAll(list, *lables)
        return list
    }

    /**
     * dp转PX
     *
     * @param context 上下文
     * @param dpValue 要转换的dp
     * @return 转换后的px
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 利用android提供的原生工具类实现转换
     */
    fun dp2px(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources
                .displayMetrics).toInt()
    }

    /**
     * PX 转dp
     *
     * @param context 上下文
     * @param pxValue 要转换的px
     * @return 转换后的dp
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将毫秒值转成 年月日时分秒的格式
     *
     * @param date 毫秒值
     * @return 格式化后的时间字符串
     */
    fun formatDate(date: Date?): String {
        val format = SimpleDateFormat("yyyy年MM月dd日HH:mm：ss")
        return if (date != null) {
            format.format(date)
        } else {    //TODO 在获取文档的时候，没有获取时间
            ""
        }
    }

    @SuppressLint("HardwareIds")
            /**
             * 获取设备id
             */
    fun getDeviceId(): String {
        return Settings.Secure.getString(CusApplication().mContext.contentResolver, Settings.Secure
                .ANDROID_ID)
    }

    /**
     * 170627 获取状态栏的高度 单位PX
     *
     * @param context 传入上下文
     */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId != 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 实现沉浸式状态栏，适用于5.0及以上版本
     * 想让哪一部分的背景延伸到状态栏，就在其节点中设置 fitSystemWindow = true。 clipToPadding=true
     *
     * @param activity 要设置为沉浸式的界面所在的activity
     * @param colorId  状态栏需要展示的颜色（如果有标题栏传入标题栏的颜色，没有标题栏则用透明色--传0）
     */
    fun setImmersionStatusBar(activity: Activity, colorId: Int) {
        //状态栏沉浸式的实现
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现(清除原有的TRANSLUCENT flag，设置ui全屏，增加更改状态栏的flag,更改状态栏颜色)
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View
                    .SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            //手动设置状态栏的颜色为标题栏的颜色
            var statusBarColorID = android.R.color.transparent
            if (colorId != 0) {
                statusBarColorID = colorId
            }
            window.statusBarColor = activity.resources.getColor(statusBarColorID)
        }
    }

    /**
     * 沉浸式状态栏的实现，适用于4.4以上版本
     */
    fun setImmersionStatusBar2(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = activity.window
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_NAVIGATION)
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels

        //方式2
        // WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    @SuppressLint("PrivateApi")
            /**
             * 获取状态栏高度
             */
    fun getStatusBarHeight(): Int {
        val c: Class<*>
        val obj: Any
        val field: Field
        val x: Int
        var sbar = 0
        try {
            c = Class.forName("com.android.internal.R\$dimen")
            obj = c.newInstance()
            field = c.getField("status_bar_height")
            x = Integer.parseInt(field.get(obj).toString())
            sbar = CusApplication().mContext.resources.getDimensionPixelSize(x)
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        return sbar
    }

    /**
     * 指定字符串中某部分带有下划线且具有点击事件
     *
     * @param str             源字符串
     * @param start           从哪开始设置下划线或点击事件
     * @param end             到哪结束设置下划线或点击事件
     * @param colorInt        下划线及其对应文字的颜色
     * @param isShowUnderLine 是否展示下划线
     */
    fun getSpannableStrWithUnderLine(str: String, start: Int, end: Int, colorInt: Int,
                                     isShowUnderLine: Boolean): SpannableString {
        val spannableString = SpannableString(str)

        if (isShowUnderLine) {
            spannableString.setSpan(UnderlineSpan(), start, end, SPAN_EXCLUSIVE_EXCLUSIVE)     //下划线
        }

        spannableString.setSpan(ForegroundColorSpan(colorInt), start, end, SPAN_EXCLUSIVE_EXCLUSIVE) //下划线及文字颜色

        return spannableString
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param data  需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    fun round(data: Double?, scale: Int): Double {
        if (scale >= 0) {
            val b = if (null == data) BigDecimal("0.0") else BigDecimal(java.lang.Double.toString(data))
            val one = BigDecimal("1")
            return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toDouble()
        } else {
            return Math.round(data!!).toDouble()
        }
    }

    //把svg转换成bitmap
    fun getSVGBitmap(context: Context, vectorDrawableId: Int): Bitmap {
        val bitmap: Bitmap
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val vectorDrawable = context.getDrawable(vectorDrawableId)
            bitmap = Bitmap.createBitmap(vectorDrawable!!.intrinsicWidth, vectorDrawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
            vectorDrawable.draw(canvas)
        } else {
            bitmap = BitmapFactory.decodeResource(context.resources, vectorDrawableId)
        }
        return bitmap
    }


    /**
     * 得到当前用户身份  CnPeng
     */
    fun getAuthLevelStr(authLevel: Int): String {
        var levelString = ""
        if (authLevel == 0) {
            levelString = "未认证用户"
        } else if (authLevel == 1) {
            levelString = "待认证审核用户"
        } else if (authLevel == 2) {
            levelString = "普通认证用户"
        } else if (authLevel == 3) {
            levelString = "高级认证用户"
        }
        return levelString
    }


    /**
     * 设置缩放之后的图片到指定的ImageView，防止大图加载时产生OOM
     *
     * @param imageView imageView控件
     * @param imgResId  image资源id
     */
    fun setScaledImgToImageView(context: Context, imageView: ImageView, imgResId: Int) {
        val wm: WindowManager? = context.getSystemService(WINDOW_SERVICE) as WindowManager // 获取屏幕管理器
        if (null != wm) {
            val screenWidth = wm.defaultDisplay.width // 获取屏幕的宽
            val screenHeight = wm.defaultDisplay.height // 获取屏幕的高

            val newmap = getScaledBitmap(imgResId, screenWidth, screenHeight, context)
            imageView.setImageBitmap(newmap)
        }
    }

    /**
     * 将缩放后的图作为背景设置给指定View
     *
     * @param context  上下文
     * @param view     需要设置背景的view
     * @param imgResId 图片资源id
     */
    fun setScaledImgAsBackGroudToView(context: Context, view: View, imgResId: Int) {
        val wm: WindowManager? = context.getSystemService(WINDOW_SERVICE) as WindowManager // 获取屏幕管理器
        if (null != wm) {
            val screenWidth = wm.defaultDisplay.width // 获取屏幕的宽
            val screenHeight = wm.defaultDisplay.height // 获取屏幕的高

            val newmap = getScaledBitmap(imgResId, screenWidth, screenHeight, context)
            view.background = BitmapDrawable(context.resources, newmap)
        }
    }

    /**
     * 获取压缩后的图片，避免大图加载时出现OOM
     *
     * @param imgResId  图片资源id
     * @param reqHeight 需要的高度
     * @param reqWidth  需要的宽度
     * @param context   上下文
     */
    fun getScaledBitmap(imgResId: Int, reqWidth: Int, reqHeight: Int, context: Context): Bitmap { // 图片路径,要求的宽高
        val options = BitmapFactory.Options()

        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, imgResId, options)

        options.inSampleSize = calcuSampleSize(options, reqWidth, reqHeight)

        options.inJustDecodeBounds = false // 值为false表示加载轮廓和内容
        return BitmapFactory.decodeResource(context.resources, imgResId, options)
    }

    /**
     * 计算图片的压缩比率
     *
     * @param options   存储图片信息的options(存储了图片的原始宽高)
     * @param reqWidth  需要的宽度
     * @param reqHeight 需要的高度
     */
    private fun calcuSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val srcHeight = options.outHeight
        val srcWidth = options.outWidth

        var sampleSize = 1

        if (srcHeight > reqHeight || srcWidth > reqWidth) {
            // 判断原图片的宽高是否大于屏幕/需求尺寸,如果大才压缩,小就不需要压缩
            // 计算出原图与屏幕的 宽比和高比(通过四舍五入得整数,保证图片能够最适合屏幕大小,如果直接取商的话,得到的图片依旧可能超出屏幕范围)
            val hightRatio = Math.round(srcHeight.toFloat() / reqHeight.toFloat()) // 四舍五入
            val widthRatio = Math.round(srcWidth.toFloat() / reqWidth.toFloat()) // 四舍五入
            sampleSize = if (hightRatio > widthRatio) hightRatio else widthRatio // 通过三目运算取小值
        }

        return sampleSize
    }

    /**
     * 180115 隐藏 魅族、Nexus、华为等底部的虚拟导航按键，避免遮挡内容
     *
     * @param activity 需要隐藏底部导航按键的Activity
     */
    fun hideBottomUIMenu(activity: Activity) {
        //隐藏虚拟按键，并且全屏  
        if (Build.VERSION.SDK_INT < 19) { // lower api  
            val v = activity.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = activity.window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View
                    .SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions
        }
    }
}