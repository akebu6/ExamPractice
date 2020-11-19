package com.myrabohuche.exampractice.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(R.id.drawerLayout)
        //supportActionBar!!.elevation= 0F
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = this.findNavController(R.id.myNavHostFragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(navView, navController)

    }
    private fun dismissDialog(dialog: androidx.appcompat.app.AlertDialog) {
        dialog.dismiss()
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

    fun showLoading(message: String) {
        binding.loadingLayoutContainer.progressMessage.text = message
        binding.loadingLayoutContainer.visibility = View.VISIBLE
    }

    fun dismissLoading() {
        binding.loadingLayoutContainer.visibility = View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
                || super.onSupportNavigateUp()
    }

}
