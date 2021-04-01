package com.fantastic.component.toast

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import com.fantastic.component.R


class CustomToast {


    companion object {
        private const val textSize = 16
        private val LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL)
        private val currentTypeface = LOADED_TOAST_TYPEFACE
        private val tintIcon = true
        private val allowQueue = true
        private var lastToast: Toast? = null
        val LENGTH_SHORT: Int = Toast.LENGTH_SHORT
        val LENGTH_LONG: Int = Toast.LENGTH_LONG

        @CheckResult
        fun normal(context: Context, @StringRes message: Int): Toast? {
            return normal(context, context.getString(message), Toast.LENGTH_SHORT, null, false)
        }

        @CheckResult
        fun normal(context: Context, message: CharSequence): Toast? {
            return normal(context, message, Toast.LENGTH_SHORT, null, false)
        }

        @CheckResult
        fun normal(context: Context, @StringRes message: Int, icon: Drawable?): Toast? {
            return normal(context, context.getString(message), Toast.LENGTH_SHORT, icon, true)
        }

        @CheckResult
        fun normal(context: Context, message: CharSequence, icon: Drawable?): Toast? {
            return normal(context, message, Toast.LENGTH_SHORT, icon, true)
        }

        @CheckResult
        fun normal(context: Context, @StringRes message: Int, duration: Int): Toast? {
            return normal(context, context.getString(message), duration, null, false)
        }

        @CheckResult
        fun normal(context: Context, message: CharSequence, duration: Int): Toast? {
            return normal(context, message, duration, null, false)
        }

        @CheckResult
        fun normal(
            context: Context, @StringRes message: Int, duration: Int,
            icon: Drawable?
        ): Toast? {
            return normal(context, context.getString(message), duration, icon, true)
        }

        @CheckResult
        fun normal(
            context: Context, message: CharSequence, duration: Int,
            icon: Drawable?
        ): Toast? {
            return normal(context, message, duration, icon, true)
        }

        @CheckResult
        fun normal(
            context: Context, @StringRes message: Int, duration: Int,
            icon: Drawable?, withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                icon,
                ToastUtils.getColor(context, R.color.defaultTextColor),
                ToastUtils.getColor(context, R.color.normalColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun normal(
            context: Context, message: CharSequence, duration: Int,
            icon: Drawable?, withIcon: Boolean
        ): Toast? {
            return custom(
                context, message, icon, ToastUtils.getColor(context, R.color.normalColor),
                ToastUtils.getColor(context, R.color.defaultTextColor), duration, withIcon, true
            )
        }

        @CheckResult
        fun custom(
            context: Context, message: CharSequence, icon: Drawable?,
            duration: Int, withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                message,
                icon,
                -1,
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                false
            )
        }

        @CheckResult
        fun custom(
            context: Context, @StringRes message: Int, @DrawableRes iconRes: Int,
            @ColorRes tintColorRes: Int, duration: Int,
            withIcon: Boolean, shouldTint: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                ToastUtils.getDrawable(context, iconRes),
                ToastUtils.getColor(context, tintColorRes),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                shouldTint
            )
        }

        @CheckResult
        fun custom(
            context: Context, message: CharSequence, @DrawableRes iconRes: Int,
            @ColorRes tintColorRes: Int, duration: Int,
            withIcon: Boolean, shouldTint: Boolean
        ): Toast? {
            return custom(
                context,
                message,
                ToastUtils.getDrawable(context, iconRes),
                ToastUtils.getColor(context, tintColorRes),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                shouldTint
            )
        }

        @CheckResult
        fun custom(
            context: Context, @StringRes message: Int, icon: Drawable?,
            @ColorRes tintColorRes: Int, duration: Int,
            withIcon: Boolean, shouldTint: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                icon,
                ToastUtils.getColor(context, tintColorRes),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                shouldTint
            )
        }

        @CheckResult
        fun custom(
            context: Context, @StringRes message: Int, icon: Drawable?,
            @ColorRes tintColorRes: Int, @ColorRes textColorRes: Int, duration: Int,
            withIcon: Boolean, shouldTint: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                icon,
                ToastUtils.getColor(context, tintColorRes),
                ToastUtils.getColor(context, textColorRes),
                duration,
                withIcon,
                shouldTint
            )
        }

        @SuppressLint("ShowToast")
        @CheckResult
        fun custom(
            context: Context, message: CharSequence, icon: Drawable?,
            @ColorInt tintColor: Int, @ColorInt textColor: Int, duration: Int,
            withIcon: Boolean, shouldTint: Boolean
        ): Toast? {
            val currentToast = Toast.makeText(context, "", duration)
            val toastLayout: View =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                    .inflate(R.layout.mytoast_layout, null)
            val toastIcon: ImageView = toastLayout.findViewById(R.id.toast_icon)
            val toastTextView: TextView = toastLayout.findViewById(R.id.toast_text)
            val drawableFrame: Drawable
            drawableFrame =
                if (shouldTint) ToastUtils.tint9PatchDrawableFrame(
                    context,
                    tintColor
                )!! else ToastUtils.getDrawable(context, R.drawable.toast_frame)!!
            ToastUtils.setBackground(toastLayout, drawableFrame)
            if (withIcon) {
                requireNotNull(icon) { "Avoid passing 'icon' as null if 'withIcon' is set to true" }
                ToastUtils.setBackground(
                    toastIcon,
                    if (tintIcon) ToastUtils.tintIcon(icon, textColor) else icon
                )
            } else {
                toastIcon.setVisibility(View.GONE)
            }
            toastTextView.text = message
            toastTextView.setTextColor(textColor)
            toastTextView.setTypeface(currentTypeface)
            toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
            currentToast.view = toastLayout
            if (!allowQueue) {
                lastToast?.cancel()
                lastToast = currentToast
            }
            return currentToast
        }


        @CheckResult
        fun success(context: Context, @StringRes message: Int): Toast? {
            return success(context, context.getString(message), Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun success(context: Context, message: CharSequence): Toast? {
            return success(context, message, Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun success(context: Context, @StringRes message: Int, duration: Int): Toast? {
            return success(context, context.getString(message), duration, true)
        }

        @CheckResult
        fun success(context: Context, message: CharSequence, duration: Int): Toast? {
            return success(context, message, duration, true)
        }

        @CheckResult
        fun success(
            context: Context,
            @StringRes message: Int,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                ToastUtils.getDrawable(context, R.drawable.ic_check_white_24dp),
                ToastUtils.getColor(context, R.color.successColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun success(
            context: Context,
            message: CharSequence,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                message,
                ToastUtils.getDrawable(context, R.drawable.ic_check_white_24dp),
                ToastUtils.getColor(context, R.color.successColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun error(
            context: Context,
            message: CharSequence,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                message,
                ToastUtils.getDrawable(context, R.drawable.ic_clear_white_24dp),
                ToastUtils.getColor(context, R.color.errorColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun error(context: Context, message: CharSequence, duration: Int): Toast? {
            return error(context, message, duration, true)
        }

        @CheckResult
        fun error(context: Context, @StringRes message: Int): Toast? {
            return error(context, context.getString(message), Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun error(context: Context, message: CharSequence): Toast? {
            return error(context, message, Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun error(context: Context, @StringRes message: Int, duration: Int): Toast? {
            return error(context, context.getString(message), duration, true)
        }

        @CheckResult
        fun warning(context: Context, @StringRes message: Int): Toast? {
            return warning(context, context.getString(message), Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun warning(context: Context, message: CharSequence): Toast? {
            return warning(context, message, Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun warning(context: Context, @StringRes message: Int, duration: Int): Toast? {
            return warning(context, context.getString(message), duration, true)
        }

        @CheckResult
        fun warning(context: Context, message: CharSequence, duration: Int): Toast? {
            return warning(context, message, duration, true)
        }

        @CheckResult
        fun warning(
            context: Context,
            @StringRes message: Int,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                ToastUtils.getDrawable(context, R.drawable.ic_error_outline_white_24dp),
                ToastUtils.getColor(context, R.color.warningColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun warning(
            context: Context,
            message: CharSequence,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                message,
                ToastUtils.getDrawable(context, R.drawable.ic_error_outline_white_24dp),
                ToastUtils.getColor(context, R.color.warningColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun info(context: Context, @StringRes message: Int): Toast? {
            return info(context, context.getString(message), Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun info(context: Context, message: CharSequence): Toast? {
            return info(context, message, Toast.LENGTH_SHORT, true)
        }

        @CheckResult
        fun info(context: Context, @StringRes message: Int, duration: Int): Toast? {
            return info(context, context.getString(message), duration, true)
        }

        @CheckResult
        fun info(context: Context, message: CharSequence, duration: Int): Toast? {
            return info(context, message, duration, true)
        }

        @CheckResult
        fun info(
            context: Context,
            @StringRes message: Int,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                context.getString(message),
                ToastUtils.getDrawable(context, R.drawable.ic_info_outline_white_24dp),
                ToastUtils.getColor(context, R.color.infoColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }

        @CheckResult
        fun info(
            context: Context,
            message: CharSequence,
            duration: Int,
            withIcon: Boolean
        ): Toast? {
            return custom(
                context,
                message,
                ToastUtils.getDrawable(context, R.drawable.ic_info_outline_white_24dp),
                ToastUtils.getColor(context, R.color.infoColor),
                ToastUtils.getColor(context, R.color.defaultTextColor),
                duration,
                withIcon,
                true
            )
        }


    }


}