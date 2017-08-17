using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using JJOOxamarin.REST;
using System.Threading.Tasks;

namespace JJOOxamarin.Droid
{
    [Activity(Label = "SedesManagement")]
    public class SedesManagement : Activity
    {
        private ListView lista;
        private Button botonNuevaSede;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.SedesManagement);
            lista = FindViewById<ListView>(Resource.Id.listaSedes);
            botonNuevaSede = FindViewById<Button>(Resource.Id.botonNuevaSede);

            #pragma warning disable CS4014 // Ya que no se esperaba esta llamada, la ejecución del método actual continúa antes de que se complete la llamada
            InitializeAsync();
            #pragma warning restore CS4014 // Ya que no se esperaba esta llamada, la ejecución del método actual continúa antes de que se complete la llamada

        }

        private async Task InitializeAsync()
        {
            //Await bloquea el hilo asincrono hasta la respuesta
            var data = await RestConsumer.GetSedesAsync();

            //Inicializa y renderiza la lista
            //lista.Adapter = new SedesAdapter(this, data);
        }
    }
}