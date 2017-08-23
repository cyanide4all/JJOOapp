using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using JJOOxamarin.REST;
using JJOOxamarin.model;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using System.Diagnostics;

namespace JJOOxamarin
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ManageSedes : ContentPage
    {
        public ManageSedes()
        {
            InitializeComponent();
            SedesList.ItemSelected += (sender, e) =>
            {
                var toModify = (Sede)((ListView)sender).SelectedItem;
                ModificarSede(toModify);
                ((ListView)sender).SelectedItem = null;
            };
            InitializeAsync();
        }

        private async void InitializeAsync()
        {
            SedesList.ItemsSource = await RestConsumer.GetSedes();
        }

        private async void NuevaSede(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new NuevaSede());
        }
        private async void OnDelete(object sender, EventArgs e)
        {
            Sede toDelete = (Sede)((MenuItem)sender).CommandParameter;
            await RestConsumer.DeleteSede(toDelete);
            //AQUI PUEDE ESPERARSE UNA RESPUESTA POSITIVA Y REACCIONAR CON UN DISPLAYALERT
            SedesList.ItemsSource = await RestConsumer.GetSedes();
        }

        private async Task ModificarSede(Sede sede)
        {
            await Navigation.PushAsync(new ModificarSede(sede));
        }

        protected override void OnAppearing()
        {
            base.OnAppearing();
            InitializeAsync();
        }
    }
}