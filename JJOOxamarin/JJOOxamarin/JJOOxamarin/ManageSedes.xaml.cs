using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using JJOOxamarin.REST;
using JJOOxamarin.model;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace JJOOxamarin
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ManageSedes : ContentPage
    {
        public ManageSedes()
        {
            InitializeComponent();
            
            InitializeAsync();
        }

        private async void InitializeAsync()
        {
            SedesList.ItemsSource = await RestConsumer.GetSedes();
        }

        private async void NuevaSede(object sender, EventArgs e)
        {
            //TODO
        }

        private void ViewCell_Tapped(object sender, EventArgs e)
        {

        }
    }
}