package com.example.myapplicationtask.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplicationtask.R
import com.example.myapplicationtask.databinding.FragmentRegisterBinding
import com.example.myapplicationtask.model.User
import com.example.myapplicationtask.ui.activity.MainActivity
import com.example.myapplicationtask.utils.getNavOptions
import com.example.myapplicationtask.utils.toast
import com.example.myapplicationtask.viewmodel.UserViewModal
import me.ibrahimsn.lib.PhoneNumberKit


class RegisterFragment : Fragment(R.layout.fragment_register) {


    private var fragmentBinding: FragmentRegisterBinding? = null
    lateinit var viewModal: UserViewModal
    private lateinit var user: User
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegisterBinding.bind(view)
        fragmentBinding = binding
        viewModelInit()
        mobileNumberInit()
        textAgree()


    }

    private fun mobileNumberInit() {
        val phoneNumberKit = PhoneNumberKit.Builder(requireContext())
            .setIconEnabled(true)
            .build()

        fragmentBinding?.userMobileNumber?.let { phoneNumberKit.attachToInput(it, "lb") }
        phoneNumberKit.setupCountryPicker(
            requireActivity() as MainActivity,
            searchEnabled = true
        ) // Requires activity context

        val parsedNumber = phoneNumberKit.parsePhoneNumber(
            number = "03123123",
            defaultRegion = "lb"
        )


    }

    private fun viewModelInit() {
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[UserViewModal::class.java]


        viewModal.userData.observe(this.viewLifecycleOwner, Observer { usertask ->
            if (usertask != null) {
                user = usertask
                fragmentBinding?.userFirstName?.setText(usertask.userFirstName)
                fragmentBinding?.userLastName?.setText(usertask.userLastName)
                fragmentBinding?.userMobileNumberEt?.setText(usertask.userMobileNumber.toString())
                fragmentBinding?.userEmail?.setText(usertask.email)
                fragmentBinding?.userPassword?.setText(usertask.password)
                fragmentBinding?.userCompany?.setText(usertask.company)
            } else {
                user = User("Admin", "Test", "+961 03 122 155", "test@admin", "123", "channelpro")
                user.id = 0
                viewModal.addNote(user)
            }
        })

        fragmentBinding?.viewNext?.setOnClickListener {
            if (fragmentBinding?.userFirstName?.text.toString().isEmpty() ||
                fragmentBinding?.userLastName?.text.toString().isEmpty() ||
                fragmentBinding?.userMobileNumberEt?.text.toString().isEmpty() ||
                fragmentBinding?.userEmail?.text.toString().isEmpty() ||
                fragmentBinding?.userPassword?.text.toString().isEmpty() ||
                fragmentBinding?.userCompany?.text.toString().isEmpty()
            ) {
                this.requireContext().toast("all Field required")
            } else {
                user = User(
                    fragmentBinding?.userFirstName?.text.toString(),
                    fragmentBinding?.userLastName?.text.toString(),
                    fragmentBinding?.userMobileNumberEt?.text.toString(),
                    fragmentBinding?.userEmail?.text.toString(),
                    fragmentBinding?.userPassword?.text.toString(),
                    fragmentBinding?.userCompany?.text.toString()
                )
                user.id = 0

                viewModal.updateUser(user)
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeActivity, null, getNavOptions)
                requireActivity().finish()
            }

        }


    }

    private fun textAgree() {
        val formattedText =
            "I accept the <a href='http://foo.com'> Terms condition</a> and <a href='http://foo.com'>Privacy Policies</a>"
        fragmentBinding?.textAcceptRegister?.text =
            HtmlCompat.fromHtml(formattedText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }


}