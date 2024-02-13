package com.fiel.note.ui.presentation.views.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.fiel.note.ui.presentation.navigation.Screens
import com.fiel.note.ui.presentation.views.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val notes = viewModel.notes1.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color(8, 117, 138)),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 20.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Icon(
                                tint = Color.White,
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "",
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Users", color = Color.White)
                        }
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "",
                            modifier = Modifier.clickable() {
                                navController.navigate(Screens.LoginScreen.route)
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(notes.value) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            3.dp,
                            color = Color(8, 117, 138),
                            shape = RoundedCornerShape(20.dp)
                        )
                ) {

                    Column(modifier = Modifier.padding(10.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "#${notes.value.indexOf(it) + 1}")

                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(onClick = {
                                navController.navigate(Screens.UpdateScreen.getById(it.id))
                            }) {
                                Icon(
                                    tint = Color(15, 113, 199, 255),
                                    imageVector = Icons.Default.Edit, contentDescription = ""
                                )
                            }
                            IconButton(onClick = {
                                //viewModel.deleteNote(note = it)
                            }) {
                                Icon(
                                    tint = Color(238, 82, 34, 255),
                                    imageVector = Icons.Default.Delete, contentDescription = ""
                                )
                            }
                        }

                        Row(
                            //modifier = Modifier.fillMaxSize().background(Color.Red),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = it.imageUrl,
                                contentDescription = "avatar",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(10.dp)
                                    .shadow(5.dp, CircleShape)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Column(
                                //modifier = Modifier.fillMaxSize().background(Color.Gray),
                            ) {
                                Text(
                                    text = it.username,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.password)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = ""+it.latitude)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = ""+it.longitude)
                                Spacer(modifier = Modifier.height(4.dp))

                            }
                        }

                    }
                }
            }
        }
    }
}