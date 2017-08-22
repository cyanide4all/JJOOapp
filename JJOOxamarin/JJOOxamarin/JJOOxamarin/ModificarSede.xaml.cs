using JJOOxamarin.model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using JJOOxamarin.REST;

namespace JJOOxamarin
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ModificarSede : ContentPage
    {
        private Sede sede;

        public ModificarSede(Sede sede)
        {
            this.sede = sede;
            InitializeComponent();
            anyoLabel.Text = sede.anyo.ToString();
            tipoJJOOLabel.Text = sede.descripcion_tipo;
            InitializeAsync();
        }

        private async void BotonModificar_Clicked(object sender, EventArgs e)
        {
            if (pickerCiudades.SelectedItem != null)
            {
                await RestConsumer.UpdateSede(sede, pickerCiudades.SelectedIndex + 1);
                Navigation.PopAsync();
            }
        }

        private async void InitializeAsync()
        {
            pickerCiudades.ItemsSource = await RestConsumer.GetNombresCiudades();
            pickerCiudades.SelectedItem = sede.nombre_ciudad;
            botonModificar.Clicked += BotonModificar_Clicked;
        }
        
    }
}