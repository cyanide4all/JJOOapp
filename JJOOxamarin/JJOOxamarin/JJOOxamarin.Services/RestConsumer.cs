using JJOOxamarin.model;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Collections;

namespace JJOOxamarin.REST
{
    public static class RestConsumer
    {
        private static String baseRestURL = "http://172.26.80.76:8080/";

        public static async Task<List<Olimpiada>> GetCiudadesCompleto()
        {
            List<Olimpiada> toret = null;
            toret = await GetResource<Olimpiada>("olimpiadas");
            return toret;
        }

        public static async Task<List<Sede>> GetSedes()
        {
            List<Sede> toret = null;
            toret = await GetResource<Sede>("sedes");
            return toret;
        }

        public static async Task<List<String>> GetDescripcionesTipoJJOO()
        {
            List<String> toret = null;
            toret = await GetResource<String>("tipoJJOO/descripciones");
            return toret;
        }

        public static async Task<List<String>> GetNombresCiudades()
        {
            List<String> toret = null;
            toret = await GetResource<String>("ciudades/nombres");
            return toret;
        }

        public static async Task DeleteSede(Sede sede)
        {
            HttpClient client = new HttpClient();
            var uri = baseRestURL +"sedes/"+ sede.anyo + "/" + sede.tipo_jjoo;
            await client.DeleteAsync(uri);
        }

        public static async Task UpdateSede(Sede sede, int nuevaIdCiudad)
        {
            HttpClient client = new HttpClient();
            var uri = baseRestURL + "sedes/" + sede.anyo + "/" + sede.tipo_jjoo;
            var json = JsonConvert.SerializeObject(nuevaIdCiudad);
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            await client.PutAsync(uri, content);
        }

        public static async Task CreateSede(int anyo, int idTipo, int idCiudad)
        {
            HttpClient client = new HttpClient();
            var uri = baseRestURL + "sedes";
            var json = JsonConvert.SerializeObject(new int[3] {anyo, idTipo, idCiudad});
            var content = new StringContent(json, Encoding.UTF8, "application/json");
            await client.PostAsync(uri, content);
        }


        private static async Task<List<T>> GetResource<T>(string v)
        {
            HttpClient client = new HttpClient();
            var uri = new Uri(baseRestURL + v);
            var response = await client.GetAsync(uri);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var toret = JsonConvert.DeserializeObject<List<T>>(content);
                return toret;
            }
            return new List<T>();
        }

    }
}
