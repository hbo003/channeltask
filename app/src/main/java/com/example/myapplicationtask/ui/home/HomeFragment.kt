package com.example.myapplicationtask.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationtask.R
import com.example.myapplicationtask.databinding.FragmentHomeBinding
import com.example.myapplicationtask.model.DataConverter
import com.example.myapplicationtask.model.Options
import com.example.myapplicationtask.model.Product
import com.example.myapplicationtask.model.mproduct
import com.example.myapplicationtask.ui.home.adapter.ProductAdapter
import com.example.myapplicationtask.viewmodel.UserViewModal


class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var viewModal: UserViewModal
    private var fragmentBinding: FragmentHomeBinding? = null
    private var mPostList: MutableList<Product> = mutableListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding
        viewModelHome()



    }

    private fun initProduct() {
        val options = Options()
        options.optionName = "product"
        options.optionValues = mproduct
        viewModal.addProduct(options)
    }

    private fun viewModelHome() {

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[UserViewModal::class.java]
        viewModal.allProduct.observe(this.viewLifecycleOwner, Observer { mProduct ->
            if (mProduct != null) {
                val x = DataConverter().toOptionValuesList(mProduct.toString())
                val mProductAdapter = x?.let { ProductAdapter(it, requireContext(), this) }
                fragmentBinding?.rvHome?.adapter = mProductAdapter
                fragmentBinding?.rvHome?.adapter?.notifyDataSetChanged()
            } else {
                initProduct()
            }


        })

        viewModal.userData.observe(this.viewLifecycleOwner) { user ->
            if (user != null) {

                val formattedText = " <b> Hello! </b>" + user.userFirstName
                fragmentBinding?.nameHome?.text =
                    HtmlCompat.fromHtml(formattedText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }

        }

    }
}
