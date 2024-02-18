package com.fiel.note.ui.presentation.views.members

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MembersScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    Scaffold(
        topBar = { TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(Screens.DashboardScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            },
            title = {
                Text(text = "")
            }
        )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Integrantes ",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
            )

            Spacer(modifier = Modifier.height(40.dp))

            member(name = "Chinguel Rojas Nelida")
            member(name = "Coloma Cardoza Hellary Jesús")
            member(name = "Fernández Fernández Paul Alexander")
            member(name = "Yarlque Farfán Mario Leonardo")

        }

    }
}

@Composable
fun member(name:String){
    Row (verticalAlignment = Alignment.CenterVertically){

        AsyncImage(
            model = "https://cdn-icons-png.flaticon.com/512/11832/11832458.png",
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp, 20.dp, 10.dp, 20.dp)
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = name,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            lineHeight = 25.sp
        )

    }

}


