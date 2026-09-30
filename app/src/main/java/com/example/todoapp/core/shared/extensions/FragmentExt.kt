package com.example.todoapp.core.shared.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.loadAndCollectOnStarted(
    block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(
            Lifecycle.State.STARTED,
            block
        )
    }
}

fun Fragment.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(requireContext(),
        message,
        duration
    )
        .show()
}

fun Fragment.showDeleteDialog(
    title: String,
    message: String,
    positiveText: String,
    onConfirm: () -> Unit
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton("cancel", null)
        .setPositiveButton(positiveText) { _, _ ->
            onConfirm()
        }
        .show()
}