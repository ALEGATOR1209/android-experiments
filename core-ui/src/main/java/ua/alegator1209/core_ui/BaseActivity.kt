package ua.alegator1209.core_ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.alegator1209.core_ui.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    protected val baseApp: BaseApplication get() = application as BaseApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    protected fun changeFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
