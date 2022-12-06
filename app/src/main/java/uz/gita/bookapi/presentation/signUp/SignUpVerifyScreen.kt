package uz.gita.bookapi.presentation.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.bookapi.R
import uz.gita.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import uz.gita.bookapi.databinding.ScreenSignUpVerifyBinding
import uz.gita.bookapi.presentation.signUp.vm.VerifyScreenViewModel
import uz.gita.bookapi.presentation.viewModels.VerifyScreenViewModelImpl

@AndroidEntryPoint
class SignUpVerifyScreen : Fragment(R.layout.screen_sign_up_verify) {

    private val viewBinding: ScreenSignUpVerifyBinding by viewBinding(ScreenSignUpVerifyBinding::bind)
    private val viewModel: VerifyScreenViewModel by viewModels<VerifyScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnCheck.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val code = viewBinding.smsCodeView.enteredCode
                viewModel.checkCode(SignUpVerifyRequest(code))
            }
        }
    }

}