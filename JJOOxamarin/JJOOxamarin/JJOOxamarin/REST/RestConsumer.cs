using Java.IO;
using Java.Util;
using JJOOxamarin.model;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;

namespace JJOOxamarin.REST
{
    public static class RestConsumer
    {
        private static String baseRestURL = "http://172.26.80.76:8080/";

        public static async Task<List<Olimpiada>> GetCiudadesCompleto()
        {
            List<Olimpiada> toret = null;
            toret = await GetResource("olimpiadas");
            return toret;
        }

        private static async Task<List<Olimpiada>> GetResource(string v)
        {
            HttpClient client = new HttpClient();
            var uri = new Uri(baseRestURL+v);
            var response = await client.GetAsync(uri);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var toret = JsonConvert.DeserializeObject<List<Olimpiada>>(content);
                return toret;
            }
            return new List<Olimpiada>();
        }
    }
}
