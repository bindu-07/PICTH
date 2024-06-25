package com.photo.picth.ui

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.photo.picth.R
import com.photo.picth.data.api.response.BaseResponse
import com.photo.picth.data.api.response.LoginResponse
import com.photo.picth.data.api.response.LogoutResponse
import com.photo.picth.databinding.ActivityMainBinding
 import com.photo.picth.ui.activities.auth.ForgotPasswordActivity
import com.photo.picth.ui.presentation.LearnHowToUseActivity
import com.photo.picth.ui.activities.auth.LoginActivity
import com.photo.picth.ui.presentation.bannerSettings.BannerSettingsActivity
import com.photo.picth.ui.presentation.download.DownloadActivity
import com.photo.picth.ui.presentation.feed.FeedFragment
import com.photo.picth.ui.presentation.homepage.HomeFragment
import com.photo.picth.ui.presentation.message.MessageFragment
import com.photo.picth.ui.presentation.profile.ProfileActivity
import com.photo.picth.ui.presentation.wallet.WalletActivity
import com.photo.picth.utils.ui.AppController
import com.photo.picth.utils.ui.CommonMethod
import com.photo.picth.utils.ui.CommonMethod.Companion.openWhatsAppWithMessage
import com.photo.picth.utils.ui.SessionManager
import com.photo.picth.utils.ui.startNewActivity
import com.photo.picth.viewmodel.LoginViewModel
import com.photo.picth.viewmodel.LogoutViewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val viewModel by viewModels<LogoutViewModel>()
    val binding get() = _binding!!
    var isIndex: Int = 0
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
        println("token=== ${AppController.mInstance.getAuth()}")
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBack()
            }
        })

        viewModel.logoutResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    //showLoading()
                }

                is BaseResponse.Success -> {
                    // stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    //stopLoading()
                }
            }
        }
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
        toggle!!.drawerArrowDrawable.color = ContextCompat.getColor(this, R.color.white)

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
            if (id == R.id.nav_pass) {
                // Handle the action
                startNewActivity(ForgotPasswordActivity::class.java)

            }
            if (id == R.id.nav_Share) {
                // Handle the action
                CommonMethod.shareApp(this)
            }
            if (id == R.id.nav_app_use) {
                // Handle the action
                startNewActivity(LearnHowToUseActivity::class.java)
            }
            if (id == R.id.nav_Connecct) {
                // Handle the action
                openWhatsAppWithMessage(this, "Connect with us")
            }
            if (id == R.id.nav_pass) {
                // Handle the action
                startNewActivity(ForgotPasswordActivity::class.java)
            }
            if (id == R.id.nav_logout) {
                // Handle the action
                customExitDialog()
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun customExitDialog() {
        // creating custom dialog
        val dialog = Dialog(this@MainActivity)

        // setting content view to dialog
        dialog.setContentView(R.layout.custom_logout_dialog)

        // getting reference of TextView
        val dialogButtonYes = dialog.findViewById(R.id.textViewYes) as TextView
        val dialogButtonNo = dialog.findViewById(R.id.textViewNo) as TextView

        // click listener for No
        dialogButtonNo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //dismiss the dialog

                dialog.dismiss()
            }
        })

        // click listener for Yes
        dialogButtonYes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // dismiss the dialog
                // and exit the exit
                dialog.dismiss()
                doLogin()
            }
        })

        // show the exit dialog
        dialog.show()
    }

    private fun navigateToHome() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }

    fun doLogin() {
//        val  mobileNumber = binding.etMobileNumber.text.toString()
//        val  password = binding.etPassword.text.toString()
        val accesstoken = SessionManager.getToken(this)
        val refreshtoken = SessionManager.getRefreshToken(this)
        if (accesstoken != null) {
            if (refreshtoken != null) {
                viewModel.LogoutUser(accessToken = accesstoken, refreshToken = refreshtoken)
            }
        }


    }

    fun doSignup() {

    }

//    fun showLoading() {
//        binding.prgbar.visibility = View.VISIBLE
//        binding.labelLogin.visibility = View.GONE
//    }
//
//    fun stopLoading() {
//        binding.prgbar.visibility = View.GONE
//        binding.labelLogin.visibility = View.VISIBLE
//
//    }

    fun processLogin(data: LogoutResponse?) {
        showToast("Successfully Logout" )
        //if (data?.message == "User details fetch successfully") {
        //data?.message?.let { SessionManager.saveAuthToken(this, it) }
        SessionManager.clearData(this)
        navigateToHome()
        //}
    }

    fun processError(msg: String?) {
        showToast("went something wrong")
    }

    fun showToast(sText: String) {
        val toast = Toast.makeText(this, sText, Toast.LENGTH_LONG)
        var inflater: LayoutInflater = getLayoutInflater();
        var toastRoot: View = inflater.inflate(R.layout.toast, null)
        toast.setView(toastRoot)


        // set a message
        var text: TextView = toastRoot.findViewById<View>(R.id.tvToast) as TextView
        text.setText(sText)

        toast.setGravity(
            Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM,
            0, 0)
        toast.setDuration(Toast.LENGTH_SHORT)
        toast.show()
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