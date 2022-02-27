package com.myrabohuche.exampractice.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.postDelayed
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.myrabohuche.exampractice.R
import com.myrabohuche.exampractice.databinding.ActivityMainBinding
import com.myrabohuche.exampractice.ui.fragment.dialog.LoginDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loading_indicator.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar
    private val controller by lazy { findNavController(R.id.myNavHostFragment) }
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawerLayout)
        //supportActionBar!!.elevation= 0F
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, controller, drawerLayout)

        NavigationUI.setupWithNavController(navView, controller)

    }
    private fun dismissDialog(dialog: androidx.appcompat.app.AlertDialog) {
        dialog.dismiss()
    }
    override fun onBackPressed() {
        if (controller.graph.startDestination == controller.currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(2000) {
                backPressedOnce = false
            }
        }
        else {
            super.onBackPressed()
        }
    }

    private fun dismissDialog(view: View) {
        MaterialAlertDialogBuilder(this)
            .setView(view)
            .setCancelable(true)
            .show()
            .dismiss()
    }

    fun onLogin(view: View) {
        LoginDialog().show(supportFragmentManager, "MyCustomFragment")
    }

//    fun showLoading(message: String) {
//        binding.loadingLayoutContainer.progressMessage.text = message
//        binding.loadingLayoutContainer.visibility = View.VISIBLE
//    }

//    fun dismissLoading() {
//        binding.loadingLayoutContainer.visibility = View.INVISIBLE
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
                || super.onSupportNavigateUp()
    }

}
