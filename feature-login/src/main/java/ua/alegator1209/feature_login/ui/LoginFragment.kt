package ua.alegator1209.feature_login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core.common.Stage
import ua.alegator1209.core.domain.model.User
import ua.alegator1209.core_ui.BaseFragment
import ua.alegator1209.feature_login.databinding.FragmentLoginBinding
import ua.alegator1209.feature_login.di.LoginComponentProvider

class LoginFragment : BaseFragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private var provider: LoginComponentProvider = LoginComponentProvider {
        error("Provider not set!")
    }

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provider.provideLoginComponent().inject(viewModel)

        with(binding) {
            tokenEdx.addTextChangedListener {
                viewModel.token = it?.toString() ?: ""
            }

            loginBtn.setOnClickListener { login() }
        }
    }

    private fun disableInput() = with(binding) {
        tokenEdx.isEnabled = false
        loginBtn.isEnabled = false
    }

    private fun enableInput() = with(binding) {
        tokenEdx.isEnabled = true
        loginBtn.isEnabled = true
    }

    private fun login() = with(binding) {
        disableInput()
        viewModel.login()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this@LoginFragment::handleSuccess, this@LoginFragment::handleError)
    }

    private fun handleError(error: Throwable) {
        enableInput()
        error.printStackTrace()
        shortToast(error.localizedMessage ?: "Unexpected error")
    }

    private fun handleSuccess(user: User) {
        enableInput()
        router.goTo(Stage.Profile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(provider: LoginComponentProvider) = LoginFragment().also {
            it.provider = provider
        }
    }
}
