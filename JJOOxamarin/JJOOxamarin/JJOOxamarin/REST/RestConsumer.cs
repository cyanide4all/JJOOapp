using Java.IO;
using Java.Util;
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

        public static async Task<List<Dictionary<string, string>>> GetCiudadesCompleto()
        {
            List<Dictionary<String, String>> toret = null;
            toret = await GetResource("olimpiadas");
            return toret;
        }

        private static async Task<List<Dictionary<String,String>>> GetResource(string v)
        {
            HttpClient client = new HttpClient();
            var uri = new Uri(baseRestURL+v);
            var response = await client.GetAsync(uri);
            if (response.IsSuccessStatusCode)
            {
                var content = await response.Content.ReadAsStringAsync();
                var toret = JsonConvert.DeserializeObject<List<Dictionary<String, String>>>(content);
                return toret;
            }
            return new List<Dictionary<String, String>>();
        }
    }
}
