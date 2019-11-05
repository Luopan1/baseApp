package www.test720.a360video.network

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView

import www.test720.a360video.R


/**
 * Created by lichuanbei on 2016/12/5.
 */

object LoadingDialog {
    private var mAlertDialog: AlertDialog? = null
    private val isShow = true

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    fun createLoadingDialog(context: Context, msg: String): AlertDialog? {
        // 首先得到整个View
        val view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null)

        val imageView = view.findViewById<View>(R.id.iv_quan) as ImageView
        val operatingAnim = AnimationUtils.loadAnimation(context, R.anim.tip)
        val lin = LinearInterpolator()
        operatingAnim.interpolator = lin
        imageView.startAnimation(operatingAnim)
        // 页面中显示文本
        val tvMessage = view.findViewById<View>(R.id.tvMessage) as TextView

        // 显示文本
        tvMessage.text = msg
        mAlertDialog = AlertDialog.Builder(context, R.style.dialog).create()
        mAlertDialog!!.show()
        mAlertDialog!!.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if (mAlertDialog != null) {
                if (keyCode == KeyEvent.KEYCODE_BACK && mAlertDialog!!.isShowing) {
                    return@OnKeyListener isShow
                }
            }
            false
        })
        mAlertDialog!!.setCanceledOnTouchOutside(false)
        mAlertDialog!!.setCancelable(true)
        mAlertDialog!!.setContentView(view)
        return mAlertDialog
    }

    /**
     * 显示Dialog
     */
    fun showDialog(context: Context) {
        //每次重建靠谱
        mAlertDialog = LoadingDialog.createLoadingDialog(context, "Loading")
    }

    /**
     * 关闭Dialog
     */
    fun closeDialog() {
        if (mAlertDialog != null) {
            if (mAlertDialog!!.isShowing) {
                mAlertDialog!!.dismiss()
            }
            mAlertDialog = null
        }
    }
}
