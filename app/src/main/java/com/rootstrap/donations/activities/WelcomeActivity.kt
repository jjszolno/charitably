package com.rootstrap.donations.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rootstrap.donations.R
import com.rootstrap.donations.controllers.UserController
import com.rootstrap.donations.databinding.ActivityWelcomeBinding
import com.rootstrap.donations.utils.FailureEvent
import com.rootstrap.donations.utils.PREF_CUERRENT_USER
import com.rootstrap.donations.utils.PREF_USER_IS_LOGGED
import com.rootstrap.donations.utils.TinyDB
import com.squareup.otto.Subscribe

class WelcomeActivity(
    var bindingView: ActivityWelcomeBinding? = null,
    val userController: UserController? = UserController()
) : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        initView()
    }

    fun initView() {
        this.bindingView!!.viewModel = this
        this.bindingView!!.step = STEPS.LOGIN
    }

    fun logIn() {
        showLoader()
        if (bindingView!!.accountPasswordValue.text.toString().isNotEmpty() &&
            bindingView!!.accountPhoneValue.text.toString().isNotEmpty()
        ) {
            userController!!.logIn(
                formatPhone(),
                bindingView!!.accountPasswordValue.text.toString()
            )
        }
    }

    fun signUp() {
        showLoader()
        if (bindingView!!.accountNameValue.text.toString().isNotEmpty() &&
            bindingView!!.accountPasswordValue.text.toString().isNotEmpty() &&
            bindingView!!.accountRepeatPasswordValue.text.toString().isNotEmpty() &&
            bindingView!!.accountPhoneValue.text.toString().isNotEmpty() &&
            bindingView!!.accountPasswordValue.text.toString() == bindingView!!.accountRepeatPasswordValue.text.toString()
        ) {
            userController!!.signUp(
                bindingView!!.accountNameValue.text.toString(),
                formatPhone(),
                bindingView!!.accountPasswordValue.text.toString()
            )
        }
    }

    fun formatPhone(): String {
        return bindingView!!.accountPhoneValue.text.toString().trim()
            .replace(" ", "")
            .replace(")", "")
            .replace("(", "")
            .replace("+", "")
            .replace("-", "")
    }

    fun goToSignUp() {
        updateStep(STEPS.SING_UP)
    }

    fun goBack() {
        updateStep(bindingView!!.step!!.previous)
    }

    fun updateStep(currentStep: STEPS) {
        bindingView!!.step = currentStep
        bindingView!!.notifyChange()
    }

    @Subscribe
    fun signUpSuccesfully(event: UserController.SuccesfulSignUp) {
        if (event.user != null) {
            TinyDB(this).putBoolean(PREF_USER_IS_LOGGED, true)
            TinyDB(this).putObject(PREF_CUERRENT_USER, event.user!!)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            dismissLoader()
            showError(getString(R.string.default_error_login))
        }
    }

    @Subscribe
    fun error(event: FailureEvent) {
        dismissLoader()
        showError(getString(R.string.default_error_login))
    }

    @Subscribe
    fun SignUpEvent(event: UserController.SignUp) {
        userController!!.completeSignUp(event.name, event.phone, event.password)
    }

    @Subscribe
    fun error(event: UserController.UserAlreadyExist) {
        dismissLoader()
        showError(getString(R.string.default_error_login_user_exist))
    }

}

enum class STEPS(val code: Int, val title: String, val previous: STEPS) {
    LOGIN(0, "Login", LOGIN),
    SING_UP(1, "Sing Up", LOGIN),
}