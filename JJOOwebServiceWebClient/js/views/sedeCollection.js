/**
 * Esta clase representa la vista (y controlador) asociados a la lista de sedes.
 */
var SedeCollectionView = Backbone.View.extend({
	/**
	 * Elemento dom asociado a esta vista
	 */
	el : $('#contenido'),
	/**
	 * Plantilla html asociada a esta vista
	 */
	template : null,

	nuevaSedeTemplate : null,

	editarSedeTemplate : null,
	/**
	 * Plantilla html asociada a la vista de un sede individual
	 */
	sedeModelTemplate : null,
	/**
	 * Información de los sedes
	 */
	sedeCollection : null,

	events : {
		"click #botonNuevo" : "nuevaSede"
	},

	/**
	 * Inicializador de la clase. Establece los atributos collection, template, sedeModelTemplate
	 *
	 * @param options.
	 *            Es un hash con los siguientes elementos { collection : value, template: value, sedeModelTemplate: value}
	 */
	initialize : function(options) {

		this.template = options.template;
		this.sedeModelTemplate = options.sedeModelTemplate;
		this.sedeCollection = options.collection;
		this.editarSedeTemplate = options.editarSedeTemplate;
		this.nuevaSedeTemplate = options.nuevaSedeTemplate;
		this.listenTo(this.sedeCollection, 'reset', this.render);
		this.listenTo(this.collection, 'destroy', this.renderCollection);
		this.listenTo(this.collection, 'change', this.renderCollection);
	},

	/**
	 * Crea un nodo dom con la plantilla html (this.template) y los datos de los países (this.collection).
	 *
	 * @return Devuelve la instancia sobre la que se ejecuta la función.
	 */
	render : function() {
		this.recuperarsedes(this.renderCollection, this.error);
	},

	renderCollection : function() {
		$(this.el).html(this.template());
		_.each(this.collection.models, function(value) {
			$('#filas').append(new SedeModelView({
				model : value,
				template : this.sedeModelTemplate,
				editarSedeTemplate : this.editarSedeTemplate
			}).render().el);
		}, this);

		return this;
	},

	error : function() {
		alert('Se ha producido un error al recuperar la lista de sedes.');
	},

	recuperarsedes : function(success, error) {
		this.sedeCollection.fetch({
			success : _.bind(success, this),
			error : _.bind(error, this)
		});
	},

	nuevaSede : function () {
		//Esto debería ser una tarea asíncrona compuesta. Investigar más adelante
		var url1 = CONTEXT_PATH + '/ciudades/nombres';
		var aux = this
		Backbone.ajax({
			url: url1,
    	type: 'GET',
			success: function(result) {
				var url2 = CONTEXT_PATH + '/tipoJJOO/descripciones';
				var ciudades = result
				var self  = aux
				Backbone.ajax({
					url : url2,
					type : 'GET',
					success : function(result){
						this.nuevaSedeView = new NuevaSedeView({
							template : self.nuevaSedeTemplate,
							ciudades : ciudades,
							tipos : result,
							collection : self //Esto hay que mirarlo bien porque mejor sería mandar solo render
						})
						this.nuevaSedeView.render()
					}
				})
			}
		});
	},

	hide : function() {
		$(this.el).empty();
	}
});
