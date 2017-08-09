/**
 * Clase par el manejo de listas de sedes
 */
var SedeCollection = Backbone.Collection.extend({
	/**
	 * url en el servidor para manejar las peticiones rest/json generadas por backbone
	 */
	url : CONTEXT_PATH + '/sedes',
	/**
	 * Modelo asociado a esta Collection
	 */
    model: SedeModel
});
