
var NuevaSedeView = Backbone.View.extend({

	/**
	 * Punto de enganche en el árbol dom
	 */
	el : $('#mensajes'),
	modal : '#NuevaSede',
	comboCiudades : "#comboCiudades",
  comboTipos : "#comboTipos",
	inputAnyo : "#anyoInput",

  ciudades : null,

  tipos : null,

	events : {
		"click #botonAceptar" : "nuevoModelo",
		"hidden.bs.modal #NuevaSede" : "destroy"
	},

	/**
	 * Incializa las instancias estableciendo la plantilla de este view.
	 *
	 * @param options.
	 *            Es un hash con un único elemento {editarSedeTemplate: plantilla}
	 */
	initialize : function(options) {
		this.template = options.template;
		this.ciudades = options.ciudades;
    this.tipos = options.tipos;
		this.collection = options.collection;
	},

	/**
	 * Crea un nodo dom con la plantilla html y los datos del país establecido.
	 *
	 * @return Devuelve la instancia sobre la que se ejecuta la función.
	 */
	render : function() {
		this.$el.html(this.template());

		for (i = 0; i < this.ciudades.length; i++){
				$(this.comboCiudades).append("<option value='"+i+"'>"+this.ciudades[i]+"</option>")
		}
		for (i = 0; i < this.tipos.length; i++){
				$(this.comboTipos).append("<option value='"+i+"'>"+this.tipos[i]+"</option>")
		}
		$(this.modal).modal();
		return this;
	},

	hide : function() {
		this.$el.empty();
	},

	destroy : function() {
		this.stopListening();
		this.undelegateEvents();
		this.$el.removeData();
		$(this.modal).remove();
	},

	nuevoModelo : function() {
		var url = CONTEXT_PATH + '/sedes';
		var id_ciudad = parseInt($(this.comboCiudades).find('option:selected').val())+1;
		var id_tipo = parseInt($(this.comboTipos).find('option:selected').val())+1;
		var anyo = parseInt($(this.inputAnyo).val());
		var data = [anyo, id_tipo, id_ciudad]
		var collection = this.collection
		Backbone.ajax({
			url: url,
			headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    	},
			method: 'POST',
			data: JSON.stringify(data),
			success: function(result) {
				collection.render();//TODO hacer un trigger aqui de algo
			}
		});
		$(this.modal).modal('hide');
		/*//PARA PILLAR ID_CIUDAD
		alert(parseInt($(this.comboCiudades).find('option:selected').val())+1)
		*/
		/*//PARA PILLAR NOMBRE_CIUDAD
		$(this).find('option:selected').text();
		*/
	}
});
