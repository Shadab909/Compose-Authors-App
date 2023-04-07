package com.example.firstcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcompose.ui.theme.FirstComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FirstComposeTheme() {
                AuthorList(authors = SampleData.AuthorSample)
            }

        }
    }
}



data class Author(val name:String, val description:String , val pic : Int)

@Composable
fun AuthorCard(msg: Author) {
    Surface(shape = MaterialTheme.shapes.medium ,
        elevation = 6.dp ,
        modifier = Modifier.padding(all = 4.dp)
    ) {

        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(painter = painterResource(id = msg.pic),
                contentDescription = "Picture of Munshi Premchand" ,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(
                        2.dp, MaterialTheme.colors.secondary,
                        CircleShape
                    ))



            Spacer(modifier = Modifier.width(6.dp))


            Column {
                Text(text = msg.name,
                    color = MaterialTheme.colors.secondary ,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(4.dp))

                var isExpanded by remember { mutableStateOf(false) }
                val surfaceColor: Color by animateColorAsState(
                    if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
                )

                Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 2.dp ,
                    color = surfaceColor ,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                        .clickable { isExpanded = !isExpanded }
                ){
                    Text(text = msg.description,
                        modifier = Modifier.padding(all = 4.dp) ,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }

            }
        }
    }
}

@Composable
fun AuthorList(authors:List<Author>){
    LazyColumn{
        items(authors){ author ->
            AuthorCard(msg = author)
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)



@Composable
fun PreviewMessageCard(){
    FirstComposeTheme() {
        AuthorList(authors = SampleData.AuthorSample)
    }
}


