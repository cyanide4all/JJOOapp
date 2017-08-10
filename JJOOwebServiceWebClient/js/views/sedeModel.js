/**
 * Renderiza una fila de la tabla de resultados, se corresponde con un modelo
 */
var SedeModelView = Backbone.View.extend({
	/**
	 * Tag asociado a esta vista
	 */
	tagName : 'tr',
	/**
	 * Plantilla html asociada a la vista.
	 */
	template : null,

	editarSedeTemplate : null,

	editarSedeView : null,
	/**
	 * Lista de eventos dom manejados por esta vista
	 */
	events : {
		"click .eliminar" : "eliminar",
		"click .editar" : "editar"
	},

	/**
	 * Inicializador de la clase. Establece los atributos model, template
	 *
	 * @param options.
	 *            Es un hash con los siguientes elementos { model : value, template: value }
	 */
	initialize : function(options) {
		this.template = options.template;
		this.editarSedeTemplate = options.editarSedeTemplate;
	},

	eliminar : function(event) {
		var url = CONTEXT_PATH + '/sedes/' + this.model.attributes.ANYO + '/' + this.model.attributes.ID_TIPO_JJOO;
		modelo = this.model;
		Backbone.ajax({
			url: url,
    	type: 'DELETE',
			success: function(result) {
				modelo.destroy();
			}
		});
	},

	editar : function(event) {
		event.preventDefault();
		if (this.editarSedeView) {
			this.editarSedeView.destroy();
		}

		var url = CONTEXT_PATH + '/ciudades/nombres';
		var editarSedeTemplate = this.editarSedeTemplate
		var model = this.model
		Backbone.ajax({
			url: url,
    	type: 'GET',
			success: function(result) {
				this.editarSedeView = new EditarSedeView({
					editarSedeTemplate : editarSedeTemplate,
					sede : model,
					ciudades : result
				});
				this.editarSedeView.render();
			}
		});
		//Esto siguiente va en una funcion dentro de success

	},
	/**
	 * Crea un nodo dom con la plantilla html y los datos del país (this.model).
	 *
	 * @return Devuelve la instancia sobre la que se ejecuta la función.
	 */
	render : function() {
		$(this.el).html(this.template(this.model.toJSON()));
		return this
	}
});
