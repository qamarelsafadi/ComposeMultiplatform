package com.qamar.composemultiplatform


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qamar.composemultiplatform.components.CategoryTabs

@Composable
internal fun App() {
    HomeContent()
}

@Composable
 internal fun LoginUi() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LocalImage(
            imageResourceName = "plant1.png",
            contentDescription = null,
            modifier = Modifier.size(35.dp).clickable {
            }
        )
        Text(
            text = "Login",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Handle login button click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Log in")
        }
    }
}

@Composable
internal fun HomeContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Planty App",
            color = Color.Black,
            modifier = Modifier.padding(start = 52.dp, top = 50.dp)
        )
        CategoryTabs()
        Spacer(modifier = Modifier.weight(1f))

        // Footer
        Row(
            Modifier
                .fillMaxWidth()
                .requiredHeight(150.dp)
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "TAKE CARE",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(1f)
                            .height(150.dp),
                    )
                }
            }
        }
    }
}