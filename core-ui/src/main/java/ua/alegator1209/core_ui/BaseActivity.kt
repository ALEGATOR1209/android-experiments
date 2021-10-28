package ua.alegator1209.core_ui

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.appcompat.app.AppCompatActivity
import ua.alegator1209.core.common.Router
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity(), Router {
    private lateinit var binding: ActivityMainBinding
    protected val baseApp: BaseApplication get() = application as BaseApplication
    abstract val Stage.fragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun changeFragment(
        fragment: BaseFragment,
        animation: AnimationSet? = null,
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            if (animation != null) setCustomAnimations(animation.enter, animation.exit)
        }.commit()
    }

    private sealed class AnimationSet(
        @AnimRes val enter: Int,
        @AnimRes val exit: Int,
    ) {
        object Forward : AnimationSet(R.anim.slide_from_right, R.anim.slide_to_left)
        object Backward : AnimationSet(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    override fun goTo(stage: Stage) {
        changeFragment(stage.fragment, AnimationSet.Forward)
    }

    override fun returnTo(stage: Stage) {
        changeFragment(stage.fragment, AnimationSet.Backward)
    }

    override fun replaceTo(stage: Stage) {
        changeFragment(stage.fragment)
    }
}
