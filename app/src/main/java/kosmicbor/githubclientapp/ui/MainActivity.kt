package kosmicbor.githubclientapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kosmicbor.githubclientapp.R
import kosmicbor.githubclientapp.ui.loginscreen.LoginFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            openLoginFragment()
        }
    }

    private fun openLoginFragment() {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_container, LoginFragment())
            .addToBackStack("LoginScreen")
            .commit()
    }
}