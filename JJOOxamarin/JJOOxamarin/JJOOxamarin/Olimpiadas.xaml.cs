using JJOOxamarin.model;
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
    public partial class Olimpiadas : ContentPage
    {
        public Olimpiadas()
        {
            InitializeComponent();
            InitializeAsync();
            //TODO instanciar los stacklayout desde aqui para evitar el resizing
        }

        private async void InitializeAsync()
        {
            OlimpiadasList.ItemsSource = await RestConsumer.GetCiudadesCompleto();
        }
    }
}