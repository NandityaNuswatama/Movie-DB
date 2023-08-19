package com.nandits.movieDb.data.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.reflect.ParameterizedType

abstract class BaseBottomSheetFragment<binding : ViewBinding> : BottomSheetDialogFragment() {
    private var _binding: binding? = null
    val binding: binding get() = _binding!!

    abstract val tagName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<binding>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        _binding = method.invoke(null, layoutInflater, container, false) as binding
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initAction()
    }

    abstract fun initUI()

    abstract fun initAction()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showBottomSheet(fragmentManager: FragmentManager) {
        this.show(fragmentManager, tagName)
    }
}