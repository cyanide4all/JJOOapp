/**
 * Modelo para olimpiadas individuales.
 */
var OlimpiadaModel = Backbone.Model.extend({
	/**
	 * definiendo urlRoot no será necesario asociar a un modelo a ninguna colección para poder sincronizarlo
	 * con el servidor.
	 */
	urlRoot: CONTEXT_PATH + '/olimpiadas',
	/**
	 * Lista de atributos mínimos que tendrán todas las instancias de olimpiadaModel.
	 */
	defaults: {
		ID_CIUDAD : 999,
		NOMBRE_CIUDAD : 'N/A',
		ID_PAIS: 999,
		NOMBRE_PAIS : 'N/A',
		VALOR: 999,
		DESCRIPCION_TIPO : 'N/A',
		NUMERO_VECES_SEDE : 999
	}

});
