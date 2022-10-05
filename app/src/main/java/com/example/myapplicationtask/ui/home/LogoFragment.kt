package com.example.myapplicationtask.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplicationtask.R
import com.example.myapplicationtask.databinding.FragmentLogoBinding
import com.example.myapplicationtask.model.Logo
import com.example.myapplicationtask.model.LogoConverter
import com.example.myapplicationtask.model.mLogo
import com.example.myapplicationtask.ui.home.adapter.LogoAdapter
import com.example.myapplicationtask.utils.getNavOptions
import com.example.myapplicationtask.viewmodel.UserViewModal


class LogoFragment : Fragment(R.layout.fragment_logo) {
    private lateinit var viewModal: UserViewModal
    private var fragmentBinding: FragmentLogoBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogoBinding.bind(view)
        fragmentBinding = binding

        viewModelHome()

    }

    private fun initLogo() {
        val logo = Logo()
        logo.logoName = "logo"
        logo.logoValues = mLogo
        viewModal.addItem(logo)
    }

    private fun viewModelHome() {

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[UserViewModal::class.java]
        viewModal.allItem.observe(this.viewLifecycleOwner, Observer { mlogo ->
            if (mlogo != null) {
                val x = LogoConverter().toLogoValuesList(mlogo.toString())
                val mLogoAdapter = x?.let { LogoAdapter(it, requireContext()) }
                fragmentBinding?.rvLogo?.adapter = mLogoAdapter
                fragmentBinding?.rvLogo?.adapter?.notifyDataSetChanged()
            } else {
                initLogo()
            }
        })
        fragmentBinding?.icLogo?.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment, null, getNavOptions)
        }
    }
}