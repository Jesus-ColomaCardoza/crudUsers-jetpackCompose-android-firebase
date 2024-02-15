package com.fiel.note.ui.presentation.views.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.fiel.note.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    var userLogin by remember { mutableStateOf("") }

    Scaffold(
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Log in",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                AsyncImage(
                    model = "https://cdn-icons-png.flaticon.com/512/11832/11832458.png",
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .shadow(5.dp, CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "User Name") },
                    value = viewModel.username,
                    onValueChange = {
                        viewModel.username = it
                    })

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    visualTransformation = PasswordVisualTransformation('*'),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Password") },
                    value = viewModel.password,
                    onValueChange = {
                        viewModel.password = it
                    })

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(Color(8, 117, 138)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = {
                        viewModel.Login()

                    }) {
                    Text(
                        text = "Log in",
                        color = Color.White,
                        modifier = Modifier.padding(7.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(Color(170, 24, 65, 255)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = {
                        navController.navigate(Screens.SignupScreen.route)
                    }) {
                    Text(
                        text = "Sign up",
                        color = Color.White,
                        modifier = Modifier.padding(7.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            //Error Message
            userLogin = viewModel.isUser
            if (userLogin == "login") {
                navController.navigate(Screens.HomeScreen.route)
            } else if (userLogin == "no-login") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(5.dp, Color(240, 96, 96, 255))
                        .padding(10.dp, 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        tint = Color(240, 96, 96, 255),
                        imageVector = Icons.Default.Info, contentDescription = ""
                    )
                    Text(
                        text = "Usuario o Contraseña incorrectos",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
