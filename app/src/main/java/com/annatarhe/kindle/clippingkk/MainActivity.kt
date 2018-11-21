package com.annatarhe.kindle.clippingkk

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.annatarhe.kindle.clippingkk.model.AppConfig
import com.annatarhe.kindle.clippingkk.model.VersionAPI
import com.annatarhe.kindle.clippingkk.ui.AuthPage
import com.annatarhe.kindle.clippingkk.ui.HomeTabFragment
import com.annatarhe.kindle.clippingkk.ui.ProfileFragment
import com.annatarhe.kindle.clippingkk.ui.SquareFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val homeTabFragment by lazy { HomeTabFragment() }
    val squareFragment by lazy { SquareFragment() }
    val profileFragment by lazy { ProfileFragment() }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.indexScreenFragment, homeTabFragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.indexScreenFragment, squareFragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.indexScreenFragment, profileFragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        this.updateContent()

        checkUser()
        checkVersion()

    }

    private fun checkUser() {
        if (AppConfig.jwt != "" && AppConfig.uid != -1) {
            return
        }
        val intent = Intent(this, AuthPage::class.java)
        startActivity(intent)
    }

    private fun checkVersion() {
        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        Log.i("version", versionName)

        VersionAPI().GetLastVersion({ versionInfo ->
            if (versionInfo == null) {
                return@GetLastVersion
            }
            // TODO: diff with current version
            // TODO: show modal to download last version
        }, { msg ->
            Snackbar.make(container, msg, Snackbar.LENGTH_LONG).show()
        })

    }

    private fun updateContent() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.indexScreenFragment, homeTabFragment)
            .commit()
    }
}
