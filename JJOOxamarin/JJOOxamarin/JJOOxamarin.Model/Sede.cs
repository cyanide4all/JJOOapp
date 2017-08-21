using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JJOOxamarin.model
{
    public class Sede
    {
        public string nombre_ciudad { get; private set; }
        public int anyo { get; private set; }
        public int tipo_jjoo { get; private set; }
        public string descripcion_tipo { get; private set; }

        public Sede(string nombre_ciudad, int anyo, int id_tipo_jjoo, string descripcion_tipo = "")
        {
            this.nombre_ciudad = nombre_ciudad;
            this.anyo = anyo;
            this.tipo_jjoo = id_tipo_jjoo;
            this.descripcion_tipo = descripcion_tipo;
        }
    }
}
