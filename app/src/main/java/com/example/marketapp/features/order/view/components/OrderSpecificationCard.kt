package com.example.marketapp.features.order.view.components

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.marketapp.R
import com.example.marketapp.core.ui.theme.*
import com.example.marketapp.features.order.view.viewmodel.order.OrderState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSpecificationCard (
    state: OrderState = OrderState(),
    onDescriptionChange : (String) -> Unit = {},
    onImageSelection: () -> Unit = {},
    onFileSelection: () -> Unit = {},
    onRecording: () -> Unit = {},
) {
    val context: Context = LocalContext.current
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 4.dp,
            bottomEnd = 50.dp,
            bottomStart = 4.dp
        ),
        border = BorderStroke(
            .1.dp,
            color = if (isSystemInDarkTheme()) Neutral400 else Neutral600
        ),
        color = if (isSystemInDarkTheme()) Neutral800.copy(alpha = 0.2f) else Neutral200.copy(
            alpha = 0.2f
        )
    ) {



        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            BasicTextField(
                value = state.description,
                onValueChange = { onDescriptionChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 15.dp),
                minLines = 10,
                maxLines = 10,
                textStyle = TextStyle(
                    fontFamily = Lato,
                    fontSize = 16.sp,
                    color = if(isSystemInDarkTheme()) Neutral100 else Neutral900
                ),
                decorationBox = { innerTextField ->
                    Box {
                        if (state.description.isEmpty()) {
                            Text(
                                text = context.getString(R.string.please_enter_order_description),
                                style = TextStyle(
                                    fontFamily = Lato,
                                    fontSize = 16.sp,
                                    color = if(isSystemInDarkTheme()) Neutral600 else Neutral400
                                )
                            )
                        }
                        innerTextField()
                    }
                },
            )



            if(state.files.isNotEmpty() || state.images.isNotEmpty() || state.records.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                    thickness = .2.dp,
                    color = if (isSystemInDarkTheme()) Neutral500 else Neutral500

                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = context.getString(R.string.attachments_to_send),
                    style = TextStyle(
                        fontFamily = Lato,
                        fontSize = 16.sp,
                        color = Secondary
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
            }




            if(state.images.isNotEmpty()){
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    repeat(state.images.size) { index ->

                        if(index == 0){
                            Spacer(modifier = Modifier.width(15.dp))
                        }

                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            painter = rememberImagePainter(data = state.images[index], builder = {
                                placeholder(R.drawable.photo) // Placeholder resource while loading
                                error(R.drawable.photo_error) // Error resource if loading fails
                            }),
                            contentDescription = null, //
                            contentScale = ContentScale.Crop
                        )
                        if(index != state.images.size){
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        if(index == state.images.size - 1){
                            Spacer(modifier = Modifier.width(15.dp))
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }



            if(state.files.isNotEmpty()){
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    repeat(state.files.size) { index ->

                        if(index == 0){
                            Spacer(modifier = Modifier.width(15.dp))
                        }

                        Surface(
                            modifier = Modifier
                                .size(100.dp),
                            shape = RoundedCornerShape(4.dp),
                            color = if(isSystemInDarkTheme()) Neutral800 else Neutral200
                        ){
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(
                                        id = R.drawable.file
                                    ),
                                    contentDescription = null,
                                    tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp)
                                        .padding(start = 0.dp)
                                    ,
                                    text = getFileName(context,state.files[index]),
                                    style = TextStyle(
                                        fontFamily = Lato,
                                        color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                                        fontSize = 12.sp
                                    ),
                                    maxLines = 2
                                )



                            }

                        }

                        if(index != state.images.size){
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        if(index == state.images.size ){
                            Spacer(modifier = Modifier.width(15.dp))
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }



            Column {

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = .2.dp,
                    color = if (isSystemInDarkTheme()) Neutral400 else Neutral600

                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = CenterVertically
                ) {

                    Surface(
                        modifier = Modifier
                            .size(32.dp)
                            .align(CenterVertically)
                            .clickable {
                                scope.launch {
                                    delay(1000)
                                    showBottomSheet = true

                                }
                            },
                        shape = CircleShape,
                        color = Secondary
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Center
                        ) {
                            Icon(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(
                                    id = R.drawable.pin
                                ),
                                contentDescription = null,
                                tint = Neutral100
                            )
                        }

                    }


                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = context.getString(R.string.send_attachments),
                        style = TextStyle(
                            fontFamily = Lato,
                            color = if (isSystemInDarkTheme()) Neutral600 else Neutral400,
                            fontSize = 14.sp
                        )
                    )

                }
            }

        }

    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.height(150.dp),
            onDismissRequest = {

                scope.launch {
                    sheetState.hide()
                    showBottomSheet = false
                }

            },
            sheetState = sheetState,
            containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
        ) {

            Row() {
                Spacer(modifier = Modifier.width(30.dp))

                Surface(
                    modifier = Modifier
                        .size(50.dp)
                        .align(CenterVertically)
                        .clickable {
                            onImageSelection()
                        },
                    shape = CircleShape,
                    color = Color(0xffA207BB)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Center
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(
                                id = R.drawable.piciture
                            ),
                            contentDescription = null,
                            tint = Neutral100
                        )
                    }

                }

                Spacer(modifier = Modifier.width(20.dp))

                Surface(
                    modifier = Modifier
                        .size(50.dp)
                        .align(CenterVertically)
                        .clickable {
                            onFileSelection()
                        },
                    shape = CircleShape,
                    color = Color(0xff61B7FF)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Center
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(
                                id = R.drawable.file
                            ),
                            contentDescription = null,
                            tint = Neutral100
                        )
                    }

                }
                Spacer(modifier = Modifier.width(20.dp))

                Surface(
                    modifier = Modifier
                        .size(50.dp)
                        .align(CenterVertically)
                        .clickable {
                            onRecording()
                        },
                    shape = CircleShape,
                    color = Color(0xffEA1796)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Center
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(
                                id = R.drawable.record
                            ),
                            contentDescription = null,
                            tint = Neutral100
                        )
                    }

                }
            }

        }
    }

}

 @SuppressLint("Range")
 fun getFileName(context: Context, uri: Uri): String {

    if (uri.scheme == "content") {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor.use {
            if (cursor != null) {
                if(cursor.moveToFirst()) {
                    if (cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME) > -1) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
        }
    }

    return uri.path?.substring(uri.path!!.lastIndexOf('/') + 1) ?: ""
}