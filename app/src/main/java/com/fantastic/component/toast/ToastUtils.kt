package com.fantastic.component.toast

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.fantastic.component.R


class ToastUtils {

    companion object MyToastUtils {
        fun tintIcon(drawable: Drawable, @ColorInt tintColor: Int): Drawable? {
            drawable.setColorFilter(tintColor, PorterDuff.Mode.SRC_IN)
            return drawable
        }

        fun tint9PatchDrawableFrame(context: Context, @ColorInt tintColor: Int): Drawable? {
            val toastDrawable =
                AppCompatResources.getDrawable(context, R.drawable.toast_frame) as NinePatchDrawable
            return tintIcon(toastDrawable, tintColor)
        }

        fun setBackground(view: View, drawable: Drawable?) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) view.background =
                drawable else view.setBackground(drawable)
        }

        fun getDrawable(context: Context, @DrawableRes id: Int): Drawable? {
            return AppCompatResources.getDrawable(context, id)
        }

        fun getColor(context: Context, @ColorRes color: Int): Int {
            return ContextCompat.getColor(context, color)
        }
    }


}