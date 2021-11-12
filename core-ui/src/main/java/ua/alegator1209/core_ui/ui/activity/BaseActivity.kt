package ua.alegator1209.core_ui.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.alegator1209.core.common.AppRouter
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core_ui.R
import ua.alegator1209.core_ui.animation.AnimationSet
import ua.alegator1209.core_ui.databinding.ActivityMainBinding
import ua.alegator1209.core_ui.ui.application.BaseApplication
import ua.alegator1209.core_ui.ui.fragments.FeatureFragment

abstract class BaseActivity : AppCompatActivity(), AppRouter {
    private lateinit var binding: ActivityMainBinding
    protected val baseApp: BaseApplication get() = application as BaseApplication
    abstract val Stage.fragment: FeatureFragment
    abstract val backStack: MutableList<Stage>

    var onBackPressed: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun changeFragment(
        fragment: FeatureFragment,
        animation: AnimationSet? = null,
    ) {
        supportFragmentManager.beginTransaction().apply {
            if (animation != null) setCustomAnimations(animation.enter, animation.exit)
            replace(R.id.container, fragment)
        }.commit()
    }

    override fun goTo(navigable: Stage, saveToBackStack: Boolean) {
        if (saveToBackStack) backStack += navigable
        changeFragment(navigable.fragment, AnimationSet.Forward)
    }

    override fun returnTo(navigable: Stage) {
        when (val i = backStack.lastIndexOf(navigable)) {
            -1 -> {
                backStack.clear()
                backStack += navigable
                changeFragment(navigable.fragment, AnimationSet.Backward)
                return
            }
            backStack.lastIndex -> {
                return
            }
            else -> {
                for (j in i + 1..backStack.lastIndex) backStack.removeAt(j)
                changeFragment(navigable.fragment, AnimationSet.Backward)
            }
        }
    }

    override fun replaceTo(navigable: Stage) {
        backStack[backStack.lastIndex] = navigable
        changeFragment(navigable.fragment)
    }

    override fun back() {
        val previousStage = backStack.removeLastOrNull()

        if (previousStage == null || backStack.isEmpty()) {
            finish()
            return
        }

        changeFragment(backStack.last().fragment, AnimationSet.Backward)
    }

    override fun onBackPressed() {
        onBackPressed?.invoke() ?: back()
    }
}
