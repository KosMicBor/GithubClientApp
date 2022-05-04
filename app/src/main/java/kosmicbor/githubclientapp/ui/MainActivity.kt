package kosmicbor.githubclientapp.ui

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.ui.loginscreen.LoginFragment
import kosmicbor.githubclientapp.ui.userprofilescreen.ProfileFragment

class MainActivity : AppCompatActivity(R.layout.activity_main), LoginFragment.LoginController {


    companion object {
        private const val USER_LOGIN = "USER_LOGIN"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            openLoginFragment()
        }
    }

    private fun openLoginFragment() {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_container_framelayout, LoginFragment())
            .commit()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus

            if (view is TextInputEditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    view.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun openProfileScreen(login: String) {
        val bundle = Bundle()

        bundle.putString(USER_LOGIN, login)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_container_framelayout, ProfileFragment.newInstance(bundle))
            .addToBackStack("ProfileScreen")
            .commit()
    }
}