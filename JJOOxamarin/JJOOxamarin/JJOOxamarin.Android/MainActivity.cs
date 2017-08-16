using System;

using Android.App;
using Android.Content;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using JJOOxamarin.REST;
using System.Threading.Tasks;

namespace JJOOxamarin.Droid
{
	[Activity (Label = "JJOOxamarin.Android", MainLauncher = true, Icon = "@drawable/icon")]
	public class MainActivity : Activity
	{
        private TextView tv;

        protected override void OnCreate (Bundle bundle)
		{
			base.OnCreate (bundle);

			// Set our view from the "main" layout resource
			SetContentView (Resource.Layout.Main);
            initializeAsync();
        }

        private async Task initializeAsync()
        {
            tv = FindViewById<TextView>(Resource.Id.texto);

            var data = await RestConsumer.GetCiudadesCompleto();
            tv.Text = data[0]["ID_CIUDAD"];
        }
    }
}


