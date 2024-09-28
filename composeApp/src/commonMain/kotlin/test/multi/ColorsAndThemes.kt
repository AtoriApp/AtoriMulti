package test.multi

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import test.multi.utils.ComposeUtils.opacify

// 恩情颜色值
val tonalButtonColors
    @Composable
    get() = ButtonColors(
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.onSecondaryContainer,
        MaterialTheme.colorScheme.onSurface.opacify(0.12F),
        MaterialTheme.colorScheme.onSurface
    )
val filledButtonColors
    @Composable
    get() = ButtonColors(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.onPrimary,
        MaterialTheme.colorScheme.onSurface.opacify(0.12F),
        MaterialTheme.colorScheme.onSurface
    )

// 生成的颜色值
val primaryLight = Color(0xFF87521C)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFFFDCC1)
val onPrimaryContainerLight = Color(0xFF2E1500)
val secondaryLight = Color(0xFF735943)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFFFDCC1)
val onSecondaryContainerLight = Color(0xFF2A1706)
val tertiaryLight = Color(0xFF5A6238)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFDFE7B2)
val onTertiaryContainerLight = Color(0xFF181E00)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFFF8F5)
val onBackgroundLight = Color(0xFF221A14)
val surfaceLight = Color(0xFFFFF8F5)
val onSurfaceLight = Color(0xFF221A14)
val surfaceVariantLight = Color(0xFFF2DFD1)
val onSurfaceVariantLight = Color(0xFF51443A)
val outlineLight = Color(0xFF837469)
val outlineVariantLight = Color(0xFFD6C3B6)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF372F28)
val inverseOnSurfaceLight = Color(0xFFFEEEE3)
val inversePrimaryLight = Color(0xFFFFB778)
val surfaceDimLight = Color(0xFFE6D7CD)
val surfaceBrightLight = Color(0xFFFFF8F5)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFFFF1E8)
val surfaceContainerLight = Color(0xFFFBEBE1)
val surfaceContainerHighLight = Color(0xFFF5E5DB)
val surfaceContainerHighestLight = Color(0xFFEFE0D5)

val primaryDark = Color(0xFFFFB778)
val onPrimaryDark = Color(0xFF4C2700)
val primaryContainerDark = Color(0xFF6B3B04)
val onPrimaryContainerDark = Color(0xFFFFDCC1)
val secondaryDark = Color(0xFFE2C0A5)
val onSecondaryDark = Color(0xFF412C19)
val secondaryContainerDark = Color(0xFF5A422D)
val onSecondaryContainerDark = Color(0xFFFFDCC1)
val tertiaryDark = Color(0xFFC3CB98)
val onTertiaryDark = Color(0xFF2D330E)
val tertiaryContainerDark = Color(0xFF434A22)
val onTertiaryContainerDark = Color(0xFFDFE7B2)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF19120C)
val onBackgroundDark = Color(0xFFEFE0D5)
val surfaceDark = Color(0xFF19120C)
val onSurfaceDark = Color(0xFFEFE0D5)
val surfaceVariantDark = Color(0xFF51443A)
val onSurfaceVariantDark = Color(0xFFD6C3B6)
val outlineDark = Color(0xFF9E8E82)
val outlineVariantDark = Color(0xFF51443A)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFEFE0D5)
val inverseOnSurfaceDark = Color(0xFF372F28)
val inversePrimaryDark = Color(0xFF87521C)
val surfaceDimDark = Color(0xFF19120C)
val surfaceBrightDark = Color(0xFF413730)
val surfaceContainerLowestDark = Color(0xFF130D08)
val surfaceContainerLowDark = Color(0xFF221A14)
val surfaceContainerDark = Color(0xFF261E18)
val surfaceContainerHighDark = Color(0xFF312822)
val surfaceContainerHighestDark = Color(0xFF3C332C)

val testMultiDarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

val testMultiLightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

// 包装的一层主题，你可以理解为在毛坯房上刷油漆
@Composable
fun TestMultiTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) testMultiDarkColorScheme
    else testMultiLightColorScheme

    MaterialTheme(
        colorScheme = colors, // 颜色
        content = content // 声明的视图
    )
}