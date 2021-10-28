package ua.alegator1209.core_ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import ua.alegator1209.core.common.Router

abstract class BaseFragment : Fragment() {
    protected val baseActivity: BaseActivity get() = requireActivity() as BaseActivity
    protected val router: Router get() = baseActivity

    protected fun shortToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun shortToast(@StringRes res: Int) = shortToast(getString(res))
}
