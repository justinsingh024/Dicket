package com.example.dicket.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicket.R
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(onLoginClicked: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-Mail") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = "" },
            label = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Star, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onLoginClicked(email, password) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClicked = { _, _ -> })
}