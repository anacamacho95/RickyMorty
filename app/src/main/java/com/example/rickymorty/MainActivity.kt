package com.example.rickymorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    val url ="https://rickandmortyapi.com/api/character/"

    // Crear una cola de solicitudes
    lateinit var cola: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cola = Volley.newRequestQueue(this)


        pruebaGetPersonajesAllInfo()
        pruebaGetPersonajesNombre()
        pruebaGetPersonajesNomId()
    }

    fun pruebaGetPersonajesAllInfo() {
        val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val documents = response.getJSONArray("results")
                for (i in 0 until documents.length()) {
                    val item = documents.getJSONObject(i)
                    Log.d("AllInfo", "All: $item")
                }
            },
            Response.ErrorListener { error ->
                Log.e("AllInfo", "Error: $error")
            })
        cola.add(solicitud)
    }


    fun pruebaGetPersonajesNombre() {
        val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val documents = response.getJSONArray("results")
                for (i in 0 until documents.length()) {
                    val item = documents.getJSONObject(i)
                    val nombre = item.getString("name")
                    val especie = item.getString("species")
                    Log.d("NomEsp", "Nombre: $nombre -- Especie: $especie")

                }
            },
            Response.ErrorListener { error ->
                Log.e("NomEsp", "Error: $error")
            })
        cola.add(solicitud)
    }



    fun pruebaGetPersonajesNomId() {
            val id = "2"
            val url = "https://rickandmortyapi.com/api/character/$id"
            val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    try {
                        val nombre = response.getString("name")
                        val iden = response.getString("id")

                        if (iden == id) {
                            Log.d("IdNombre", "Nombre: $nombre")
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Log.e("IdNombre", "Error: $error")
                })

            cola.add(solicitud)

    }
}