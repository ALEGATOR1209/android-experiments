package ua.alegator1209.core_ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected fun shortToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun shortToast(@StringRes res: Int) = shortToast(getString(res))
}
