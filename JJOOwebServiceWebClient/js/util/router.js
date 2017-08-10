/**
 * Router que se encarga de manejar las peticiones a la página de inicio, de libros y de referencias.
 */
var AppRouter = Backbone.Router.extend({
	/**
	 * Rutas manejadas en la aplicación
	 */
	routes : {
		"" : "index",
		"sedes" : "sedes"
	},

  olimpiadaCollectionView: null,

	sedeCollectionView: null,

	currentView : null,

	initialize : function() {
    var olimpiadaCollection = new OlimpiadaCollection();
		var olimpiadaCollectionTemplate = _.template(this.loadTemplate('tablaOlimpiadas'));
		var olimpiadaModelTemplate = _.template(this.loadTemplate('filaOlimpiada'));
		this.olimpiadaCollectionView = new OlimpiadaCollectionView({
			collection : olimpiadaCollection,
			template : olimpiadaCollectionTemplate,
			olimpiadaModelTemplate : olimpiadaModelTemplate
		});

		var sedeCollection = new SedeCollection();
		var sedeCollectionTemplate = _.template(this.loadTemplate('tablaSedes'));
		var sedeModelTemplate =  _.template(this.loadTemplate('filaSede'));
		var editarSedeTemplate = _.template(this.loadTemplate('editarSede'));
		var nuevaSedeTemplate = _.template(this.loadTemplate('nuevaSede'));
		this.sedeCollectionView = new SedeCollectionView({
			collection : sedeCollection,
			template : sedeCollectionTemplate,
			editarSedeTemplate : editarSedeTemplate,
			sedeModelTemplate : sedeModelTemplate,
			nuevaSedeTemplate : nuevaSedeTemplate
		});

	},

	/**
	 * Muestra la página de inicio
	 */
	index : function() {
		this.changeView(this.olimpiadaCollectionView);
	},
	sedes : function(){
		this.changeView(this.sedeCollectionView);
	},

	changeView : function(newView) {
		if (newView && this.currentView && newView != this.currentView) {
			this.currentView.hide();
			this.currentView = newView;
			this.currentView.render();
		} else if (newView) {
			this.currentView = newView;
			this.currentView.render();
		}
	},

	/**
	 * Carga una plantilla html indicándole el nombre del archivo sin extensión. La función buscará la plantilla en el
	 * directorio web/static/js/libros/templates y utilizará como extensión del archivo ".tmpl"
	 */
	loadTemplate : function(name) {
		var url = "templates/" + name + '.html';

		var template = '';
		$.ajax({
			url : url,
			method : 'GET',
			async : false,
			dataType : 'html',
			success : function(data) {
				template = data;
			}
		});
		return template;
	}
});

// Creamos el router
var app = new AppRouter();
// Creamos la barra de navegación
new NavBar({
	el : document.getElementById('nav-item-container')
});
// Iniciamos el gestor de histórico
Backbone.history.start();
