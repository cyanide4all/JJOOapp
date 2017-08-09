
var EditarSedeView = Backbone.View.extend({

	/**
	 * Punto de enganche en el árbol dom
	 */
	el : $('#mensajes'),
	modal : '#editarSede',

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
	},

	/**
	 * Crea un nodo dom con la plantilla html y los datos del país establecido.
	 *
	 * @return Devuelve la instancia sobre la que se ejecuta la función.
	 */
	render : function() {
		this.$el.html(this.template(this.sede.toJSON()));
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
		this.sede.set({"titulo": $('#tituloInput').val(), "autor" : $('#autorInput').val()})
		//this.sede.titulo = $('#tituloInput').val();
		//this.sede.autor = $('#autorInput').val();
		this.sede.save();
		$(this.modal).modal('toggle');
	}
});
