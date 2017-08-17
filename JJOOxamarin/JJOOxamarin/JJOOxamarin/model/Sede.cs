using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JJOOxamarin.model
{
    public class Sede
    {
        public int id_ciudad { get; private set; }
        public int anyo { get; private set; }
        public int tipo_jjoo { get; private set; }
        public String descripcion_tipo { get; private set; }

        public Sede(int id_ciudad, int anyo, int tipo_jjoo, String descripcion_tipo="")
        {
            this.id_ciudad = id_ciudad;
            this.anyo = anyo;
            this.tipo_jjoo = tipo_jjoo;
            this.descripcion_tipo = descripcion_tipo;
        }

       
    }
}
