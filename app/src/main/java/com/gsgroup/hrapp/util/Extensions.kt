package com.gsgroup.hrapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import cn.pedant.SweetAlert.SweetAlertDialog
import com.gsgroup.hrapp.BR
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.app.BaseApplication
import com.gsgroup.hrapp.base.BaseFragment
import timber.log.Timber
import java.io.File
import java.lang.reflect.ParameterizedType
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


fun Activity.showActivity(
    destActivity: Class<out AppCompatActivity>,
    intent: Intent = Intent(this, destActivity)
) {
    this.startActivity(intent)
}

fun AppCompatActivity.findFragmentById(id: Int): Fragment? {
    supportFragmentManager.let {
        return it.findFragmentById(id)
    }
}

fun Activity.showExitDialog() {
    val dialog = SweetAlertDialog(this)
        .setContentText(getString(R.string.exit_app))
        .setConfirmText(getString(R.string.exit))
        .setConfirmClickListener { sDialog ->
            sDialog.closeDialog()
            finishAffinity()
        }
        .setCancelText(getString(R.string.cancel))
        .setCancelClickListener { sDialog ->
            sDialog.closeDialog()
        }
        .setConfirmButtonBackgroundColor(getColorFromRes(R.color.colorPrimary))
        .setCancelButtonBackgroundColor(getColorFromRes(R.color.white))
    dialog.show()
}

fun Context.getColorFromRes(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun SweetAlertDialog.closeDialog() {
    if (!AppUtil.isOldDevice())
        dismissWithAnimation()
    else
        dismiss()
}


fun <T : AndroidViewModel> T.app() = this.getApplication<BaseApplication>()
fun String.uri(): Uri? {
    return try {
        Uri.fromFile(File(this))
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Activity?.dialContactPhone(phoneNumber: String) = this?.run {
    startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))
}


fun validateData(size: Int?, progress: Boolean?): Boolean? {
    return if (progress == false)
        (size ?: 0 > 0)
    else false
}

fun validateHolder(size: Int?, progress: Boolean?): Boolean? {
    return if (progress == false)
        (size ?: 0 == 0)
    else false
}


fun String.isValidUrl(): Boolean {
    return try {
        URLUtil.isValidUrl(this) && Patterns.WEB_URL.matcher(this).matches()
    } catch (e: Exception) {
        Timber.e(e)
        false
    }
}


fun ImageView.loadImageFromURL(url: String, progressBar: ProgressBar? = null) {
//    val picasso =
//        Picasso.Builder(this.context).downloader(OkHttp3Downloader(UnsafeOkHttpClient().client))
//            .build()
//    picasso.isLoggingEnabled = true
//    picasso.load(url)
//        .error(R.drawable.ic_broken_image)
//        .placeholder(R.color.offwhite)
//        .fit()
//        .into(this, object : Callback {
//            override fun onSuccess() {
//                progressBar?.apply {
//                    visibility = View.GONE
//                }
//            }
//
//            override fun onError(e: Exception?) {
//                Timber.e("$e")
//                progressBar?.apply {
//                    visibility = View.GONE
//                }
//            }
//        })
}



inline fun <reified T : BaseFragment> FragmentActivity.replaceFragment(
    bundle: Bundle? = null
) {
    val fragment = T::class.java.newInstance()
    fragment.let { myFrag ->
        supportFragmentManager.apply {
            if (supportFragmentManager.backStackEntryCount != 0) {
                val currentFragmentTag = getBackStackEntryAt(backStackEntryCount - 1).name
                (findFragmentByTag(currentFragmentTag) as BaseFragment).let { curFrag ->
                    beginTransaction().hide(curFrag)
                }
            }
        }.commit {
            if (!AppUtil.isOldDevice())
                setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            myFrag.arguments = bundle
            if (supportFragmentManager.findFragmentByTag(myFrag.tag) == fragment) {
                Timber.e("show now")
                show(fragment)
            } else {
                Timber.e("add")
                replace(R.id.fragment_container, myFrag, myFrag.tag)
            }
            addToBackStack(myFrag::class.java.name)
        }
    }
}

fun String.removeSpaces(): String = this.replace(" ", "").trim()

fun CharSequence.removeSpaces(): CharSequence = this.toString().replace(" ", "").trim()

fun Fragment.showKeyboard(show: Boolean) {
    view?.let { activity?.showKeyboard(it, show) }
}

fun Activity.showKeyboard(show: Boolean) {
    showKeyboard(currentFocus ?: View(this), show)
}

fun Context.showKeyboard(view: View, show: Boolean) {
    with(view) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show)
            inputMethodManager.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        else
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}


fun View.initAnimation(@AnimRes animRes: Int, seconds: Int, onEndCallBack: () -> Unit = {}) {
    val animation = AnimationUtils.loadAnimation(context, animRes)
    animation.duration = seconds.toLong() * 1000
    startAnimation(animation)
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            onEndCallBack()
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
}

fun <T : Any> Any.getTClass(): Class<T> {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
}

inline fun <reified VM : ViewModel> ViewModelStoreOwner.bindViewModel(
    binding: ViewDataBinding,
    factory: ViewModelProvider.Factory? = null, body: VM.() -> Unit
): VM {
    return if (this is Fragment) {
        val vm by lazy {
            ViewModelProvider(
                this,
                factory ?: defaultViewModelProviderFactory
            )[VM::class.java]
        }
        binding.setVariable(BR.viewModel, vm)
        vm.apply(body)
    } else {
        val act = this as AppCompatActivity
        val vm by lazy {
            ViewModelProvider(
                act,
                factory ?: defaultViewModelProviderFactory
            )[VM::class.java]
        }
        binding.setVariable(BR.viewModel, vm)
        vm.apply(body)
    }
}

inline fun <T : ViewBinding> AppCompatActivity.bindView(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun <T : Any?, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(if (this is Fragment) viewLifecycleOwner else this, Observer(body))
}

inline fun <reified T : AppCompatActivity> Fragment.castToActivity(
    callback: (T?) -> Unit
): T {
    return if (requireActivity() is T) {
        callback(requireActivity() as T)
        requireActivity() as T
    } else {
        Timber.e("class cast exception, check your fragment is in the correct activity")
        callback(null)
        throw ClassCastException()
    }

}


fun View.visible(){
    visibility = View.VISIBLE
}
fun View.gone(){
    visibility = View.GONE
}
fun View.invisible(){
    visibility = View.INVISIBLE
}

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerLiveDataObserver =
                Observer<LifecycleOwner?> {
                    val viewLifecycleOwner = it ?: return@Observer

                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)