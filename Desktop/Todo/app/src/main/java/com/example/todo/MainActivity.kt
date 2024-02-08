package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.ui.theme.TodoTheme

import android.util.Log
import android.view.View
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Priority
import com.amplifyframework.datastore.generated.model.Todo


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView(R.layout.mainactivity)
        }
    }

    fun fetchSession(view: View) {
        Amplify.Auth.fetchAuthSession(
            { Log.i("AmplifyQuickstart", "Auth session = $it") },
            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
        )
    }

    fun signInClicked(view: View) {
        Amplify.Auth.signIn("anjuz", "Windows@123",
            { result ->
                if (result.isSignedIn) {
                    Log.i("AuthQuickstart", "Sign in succeeded")
                } else {
                    Log.i("AuthQuickstart", "Sign in not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to sign in", it) }
        )
    }

    fun saveItemClicked(view: View) {
        val item = Todo.builder()
            .name("Build Android application")
            .priority(Priority.NORMAL)
            .build()

        Amplify.DataStore.save(item,
            { Log.i("Tutorial", "Saved item: ${item.name}") },
            { Log.e("Tutorial", "Could not save item to DataStore", it) }
        )
    }

}