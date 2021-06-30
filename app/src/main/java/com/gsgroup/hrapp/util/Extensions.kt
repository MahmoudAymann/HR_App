package com.gsgroup.hrapp.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.AnimRes
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gsgroup.hrapp.BR
import com.gsgroup.hrapp.R
import com.gsgroup.hrapp.app.BaseApplication
import com.gsgroup.hrapp.base.AndroidBaseViewModel
import com.gsgroup.hrapp.base.BaseFragment
import com.gsgroup.hrapp.constants.ConstString
import com.gsgroup.hrapp.data.model.ErrorResponse
import com.gsgroup.hrapp.data.model.SearchItemInterface
import com.gsgroup.hrapp.ui.activity.details.DetailsActivity
import com.gsgroup.hrapp.util.ExceptionUtil.getErrorFromException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.File
import java.lang.reflect.ParameterizedType


fun Any?.isNull(): Boolean {
    return this == null
}


fun <T : Any> Fragment.listenForResult(key: String, callback: (T?) -> Unit) {
    //Observe the result to be set by Fragment B in the stateHandle of the currentBackStackEntry
    val currentBackStackEntry = findNavController().currentBackStackEntry
    val savedStateHandle = currentBackStackEntry?.savedStateHandle
    savedStateHandle?.getLiveData<T>(key)
        ?.observe(currentBackStackEntry, Observer { result ->
            callback(result)
        })
}

fun Fragment.setResultToFragment(key: String, it: SearchItemInterface?) {
    val savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle
    savedStateHandle?.set(key, it)
    findNavController().popBackStack()
}

fun String.stringPathToFile(app: Application): File? {
    FileManager(app.applicationContext.cacheDir).extractAndCompress(this)?.let {
        return it
    } ?: return null
}

fun File.toMultiPart(key: String): MultipartBody.Part {
    val reqFile = RequestBody.create(MediaType.parse("image/*"), this)
    return MultipartBody.Part.createFormData(
        key,
        name, // filename, this is optional
        reqFile
    )
}


fun <T : Any> T.toRequestBodyParam(): RequestBody =
    RequestBody.create(MediaType.parse("text/plain"), this.toString())


inline fun <reified T : AppCompatActivity> Activity.showActivity(
    intent: Intent = Intent()
) {
    intent.setClass(this, T::class.java)
    this.startActivity(intent)
}

inline fun <reified T : Parcelable> castToObj(obj: Parcelable?): T? {
    return if (obj is T) {
        obj
    } else
        null
}

inline fun <reified T : AppCompatActivity> Activity.showActivityWithDestination(
    @IdRes destination: Int,
    intent: Intent = Intent()
) {
    intent.apply {
        setClass(this@showActivityWithDestination, T::class.java)
        putExtra(ConstString.DESTINATION_NAME, destination)
    }
    this.startActivity(intent)
}

fun AppCompatActivity.findFragmentById(id: Int): Fragment? {
    supportFragmentManager.let {
        return it.findFragmentById(id)
    }
}

//this usually used when supporting apis below 21
fun <T : Any> AndroidBaseViewModel.requestNewCallDeferred(
    networkCall: () -> Deferred<T>,
    successCallBack: (T) -> Unit
){
    if (!AppUtil.isNetworkAvailable(app.applicationContext)) {
        postResult(Resource.message(getString(R.string.network_error)))
        return
    }
    viewModelScope.launch {
        postResult(Resource.loading())
        try {
            val res = networkCall().await() // execute
            successCallBack(res)
        } catch (e: Exception) {
            Timber.e(e)
            postResult(Resource.message(e.getErrorFromException(app)))
        }
    }
}

private fun getShownError(response: ErrorResponse?): String {
    return response?.errors?.let {
        "${it[0]}"
    } ?: "null"
}

fun Activity.restartApp() {
    showActivity<DetailsActivity>()
    finishAffinity()
}

fun Context.getColorFromRes(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
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
    Glide.with(this).load(url)
        .error(R.drawable.ic_broken_image)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Timber.e("$e")
                setImageResource(R.drawable.ic_broken_image)
//                setPadding(200, 50, 200, 50)
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                setImageDrawable(resource)
                return true
            }

        })
        .into(this)
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


@Suppress("UNCHECKED_CAST")
fun <B : ViewDataBinding> LifecycleOwner.bindView(container: ViewGroup? = null): B {
    return if (this is Activity) {
        val inflateMethod = getTClass<B>().getMethod("inflate", LayoutInflater::class.java)
        val invokeLayout = inflateMethod.invoke(null, this.layoutInflater) as B
        this.setContentView(invokeLayout.root)
        invokeLayout
    } else {
        val fragment = this as Fragment
        val inflateMethod = getTClass<B>().getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }
        val invoke: B = inflateMethod.invoke(null, layoutInflater, container, false) as B
        invoke
    }
}

fun Context.showSuccessfulDialog(message: String?, onClick: () -> Unit = {}) {
    val dialog = SweetAlertDialog(
        this,
        SweetAlertDialog.SUCCESS_TYPE
    )
        .setConfirmButton(getString(R.string.continue_)) { sDialog ->
            sDialog.closeDialog()
            onClick()
        }
        .setConfirmButtonBackgroundColor(getColorFromRes(R.color.colorPrimary))
        .setConfirmButtonTextColor(getColorFromRes(R.color.white))
    message?.let {
        dialog.setContentText(message)
    }
    dialog.show()
}

fun Context.showErrorDialog(message: String?, onClick: () -> Unit = {}) {
    SweetAlertDialog(
        this,
        SweetAlertDialog.ERROR_TYPE
    )
        .setContentText(message)
        .setConfirmButton(getString(R.string.continue_)) { sDialog ->
            sDialog.closeDialog()
            onClick()
        }
        .setConfirmButtonBackgroundColor(getColorFromRes(R.color.colorPrimary))
        .setConfirmButtonTextColor(getColorFromRes(R.color.white))
        .show()
}

fun <T : Any?, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(if (this is Fragment) viewLifecycleOwner else this, Observer {
        if (lifecycle.currentState == Lifecycle.State.RESUMED) {
            body(it)
        }
    })
}

inline fun <reified T : AppCompatActivity> Fragment.castToActivity(
    callback: (T?) -> Unit
): T? {
    return if (requireActivity() is T) {
        callback(requireActivity() as T)
        requireActivity() as T
    } else {
        Timber.e("class cast exception, check your fragment is in the correct activity")
        callback(null)
        null
    }

}


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}


fun LifecycleOwner.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
    val navController: NavController?
    val mView: View?
    if (this is Fragment) {
        navController = findNavController()
        mView = view
    } else {
        val activity = this as Activity
        navController = activity.findNavController(R.id.fragment_container_view)
        mView = currentFocus
    }
    if (canNavigate(navController, mView)) navController.navigate(directions, navOptions)
}

fun LifecycleOwner.navigateSafe(@IdRes navFragmentRes: Int, bundle: Bundle? = null) {
    val navController: NavController?
    val mView: View?
    if (this is Fragment) {
        navController = findNavController()
        mView = view
    } else {
        val activity = this as Activity
        navController = activity.findNavController(R.id.fragment_container_view)
        mView = currentFocus
    }
    if (canNavigate(navController, mView)) navController.navigate(navFragmentRes, bundle)
}

fun canNavigate(controller: NavController, view: View?): Boolean {
    val destinationIdInNavController = controller.currentDestination?.id
    // add tag_navigation_destination_id to your res\values\ids.xml so that it's unique:
    val destinationIdOfThisFragment =
        view?.getTag(R.id.tag_navigation_destination_id) ?: destinationIdInNavController

    // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
    return if (destinationIdInNavController == destinationIdOfThisFragment) {
        view?.setTag(R.id.tag_navigation_destination_id, destinationIdOfThisFragment)
        true
    } else {
        Timber.e("May not navigate: current destination is not the current fragment.")
        false
    }
}

fun Uri?.openInBrowser(context: Context) {
    this ?: return // Do nothing if uri is null

    val browserIntent = Intent(Intent.ACTION_VIEW, this)
    ContextCompat.startActivity(context, browserIntent, null)
}

fun String?.asUri(): Uri? {
    return this?.toUri()
}
