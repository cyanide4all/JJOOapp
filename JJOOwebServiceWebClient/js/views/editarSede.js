
var EditarSedeView = Backbone.View.extend({

	/**
	 * Punto de enganche en el árbol dom
	 */
	el : $('#mensajes'),
	modal : '#EditarSede',
	comboCiudades : "#comboCiudades",

	/**
	 * Sede mostrado y (posiblemente) actualizado.
	 */
	sede : null,

	events : {
		"click #botonModificar" : "modificarModelo",
		"hidden.bs.modal #editarSede" : "destroy"
	},

	/**
	 * Incializa las instancias estableciendo la plantilla de este view.
	 *
	 * @param options.
	 *            Es un hash con un único elemento {editarSedeTemplate: plantilla}
	 */
	initialize : function(options) {
		this.template = options.editarSedeTemplate;
		this.sede = options.sede;
		this.ciudades = options.ciudades;
	},

	/**
	 * Crea un nodo dom con la plantilla html y los datos del país establecido.
	 *
	 * @return Devuelve la instancia sobre la que se ejecuta la función.
	 */
	render : function() {
		this.$el.html(this.template(this.sede.toJSON()));

		for (i = 0; i < this.ciudades.length; i++){
			if(this.ciudades[i] == this.sede.attributes.NOMBRE_CIUDAD){
				$(this.comboCiudades).append("<option value='"+i+"' selected>"+this.ciudades[i]+"</option>")
			}else{
				$(this.comboCiudades).append("<option value='"+i+"'>"+this.ciudades[i]+"</option>")
			}
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

	modificarModelo : function() {
		var url = CONTEXT_PATH + '/sedes/' + this.sede.attributes.ANYO + '/' + this.sede.attributes.ID_TIPO_JJOO;
		var data = parseInt($(this.comboCiudades).find('option:selected').val())+1;
		var nuevaSede = $(this.comboCiudades).find('option:selected').text();
		var sede = this.sede;
		Backbone.ajax({
			url: url,
			headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    	},
			method: 'PUT',
			data: JSON.stringify(data),
			success: function(result) {
				sede.set({ "NOMBRE_CIUDAD": nuevaSede})
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
