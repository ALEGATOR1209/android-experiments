package ua.alegator1209.core_ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.AnimRes
import androidx.appcompat.app.AppCompatActivity
import ua.alegator1209.core.common.Router
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity(), Router {
    private lateinit var binding: ActivityMainBinding
    protected val baseApp: BaseApplication get() = application as BaseApplication
    abstract val Stage.fragment: BaseFragment
    abstract val backStack: MutableList<Stage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    back()
                }
            }
        )

        setContentView(binding.root)
    }

    private fun changeFragment(
        fragment: BaseFragment,
        animation: AnimationSet? = null,
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (animation != null) setCustomAnimations(animation.enter, animation.exit)
            replace(R.id.container, fragment)
        }.commit()
    }

    private sealed class AnimationSet(
        @AnimRes val enter: Int,
        @AnimRes val exit: Int,
    ) {
        object Forward : AnimationSet(R.anim.slide_from_right, R.anim.slide_to_left)
        object Backward : AnimationSet(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    override fun goTo(stage: Stage, saveToBackStack: Boolean) {
        if (saveToBackStack) backStack += stage
        changeFragment(stage.fragment, AnimationSet.Forward)
    }

    override fun returnTo(stage: Stage) {
        when (val i = backStack.lastIndexOf(stage)) {
            -1 -> {
                backStack.clear()
                backStack += stage
                changeFragment(stage.fragment, AnimationSet.Backward)
                return
            }
            backStack.lastIndex -> {
                return
            }
            else -> {
                for (j in i + 1..backStack.lastIndex) backStack.removeAt(j)
                changeFragment(stage.fragment, AnimationSet.Backward)
            }
        }
    }

    override fun replaceTo(stage: Stage) {
        backStack[backStack.lastIndex] = stage
        changeFragment(stage.fragment)
    }

    override fun back() {
        val previousStage = backStack.removeLastOrNull()

        if (previousStage == null || backStack.isEmpty()) {
            finish()
            return
        }

        changeFragment(backStack.last().fragment, AnimationSet.Backward)
    }
}
