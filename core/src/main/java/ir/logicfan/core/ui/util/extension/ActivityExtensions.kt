package ir.logicfan.core.ui.util.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragmentWithFadeAnimation(@IdRes container: Int, fragment: Fragment, addToBackStack: Boolean) {
    val ft = this.supportFragmentManager.beginTransaction()
    ft.replace(container, fragment)
    if (addToBackStack) {
        ft.addToBackStack(null) // argument name is optional
    }
    ft.setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)  // add animation to replacement process
    ft.commit()
}