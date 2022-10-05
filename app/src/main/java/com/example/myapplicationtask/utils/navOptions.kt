package com.example.myapplicationtask.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtask.R

val getNavOptions: NavOptions
    get() = navOptions {
        anim {
            enter = R.anim.enter
            exit = R.anim.exit
        }
    }

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}