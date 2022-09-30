package com.example.acronymmeaningapp.presentation.screens.dashboard_fragment

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acronymmeaningapp.domain.models.LongForm
import com.google.android.material.snackbar.Snackbar

object DashboardFragmentBinding {
    @JvmStatic
    @BindingAdapter("bind:acronymMeaningsViewState")
    fun bindAcronymMeaningsToRVAdapter(
        recyclerView: RecyclerView,
        acronymMeaningsViewState: List<LongForm>?
    ) {
        val adapter = if (recyclerView.adapter == null) {
            AcronymMeaningsRVAdapter().also { recyclerView.adapter = it }
        } else {
            recyclerView.adapter as AcronymMeaningsRVAdapter
        }
        adapter.submitList(acronymMeaningsViewState)
    }

    @JvmStatic
    @BindingAdapter(
        value = ["bind:exceptionMessage", "bind:snackActionText", "bind:snackAction"],
        requireAll = false
    )
    fun bindExceptionMessageToSnackBar(
        viewGroup: ViewGroup,
        exceptionMessage: String?,
        snackActionText: String?,
        snackAction: (() -> Unit)?
    ) {
        if (!exceptionMessage.isNullOrEmpty()) {
            Snackbar
                .make(viewGroup, exceptionMessage, Snackbar.LENGTH_LONG)
                .setAction(snackActionText ?: "") { snackAction?.invoke() }
                .show()
        }
    }

}