package com.photo.picth.ui.presentation.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.photo.picth.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WalletActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class WalletActivity :  AppCompatActivity(R.layout.fragment_wallet) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        supportActionBar!!.hide()

    }
}