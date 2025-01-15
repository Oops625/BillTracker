package com.griffin.core.base.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.griffin.core.base.vm.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 用于没有实现Hilt的ViewModel
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(layoutResId: Int) : HiltBaseFragment<DB>(layoutResId) {

    override val viewModel: BaseViewModel by lazy {
        createViewModel()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVmClazz(this)]
    }
}