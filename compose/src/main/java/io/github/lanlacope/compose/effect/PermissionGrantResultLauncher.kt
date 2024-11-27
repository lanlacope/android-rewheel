package io.github.lanlacope.compose.effect

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

data class PermissionGrantResultLauncher(
    private val context: Context,
    private val permission: String,
    private val launcher: ManagedActivityResultLauncher<String, Boolean>,
    private val onAlreadyGranted: () -> Unit,
) {
    fun launch() {
        if (!isGranted()) {
            launcher.launch(permission)
        } else {
            onAlreadyGranted()
        }
    }

    private fun isGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}

@Composable
fun rememberPermissionGrantResult(
    permission: String,
    onGrant: () -> Unit,
): PermissionGrantResultLauncher {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGrant ->
        if (isGrant) {
            onGrant()
        }
    }
    return remember {
        PermissionGrantResultLauncher(
            context = context,
            permission = permission,
            launcher = launcher,
            onAlreadyGranted = onGrant
        )
    }
}