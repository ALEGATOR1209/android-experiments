package ua.alegator1209.core_ui.ui.fragments

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.alegator1209.core_ui.ui.activity.BaseActivity
import ua.alegator1209.core_ui.ui.application.BaseApplication

abstract class BaseFragment : Fragment() {
    val baseActivity: BaseActivity get() = requireActivity() as BaseActivity
    val baseApplication: BaseApplication get() = baseActivity.application as BaseApplication

    /** Lazily creates a ViewModel that can be used by this fragment.
     * Owner of the [ViewModelProvider] is this fragment. */
    protected inline fun <reified K : ViewModel> viewModel() = lazy {
        ViewModelProvider(this)[K::class.java]
    }

    protected fun shortToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun shortToast(@StringRes res: Int) = shortToast(getString(res))
}
