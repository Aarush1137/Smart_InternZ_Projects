package com.example.assignment3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
            // Handle the result if needed
        }
        setContent {
            CompositionLocalProvider(LocalContext provides this@MainActivity) {
                MaterialTheme {
                    // Use Box to create a layered layout
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Background Image
                        Image(
                            painter = painterResource(id = R.drawable.background_image),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Content
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Hello Aarush!!",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp,
                                    letterSpacing = 0.15.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            var username by remember { mutableStateOf(TextFieldValue()) }
                            var password by remember { mutableStateOf(TextFieldValue()) }

                            OutlinedTextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = { /* Handle login button click */ },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Login")
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            WebsiteButtons(context = this@MainActivity)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun WebsiteButtons(context: Context) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Flipkart
        WebsiteButtonWithLogin(imageUrl = R.drawable.flipkart_logo, buttonText = "Login to Flipkart", websiteUrl = "https://www.flipkart.com/", context = context)
        Spacer(modifier = Modifier.height(16.dp)) // Add space between Amazon and Myntra

        // Amazon
        WebsiteButtonWithLogin(imageUrl = R.drawable.amazon_logo, buttonText = "Login to Amazon", websiteUrl = "https://www.amazon.in/", context = context)
        Spacer(modifier = Modifier.height(16.dp)) // Add space between Amazon and Myntra

        // Myntra
        WebsiteButtonWithLogin(imageUrl = R.drawable.myntra_logo, buttonText = "Login to Myntra", websiteUrl = "https://www.myntra.com/", context = context)
        Spacer(modifier = Modifier.height(16.dp)) // Add space between Amazon and Myntra

        // AJIO
        WebsiteButtonWithLogin(imageUrl = R.drawable.ajio_logo, buttonText = "Login to AJIO", websiteUrl = "https://www.ajio.com/", context = context)
    }
}


@Composable
fun WebsiteButtonWithLogin(imageUrl: Int, websiteUrl: String, buttonText: String, context: Context) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WebsiteButton(
            imageUrl = imageUrl
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                openWebsite(websiteUrl, context)
            }
        ) {
            Text(text = buttonText)
        }
    }
}

@Composable
fun WebsiteButton(imageUrl: Int) {
    Button(
        onClick = {},
        modifier = Modifier.size(100.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(120.dp)
            )
        }
    }
}
// ...

private fun openWebsite(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

val LocalContext = compositionLocalOf<Context> { error("No Context provided") }