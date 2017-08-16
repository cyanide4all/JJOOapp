using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JJOOxamarin.model
{
    class Olimpiada
    {
        public int id_ciudad { get; private set; }
        public int id_pais { get; private set; }
        public int valor { get; private set; }
        public String nombre_ciudad { get; private set; }
        public String nombre_pais { get; private set; }
        public String descripcion_tipo_jjoo { get; private set; }
        public int numero_veces_sede { get; private set; }

        public Olimpiada(int id_ciudad, int id_pais, int valor, string nombre_ciudad, string nombre_pais, string descripcion_tipo_jjoo, int numero_veces_sede)
        {
            this.id_ciudad = id_ciudad;
            this.id_pais = id_pais;
            this.valor = valor;
            this.nombre_ciudad = nombre_ciudad;
            this.nombre_pais = nombre_pais;
            this.descripcion_tipo_jjoo = descripcion_tipo_jjoo;
            this.numero_veces_sede = numero_veces_sede;
        }
    }
}
