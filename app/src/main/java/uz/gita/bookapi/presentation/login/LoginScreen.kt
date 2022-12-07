package uz.gita.bookapi.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.bookapi.R
import uz.gita.bookapi.data.remote.dto.auth.request.SignInRequest
import uz.gita.bookapi.databinding.ScreenLogInBinding
import uz.gita.bookapi.presentation.login.vm.LoginScreenViewModel
import uz.gita.bookapi.presentation.viewModels.LoginScreenViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_log_in) {

    private val viewBinding: ScreenLogInBinding by viewBinding(ScreenLogInBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tvNew.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.openSignUpScreen()
            }
        }

        viewBinding.btnConfirm.setOnClickListener {
            val number = viewBinding.enterPhone.text.toString()
            val password = viewBinding.enterPassword.text.toString()
                viewModel.openMainScreen(SignInRequest(number, password))
        }

        viewModel.message.onEach {
            if (it) {
                Toast.makeText(requireContext(), "true", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "ma'lumotla xato", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


}