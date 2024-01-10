package com.example.dicket

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicket.data.dao.UserDao
import com.example.dicket.data.database.DicketDatabase
import com.example.dicket.ui.theme.DicketTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : ComponentActivity() {
    lateinit var database: DicketDatabase
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeDatabase()
        performDummyQuery()

        setContent {
            DicketTheme {
                DicketApp()
            }
        }
    }

    private fun initializeDatabase() {
        database = DicketDatabase.getInstance(this)
        userDao = database.userDao()
    }

    private fun performDummyQuery() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = userDao.getAllUsers()
            Log.d("DicketDatabase", "Ergebnis der Dummy-Abfrage in MainActivity: $users")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DicketTheme {
        DicketApp()
    }
}