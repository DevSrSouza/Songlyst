@file:Suppress("ktlint:standard:function-naming")

package dev.srsouza.kmp.songlyst

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

public fun MainViewController(): UIViewController =
    ComposeUIViewController {
        SonglystApp()
    }
