package com.sd.lib.demo.brightness

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.brightness.FStatusBarBrightness
import com.sd.lib.itemholder.FItemHolder
import com.sd.lib.systemui.statusbar.FStatusBarUtils

class MainActivity : AppCompatActivity(), FStatusBarBrightness.Item, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val brightness = FItemHolder.activity(this).getOrCreateItem(FStatusBarBrightness::class.java)
        brightness.callback = object : FStatusBarBrightness.Callback {
            override fun updateItem(item: FStatusBarBrightness.Item?) {
                val isDark = !(item ?: this@MainActivity).isLightStatusBar()
                FStatusBarUtils.setBrightness(this@MainActivity, isDark)
            }
        }
        // 设置默认Item
        brightness.setDefaultItem(this)
    }

    /**
     * 亮色背景，暗色图标
     */
    private val _itemLight = object : FStatusBarBrightness.Item {
        override fun isLightStatusBar(): Boolean {
            return true
        }
    }

    /**
     * 暗色背景，亮色图标
     */
    private val _itemDark = object : FStatusBarBrightness.Item {
        override fun isLightStatusBar(): Boolean {
            return false
        }
    }

    override fun isLightStatusBar(): Boolean {
        return false
    }

    override fun onClick(v: View?) {
        val brightness = FItemHolder.activity(this).getOrCreateItem(FStatusBarBrightness::class.java)
        when (v!!.id) {
            R.id.btn_light -> brightness.addItem(_itemLight)
            R.id.btn_dark -> brightness.addItem(_itemDark)
            R.id.btn_remove -> {
                brightness.removeItem(_itemLight)
                brightness.removeItem(_itemDark)
            }
        }
    }
}