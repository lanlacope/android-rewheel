package io.github.lanlacope.compose.ui.text.toolbar

import android.os.Build
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus

@Composable
fun rememberDynamicTextToolbar(): DynamicTextToolbar {
    val view = LocalView.current
    return remember { DynamicTextToolbar(view = view) }
}

@Stable
class DynamicTextToolbar(
    private val view: View,
) : TextToolbar {

    private var actionMode: ActionMode? = null
    private val menuActions = emptyMap<Int, TextToolbarAction>().toMutableMap()

    fun addAction(
        title: String,
        action: () -> Unit,
        order: Int = Menu.NONE
    ) {
        menuActions.put(View.generateViewId(), TextToolbarAction(title, action, order))
        actionMode?.invalidate()
    }

    fun clearActions() {
        menuActions.clear()
        actionMode?.invalidate()
    }

    override val status: TextToolbarStatus
        get() = if (actionMode == null) TextToolbarStatus.Hidden else TextToolbarStatus.Shown

    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
        // return if already shown
        if (actionMode != null) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val callback = object : ActionMode.Callback2() {
                override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {

                    menuActions.forEach { (id, action) ->
                        menu.add(Menu.NONE, id, action.order, action.title)
                    }
                    return true
                }
                override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                    menu.clear()
                    menuActions.forEach { (id, action) ->
                        menu.add(Menu.NONE, id, action.order, action.title)
                    }
                    return true
                }
                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                    menuActions[item.itemId]?.onAction?.invoke()
                    mode.finish()
                    return true
                }
                override fun onDestroyActionMode(mode: ActionMode) {
                    mode.hide(0)
                    actionMode = null
                }
                override fun onGetContentRect(mode: ActionMode, view: View, outRect: android.graphics.Rect) {
                    outRect.set(
                        rect.left.toInt(),
                        rect.top.toInt(),
                        rect.right.toInt(),
                        rect.bottom.toInt()
                    )
                }
            }
            actionMode = view.startActionMode(callback, ActionMode.TYPE_FLOATING)
        } else {
            val callback = object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {

                    menuActions.forEach { (id, action) ->
                        menu.add(Menu.NONE, id, action.order, action.title)
                    }
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                    menu.clear()
                    menuActions.forEach { (id, action) ->
                        menu.add(Menu.NONE, id, action.order, action.title)
                    }
                    return true
                }

                override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                    menuActions[item.itemId]?.onAction?.invoke()
                    mode.finish()
                    return true
                }

                override fun onDestroyActionMode(mode: ActionMode) {
                    actionMode = null
                }
            }
            view.startActionMode(callback)
        }
    }

    override fun hide() {
        actionMode?.finish()
        actionMode = null
    }
}

private data class TextToolbarAction(
    val title: String,
    val onAction: () -> Unit,
    val order: Int
)
