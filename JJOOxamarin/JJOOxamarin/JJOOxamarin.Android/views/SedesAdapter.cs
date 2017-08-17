using Android.Widget;
using JJOOxamarin.model;
using System.Collections.Generic;
using Android.Views;
using System;
using JJOOxamarin.Droid;
using Android.App;

namespace JJOOxamarin.Droid.views
{
    class SedesAdapter : BaseAdapter<Sede>
    {
        public List<Sede> Lista { get; private set; }
        public Activity Context { get; private set; }
        public int LayoutID { get; private set; }

        public SedesAdapter(Activity context, List<Sede> lista)
        {
            this.Lista = lista;
            this.Context = context;
            this.LayoutID = LayoutID;
        }

        public override Sede this[int position] => this.Lista[position];

        public override int Count => this.Lista.Count;

        public override long GetItemId(int position)
        {
            return this.Lista[position].GetHashCode();
        }

        public override View GetView(int position, View convertView, ViewGroup parent)
        {
            var sede = this[position];
            View view = convertView;
            if (view == null)
            {
                view = Context.LayoutInflater.Inflate(Resource.Layout.SedeView, null);
            }
            view.FindViewById<TextView>(Resource.Id.id_ciudad).Text = sede.id_ciudad.ToString();
            return view;
        }
    }
}
