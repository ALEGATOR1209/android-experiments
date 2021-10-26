package ua.alegator1209.feature_login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.alegator1209.core_ui.BaseFragment
import ua.alegator1209.feature_login.core.domain.interactor.LoginUseCase
import ua.alegator1209.feature_login.core.domain.model.LoginCredentials
import ua.alegator1209.feature_login.databinding.FragmentLoginBinding
import ua.alegator1209.feature_login.di.LoginComponentProvider
import javax.inject.Inject

class LoginFragment : BaseFragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private var provider: LoginComponentProvider = LoginComponentProvider {
        error("Provider not set!")
    }

    @Inject lateinit var useCase: LoginUseCase

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
        provider.provideLoginComponent().inject(this)

        useCase(LoginCredentials("alegator1209", ""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Toast.makeText(requireContext(), "Completed!", Toast.LENGTH_SHORT)
                        .show()
                },
                {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }
            )
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
