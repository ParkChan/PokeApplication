package com.poke.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment


abstract class BaseFragmentDialog<VDB : ViewDataBinding>(
    @LayoutRes
    private val resource: Int
) : DialogFragment() {

    protected lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<VDB>(
            inflater,
            resource,
            container,
            false
        ).apply {
            lifecycleOwner = this@BaseFragmentDialog.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }
}