/**
 * Clase par el manejo de listas de olimpiadas
 */
var OlimpiadaCollection = Backbone.Collection.extend({
	/**
	 * url en el servidor para manejar las peticiones rest/json generadas por backbone
	 */
	url : CONTEXT_PATH + '/olimpiadas',
	/**
	 * Modelo asociado a esta Collection
	 */
    model: OlimpiadaModel
});
