package com.example.charigochi.screeens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charigochi.R
import com.example.charigochi.ui.theme.Typography

@Composable
fun AboutUs() {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary
    val backgroundColor = colorScheme.background
    val onBackgroundColor = colorScheme.onBackground
    val tertiary = colorScheme.tertiary

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_goal_text),
            style = Typography.titleLarge.copy(color = onBackgroundColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.about_us_text1),
            style = Typography.bodyLarge.copy(color = onBackgroundColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(R.string.about_us_text2),
            style = Typography.bodyLarge.copy(color = onBackgroundColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Justify
        )
        // Добавляем ссылки в самом низу
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.developers_text),
                style = Typography.titleLarge.copy(color = onBackgroundColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
            val developer1 = stringResource(R.string.developer1_name)
            val developer2 = stringResource(R.string.develope2_name)
            val developer1Link = "https://github.com/nevermind322"
            val developer2Link = "https://github.com/h8watermelon"
            val linkStyle = SpanStyle(
                color = tertiary,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            )

            val annotatedText1 = buildAnnotatedString {
                pushStringAnnotation(tag = "URL", annotation = developer1Link)
                withStyle(style = linkStyle) {
                    append(developer1Link)
                }
                pop()
            }

            val annotatedText2 = buildAnnotatedString {
                pushStringAnnotation(tag = "URL", annotation = developer2Link)
                withStyle(style = linkStyle) {
                    append(developer2Link)
                }
                pop()
            }
            Text(developer1, style = Typography.bodyLarge.copy(color = onBackgroundColor))
            ClickableText(
                text = annotatedText1,
                style = Typography.bodyLarge.copy(color = tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = { offset ->
                    annotatedText1.getStringAnnotations(tag = "URL", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                            context.startActivity(intent)
                        }
                }
            )
            Text(text = developer2, style = Typography.bodyLarge.copy(color = onBackgroundColor),)
            ClickableText(
                text = annotatedText2,
                style = Typography.bodyLarge.copy(color = tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = { offset ->
                    annotatedText2.getStringAnnotations(tag = "URL", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                            context.startActivity(intent)
                        }
                }
            )
        }
    }
}