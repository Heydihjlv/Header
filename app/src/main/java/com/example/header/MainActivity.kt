package com.example.header

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            val ListCountry = Volley.newRequestQueue(this)
            val url = "https://api.covid19api.com/dayone/country/south-africa/status/confirmed"
            val textView = findViewById<TextView>(R.id.textAp)

            var conJ = object : JsonArrayRequest(
                Method.GET, url,null,
                { respuestaJson ->
                    var lista = ""
                    for (i in 1 until respuestaJson.length()) {
                        val rec: JSONObject = respuestaJson.getJSONObject(i)
                        lista = lista +
                                "\n"+"Country : "+rec.getString("Country")+"\n"+
                                "CountryCode : "+rec.getString("CountryCode")+"\n"+
                                "Lat : "+rec.getString("Lat")+"\n"+
                                "Lon : "+rec.getString("Lon")+"\n"+
                                "Cases : "+rec.getInt("Cases")+"\n"+
                                "Status : "+rec.getString("Status")+"\n"+
                                "Date : "+rec.getString("Date")+"\n"+
                                "\n"
                    }
                    textView.text = lista
                },
                { ErrorJson ->
                    textView.text = ErrorJson.toString()
                }  )

            {
                override fun getHeaders(): Map<String, String>? {
                    val headers = HashMap<String, String>()
                    headers.put("X-Request-Id", "4ae9268e-65cd-4416-83bb-8ae521eaf438")
                    return headers
                }
            }
            ListCountry.add(conJ);


    }

}