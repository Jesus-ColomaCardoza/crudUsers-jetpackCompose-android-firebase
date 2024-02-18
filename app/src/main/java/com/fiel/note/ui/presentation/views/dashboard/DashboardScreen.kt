package com.fiel.note.ui.presentation.views.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.fiel.note.ui.presentation.navigation.Screens
import com.fiel.note.ui.presentation.views.login.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    Scaffold(
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {

                AsyncImage(
                    model = "https://github.com/Jesus-ColomaCardoza/crudUsers-jetpackCompose-android-firebase/blob/main/app/src/main/java/com/fiel/note/ui/logos/u.png?raw=true",
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp, 20.dp, 10.dp, 20.dp)
                        .size(240.dp)
                        .clip(RoundedCornerShape(10)),
                    contentScale = ContentScale.Crop,
                )

                Column {
                    Spacer(modifier = Modifier.height(20.dp))

                    AsyncImage(
                        model = "https://raw.githubusercontent.com/Jesus-ColomaCardoza/crudUsers-jetpackCompose-android-firebase/main/app/src/main/java/com/fiel/note/ui/logos/logo-unp.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .shadow(5.dp, CircleShape)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AsyncImage(
                        model = "https://raw.githubusercontent.com/Jesus-ColomaCardoza/crudUsers-jetpackCompose-android-firebase/main/app/src/main/java/com/fiel/note/ui/logos/logo-facultad.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .shadow(5.dp, CircleShape)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AsyncImage(
                        model = "https://raw.githubusercontent.com/Jesus-ColomaCardoza/crudUsers-jetpackCompose-android-firebase/main/app/src/main/java/com/fiel/note/ui/logos/logo-escuela.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .shadow(5.dp, CircleShape)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Trabajo Final Tecnología Móvil",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                lineHeight = 45.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                colors = ButtonDefaults.buttonColors(Color(8, 117, 138)),
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                onClick = {
                    navController.navigate(Screens.LoginScreen.route)
                }) {
                Text(
                    text = "Ir a Login",
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
                    navController.navigate(Screens.MembersScreen.route)
                }) {
                Text(
                    text = "Ver Integrantes",
                    color = Color.White,
                    modifier = Modifier.padding(7.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


