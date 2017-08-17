using System;

using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using JJOOxamarin.REST;
using System.Threading.Tasks;
using JJOOxamarin.Droid.views;

namespace JJOOxamarin.Droid
{
	[Activity (Label = "JJOOxamarin.Android", MainLauncher = true, Icon = "@drawable/icon")]
	public class MainActivity : Activity
	{
        private ListView lista;
        private Button botonSedes;

        protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			// Set our view from the "main" layout resource
			SetContentView (Resource.Layout.Main);

            lista = FindViewById<ListView>(Resource.Id.lista);
            botonSedes = FindViewById<Button>(Resource.Id.button1);

            botonSedes.Click += BotonSedes_Click;

            #pragma warning disable CS4014 // Ya que no se esperaba esta llamada, la ejecución del método actual continúa antes de que se complete la llamada
            InitializeAsync();
            #pragma warning restore CS4014 // Ya que no se esperaba esta llamada, la ejecución del método actual continúa antes de que se complete la llamada
        }

        private void BotonSedes_Click(object sender, EventArgs e)
        {
            StartActivity(typeof(SedesManagement));
        }

        private async Task InitializeAsync()
        {            
            //Await bloquea el hilo asincrono hasta la respuesta
            var data = await RestConsumer.GetCiudadesCompleto();

            //Inicializa y renderiza la lista
            lista.Adapter = new OlimpiadasAdapter(this, data);
        }
    }
}


