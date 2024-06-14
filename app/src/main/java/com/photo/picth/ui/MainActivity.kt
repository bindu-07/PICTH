package com.photo.picth.ui

import android.content.BroadcastReceiver
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.photo.picth.R
import com.photo.picth.databinding.ActivityMainBinding
import com.photo.picth.presentation.LearnHowToUseActivity
import com.photo.picth.ui.activities.auth.ForgotPasswordActivity
import com.photo.picth.ui.presentation.bannerSettings.BannerSettingsActivity
import com.photo.picth.ui.presentation.download.DownloadActivity
import com.photo.picth.ui.presentation.feed.FeedFragment
import com.photo.picth.ui.presentation.homepage.HomeFragment
import com.photo.picth.ui.presentation.message.MessageFragment
import com.photo.picth.ui.presentation.profile.ProfileActivity
import com.photo.picth.ui.presentation.wallet.WalletActivity
import com.photo.picth.utils.ui.CommonMethod
import com.photo.picth.utils.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    var isIndex: Int = 0
    var mNetworkReceiver: BroadcastReceiver? = null
    var count = 0
    private var toggle: ActionBarDrawerToggle? = null

    companion object {
        @JvmStatic
        lateinit var mInstance: MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInstance = this
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        setUpTab()
        drawer()
    }







    @Synchronized
    fun getInstance(): MainActivity {
        return mInstance
    }


    private fun initUI() {

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBack()
            }
        })
    }

    private fun drawer() {
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle!!)
        toggle!!.syncState()

        binding.navView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.white)

        binding.navView.setNavigationItemSelectedListener { item ->
            // Handle navigation view item clicks here.
            val id: Int = item.itemId

            if (id ==  R.id.nav_profile) {
                startNewActivity(ProfileActivity::class.java)
               
            }
            if (id == R.id.nav_banner_settings) {
                startNewActivity(BannerSettingsActivity::class.java)

            }
            if (id == R.id.nav_Wallet) {
                // Handle the action
                startNewActivity(WalletActivity::class.java)

            }
            if (id == R.id.nav_Downloads) {
                // Handle the action
                startNewActivity(DownloadActivity::class.java)

            }
            if (id == R.id.nav_Share) {
                // Handle the action
                CommonMethod.shareApp(this)
            }
            if (id == R.id.nav_app_use) {
                // Handle the action
                startNewActivity(LearnHowToUseActivity::class.java)
            }

            if (id == R.id.nav_pass) {
                // Handle the action
                startNewActivity(ForgotPasswordActivity::class.java)
            }

            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }


    private fun setUpTab(){
        replaceFragment(HomeFragment(), "", 0)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Dashboard"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Message"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Video"))
         binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 ->  replaceFragment(HomeFragment(), "", 0)
                    1 ->  replaceFragment(MessageFragment(), "", 1)
                    2 -> replaceFragment(FeedFragment(), "", 0)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Optional: handle unselected state
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Optional: handle reselected state
            }
        })
      //  binding.imageView.visibility=View.GONE
    }
    private fun replaceFragment(fragment1: Fragment, fragment: String, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHost, fragment1)
        transaction.addToBackStack(fragment)
        transaction.commit()
        isIndex = index
    }

    fun handleBack() {
        if (isIndex == 1) {
            replaceFragment(HomeFragment(), "", 0)
        } else {
            finishAffinity()

        }
    }

}