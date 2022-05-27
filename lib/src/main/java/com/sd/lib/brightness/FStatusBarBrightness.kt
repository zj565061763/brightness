package com.sd.lib.brightness

import android.app.Activity
import com.sd.lib.itemholder.FItemHolder
import com.sd.lib.stacktop.FStackTop

class FStatusBarBrightness : FStackTop<FStatusBarBrightness.Item>(), FItemHolder.Item<Activity> {
    /** 回调对象 */
    var callback: Callback? = null

    override fun updateItem(item: Item?) {
        callback?.updateItem(item)
    }

    interface Item {
        /**
         * true-亮背景暗图标；false-暗背景亮图标
         */
        fun isLightStatusBar(): Boolean
    }

    interface Callback {
        fun updateItem(item: Item?)
    }

    override fun init(target: Activity) {
    }

    override fun close() {
    }
}