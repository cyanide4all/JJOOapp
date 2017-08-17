using Android.Widget;
using JJOOxamarin.model;
using System.Collections.Generic;
using Android.Views;
using System;
using JJOOxamarin.Droid;
using Android.App;

namespace JJOOxamarin.Droid.views
{
    class OlimpiadasAdapter : BaseAdapter<Olimpiada>
    {
        public List<Olimpiada> Lista { get; private set; }
        public Activity Context { get; private set; }
        public int LayoutID { get; private set; }

        public OlimpiadasAdapter(Activity context, List<Olimpiada> lista)
        {
            this.Lista = lista;
            this.Context = context;
            this.LayoutID = LayoutID;
        }

        public override Olimpiada this[int position] => this.Lista[position];

        public override int Count => this.Lista.Count;

        public override long GetItemId(int position)
        {
            return this.Lista[position].GetHashCode();
        }

        public override View GetView(int position, View convertView, ViewGroup parent)
        {
            var olimpiada = this[position];
            View view = convertView;
            if (view == null)
            {
                view = Context.LayoutInflater.Inflate(Resource.Layout.OlimpiadaView, null);
            }
            view.FindViewById<TextView>(Resource.Id.id_ciudad).Text = olimpiada.id_ciudad.ToString();
            view.FindViewById<TextView>(Resource.Id.id_pais).Text = olimpiada.id_pais.ToString();
            view.FindViewById<TextView>(Resource.Id.nombre_ciudad).Text = olimpiada.nombre_ciudad;
            view.FindViewById<TextView>(Resource.Id.nombre_pais).Text = olimpiada.nombre_pais;
            view.FindViewById<TextView>(Resource.Id.valor).Text = olimpiada.valor.ToString();
            view.FindViewById<TextView>(Resource.Id.veces_sede).Text = olimpiada.numero_veces_sede.ToString();
            view.FindViewById<TextView>(Resource.Id.tipo_jjoo).Text = olimpiada.descripcion_tipo_jjoo;
            return view;
        }
    }
}
