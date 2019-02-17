package com.example.miguelangel.actividad_exo_player;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {









    //Objeto: Para guardar la vista de tipo PlayerView.
    private PlayerView playerView; //Va a guardar el objeto de la Vista.

    //Objeto: El reproductor que va a ser una instancia de Exoplayer.
    private SimpleExoPlayer player; //Reproductor.





    //Al crear la Aplicación: onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar el objeto "playerView".
        //Cargar playerView en la variable

        playerView = findViewById(R.id.player_view);



    }//Fin del Método onCreate


    //Configuración del Exoplayer.
    //Esta configuración se va a realizar en el onStart del Ciclo de Vida
    // de la Aplicación.

    //Al Arrancar la Aplicación: Método onStart.
    @Override
    protected void onStart() {
        super.onStart();

        //Creación y Configuración del Reproductor
        player = ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());


        //Añadir el reproductor al View (PlayerView)

        playerView.setPlayer(player);//Se introduce el Reproductor denominado "player".

        //Configuración del origen de datos, el origen multimedia, con la URL del archivo
        // a reproducir en Streaming.


        //dataSource es el origen de los datos.

        //La variable "dataSourceFactory" es donde se va a guardar la instancia.

        //Se le introduce el contexto: "this" y el nombre de la aplicación "ExoPlayer".

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"Exoplayer"));




        //El Archivo Multimedia: es el origen del Medio, que en nuestro caso es una URL para Video Streaming.
        //Hay que pasarle el "origen de los Datos"
        //En createMediaSource se introduce la URL de la Aplicación Multimedia MP4.
        //Es necesario realizar un parseo de la URL con


        ExtractorMediaSource archivoMultimedia = new ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse("http://blueappsoftware.in/layout_design_android_blog.mp4"));



        //Preparar el Resproductor con el archivo que se desea reproducir.

        player.prepare(archivoMultimedia);

        //Cuando el Reproductor se encuentre preparado, ponerlo en marcha: setPlay or Ready.
        player.setPlayWhenReady(true);

    }//Fin del Método onStart


    //Cuando el Activity deja de visionarse, cerrar el Exoplayer: Método onStop


    @Override
    protected void onStop() {
        super.onStop();

        playerView.setPlayer(null); //La View No va a tener ningún player configurado (reproductor).
        player.release();
        player = null;//El propio reproductor, lo dejamos limpio.

    }

}//Fin de la Clase MainActivity
