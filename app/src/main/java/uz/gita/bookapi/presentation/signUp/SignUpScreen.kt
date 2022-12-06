package uz.gita.bookapi.presentation.signUp

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
import uz.gita.bookapi.databinding.ScreenSignUpBinding
import uz.gita.bookapi.presentation.signUp.vm.SignUpScreenViewModel
import uz.gita.bookapi.presentation.viewModels.SignUpScreenViewModelImpl

@AndroidEntryPoint
class SignUpScreen : Fragment(R.layout.screen_sign_up) {

    private val viewBinding: ScreenSignUpBinding by viewBinding(ScreenSignUpBinding::bind)
    private val viewModel: SignUpScreenViewModel by viewModels<SignUpScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnRegister.setOnClickListener {
            val firstName = viewBinding.enterFirstName.text.toString()
            val lastName = viewBinding.enterLastName.text.toString()
            val phoneNumber = viewBinding.enterPhone.text.toString()
            val password1 = viewBinding.enterPassword.text.toString()
            val password2 = viewBinding.reEnterPassword.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.openVerifyScreen(firstName, lastName, phoneNumber, password1, password2)
            }
        }


        viewBinding.imageView2.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.back()
            }
        }

        viewModel.message.onEach {
            if (it) {
                Toast.makeText(requireContext(), "true", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "ma'lumotla noto'g'ri", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

}