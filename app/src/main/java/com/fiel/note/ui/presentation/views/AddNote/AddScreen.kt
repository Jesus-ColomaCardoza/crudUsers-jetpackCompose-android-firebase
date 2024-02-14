package com.fiel.note.ui.presentation.views.AddNote

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.fiel.note.ui.data.avatars
import com.fiel.note.ui.presentation.navigation.Screens
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavHostController,
    viewModel: AddViewModel = hiltViewModel(),
    //state:UserAddState,
    //addUser:(String,String)->Unit
) {

    var seeMap by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(Screens.HomeScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            },
            title = {
                Text(text = "New User")
            })
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                PickImageFromGallery(viewModel)

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "User Name") },
                    value = viewModel.titulo,
                    onValueChange = {
                        viewModel.titulo = it
                    })

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Password") },
                    value = viewModel.contenido,
                    onValueChange = {
                        viewModel.contenido = it
                    })

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 0.dp, 5.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Text(text = "Add My Location",
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp)

                    Switch(
                        checked = seeMap,
                        onCheckedChange = { newSwitchState ->
                            seeMap = newSwitchState
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (seeMap) {
                    myGoogleMaps(viewModel)
                }

                Spacer(modifier = Modifier.height(20.dp))

            }
            Column {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(8, 117, 138)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = {
                        viewModel.addNote()
                        //addUser()
                        navController.popBackStack()
                    }) {
                    Text(text = "Add user",color = Color.White)
                }

            }

        }
    }
}

@Composable
fun PickImageFromGallery(viewModel: AddViewModel = hiltViewModel()) {

    var imageUrl by remember { mutableStateOf("") }

    val stringArray = avatars

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(!imageUrl.isNullOrEmpty()) {

            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(20.dp)
                    .shadow(20.dp, CircleShape)
                    .clip(CircleShape),
            contentScale = ContentScale.Crop)

        }else{
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(250.dp)
                    .background(Color(8, 117, 138, 116)),
                contentAlignment = Alignment.Center
                ){
                Icon(
                    modifier=Modifier.size(50.dp),
                    tint=Color(83, 109, 131, 255),
                    imageVector = Icons.Default.Face, contentDescription ="" )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            colors = ButtonDefaults.buttonColors(Color(8, 117, 138)),
            shape = RectangleShape,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)),
            onClick = {
                //launcher.launch("image/*")
                var randomImage=((Math.random() * 19).toInt())
                viewModel.imageUrl =stringArray[randomImage].ImageUrl
                imageUrl=stringArray[randomImage].ImageUrl
            }

        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.White,
                text = "Choose avatar")
        }
    }

}

@Composable
fun myGoogleMaps(viewModel: AddViewModel = hiltViewModel()){
    //UNP location -5.176953123533414,-80.61788197606802,
    var markerPosition by remember { mutableStateOf(LatLng(-5.176953123533414,-80.61788197606802)) }

    //values for default of latitude and longitude if the user doesn't choose his coordinates
    viewModel.latitud= markerPosition.latitude
    viewModel.longitud= markerPosition.longitude

    val zoomLevel = 15f // Nivel de zoom deseado
    val tilt = 0f // Inclinación (0 para vista desde arriba)
    val bearing = 0f // Orientación de la cámara (0 para norte)

    GoogleMap (
        modifier= Modifier
            .fillMaxWidth()
            .height(450.dp),
        onMapClick = { clickedLatLng ->
            markerPosition = clickedLatLng
            viewModel.latitud= markerPosition.latitude
            viewModel.longitud= markerPosition.longitude
        },
        cameraPositionState = CameraPositionState(
            CameraPosition(markerPosition,zoomLevel,tilt,bearing)
        )
    ) {
        Marker(position = markerPosition, title = "My location", snippet = "I am here")
    }

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Latitud") },
        value = ""+markerPosition.latitude,
        onValueChange = {
        })

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Longitud") },
        value = ""+markerPosition.longitude,
        onValueChange = {
        })
}



