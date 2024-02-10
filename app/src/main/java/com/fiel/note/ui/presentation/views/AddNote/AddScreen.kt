package com.fiel.note.ui.presentation.views.AddNote

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavHostController, viewModel: AddViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
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
                    label = { Text(text = "Titulo") },
                    value = viewModel.titulo,
                    onValueChange = {
                        viewModel.titulo = it
                    })

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Contenido") },
                    value = viewModel.contenido,
                    onValueChange = {
                        viewModel.contenido = it
                    })

                Spacer(modifier = Modifier.height(20.dp))


            }
            Column {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(8, 117, 138)),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RectangleShape,
                    onClick = {
                        viewModel.addNote()
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

    /*var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }*/

    var imageUrl by remember { mutableStateOf("") }

    val stringArray = arrayOf(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAyu0d5BmsgOXgLZcuUIWVZS1NAVfhkXtOizGR01Uesy4hGTxhn0MAeGcRF_DEWZo4FYE&usqp=CAU"
        , "https://us.123rf.com/450wm/alexutemov/alexutemov1609/alexutemov160900193/62295615-animales-mapache-cabeza-emoción-vector-avatar-linda-aislado-de-dibujos-animados-feliz-mapache.jpg"
        , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8C3u4edB9iEUj2RJJyUHjswQqj9kB8gXtC5R0V-oAl9Uu2mrcMuOb5zFrSw5RYzGt-wM&usqp=CAU"
        , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNfOer9eQbJvThcDmbTwCHEiltODbJGJHXugvdjva74wULoUYXZa44s3AdXKZNSQvSAmM&usqp=CAU"
        , "https://c8.alamy.com/compes/2m9mnmn/aislado-lindo-raton-avatar-personaje-vector-2m9mnmn.jpg"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-4Egaj2u8lfmRlwMnwmQKmggOFYnZ3zZahA&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTW1kj_kWQbya9GUrPvy8SD-mtFlM2_z4v5Lg&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQd3y4zsU6iY2Bd3aVFMJmfLZNRW4vM7qz-1g&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTC3QsPKSTzGl8JxxIx0IiSu2Jwh2yz1QJMfw&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6ol_1foTEG2r2TCXto5BAm_4M92fHHX4exA&usqp=CAU"
        , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUFiqXEWEWwHB3hl0B1ZOFIKogz2m76vf9RA&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGf69kDCpckGaoQ3fDkJq7Pid6LfhRg7VlLA&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSw9pfz-7lQxkwhH0QFnA51mLGdAqPI0z2HIw&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkJif48RH-a6F5PdStdk4w7evwF_li_oaEbg&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZpfgnMrN-AREsick5LQsCUKWCryx_PbWfgA&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRG8DZWbW7rWVFLNnN4x9QiG6mEgVAKgHwOrw&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgMgLyI6KgWere_PKpwVTNv2ECDSsq6yJIJA&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTV8NyAlp1_8ULkItz8JL2JC6R-9m_x6BFkAA&usqp=CAU"
        ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnmBvRNSQKaG59US-Ny6brEJcyYmKEWNOkJA&usqp=CAU"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
/*
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->

                /*TextField(value =""+ imageUri,
                    onValueChange = {
                    })*/

                viewModel.imageUrl =""+ imageUri

                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(20.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
*/
        if(!imageUrl.isNullOrEmpty()) {

            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(20.dp)
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
                viewModel.imageUrl =stringArray.get(randomImage)
                imageUrl=stringArray.get(randomImage)
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




