using JJOOxamarin.REST;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace JJOOxamarin
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NuevaSede : ContentPage
    {
        public NuevaSede()
        {
            InitializeComponent();
            InitializeAsync();
            botonNuevo.Clicked += BotonNuevo_Clicked;
        }

        private async void BotonNuevo_Clicked(object sender, EventArgs e)
        {
            await RestConsumer.CreateSede(int.Parse(anyoInput.Text), pickerTipoJuego.SelectedIndex + 1, pickerCiudades.SelectedIndex + 1);
            Navigation.PopAsync();
        }

        private async void InitializeAsync()
        {
            pickerCiudades.ItemsSource = await RestConsumer.GetNombresCiudades();
            pickerTipoJuego.ItemsSource = await RestConsumer.GetDescripcionesTipoJJOO();
        }
    }
}