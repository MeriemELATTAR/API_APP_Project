package com.example.android_api_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    // Liste pour stocker les utilisateurs récupérés de l'API
    var userList = arrayListOf<User>()

    // URL de l'API pour obtenir des utilisateurs (actuellement, c'est une fausse URL "https://reqres.in/api/users", vous devrez la remplacer par la vraie URL)
    private val apiNews = "https://reqres.in/api/users"

    // RecyclerView pour afficher la liste des utilisateurs
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation de la RecyclerView
        recyclerView = findViewById(R.id.recyclerView)

        // Initialisation de la file d'attente de requêtes Volley
        val reqQueue : RequestQueue = Volley.newRequestQueue(this)

        // Création d'une requête JSON pour obtenir des données d'utilisateurs depuis l'API
        val request = JsonObjectRequest(Request.Method.GET, apiNews, null, { res ->
            // Traitement de la réponse JSON avec succès

            // Affichage du numéro de page de la réponse dans les logs
            Log.d("Volley Sample", res.getString("page"))

            // Récupération de la liste d'utilisateurs du champ "data" de la réponse
            val jsonArray = res.getJSONArray("data")

            // Parcours de la liste JSON pour créer des objets User et les ajouter à la liste userList
            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val user = User(
                    jsonObj.getInt("id"),
                    jsonObj.getString("email"),
                    jsonObj.getString("first_name"),
                    jsonObj.getString("last_name"),
                    jsonObj.getString("avatar"),
                )

                userList.add(user)
                Log.d("Volley Sample", userList.toString())
            }

            // Configuration du layoutManager et de l'adapter de la RecyclerView
            recyclerView?.layoutManager = LinearLayoutManager(this)
            recyclerView?.adapter = UserAdapter(userList)

        }, { err ->
            // Traitement des erreurs de la requête
            Log.d("Fail", err.message.toString())
        })

        // Ajout de la requête à la file d'attente Volley
        reqQueue.add(request)
    }
}

// Classe de données représentant un utilisateur
data class User(
    var id: Int,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String
)
