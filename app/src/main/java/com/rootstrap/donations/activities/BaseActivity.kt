package com.rootstrap.donations.activities

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rootstrap.ava.util.LoadingDialog
import com.rootstrap.donations.R
import com.rootstrap.donations.bus

abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null

    fun showLoader() {
        if (loadingDialog == null)
            loadingDialog = LoadingDialog(this, null)
        loadingDialog!!.show()
    }

    fun dismissLoader() {
        if (loadingDialog != null)
            loadingDialog!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoader()
    }

    override fun onResume() {
        super.onResume()
        bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        bus.unregister(this)
    }

    fun createIntentClearTask(activity: Class<*>): Intent {
        val intent = Intent(this, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        return intent
    }

    fun goToWebsite(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    fun showError(error: String? = null) {
        if (error == null) {
            Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}
