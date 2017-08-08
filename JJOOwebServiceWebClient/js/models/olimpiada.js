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
		id_ciudad : 999,
		nombre_ciudad : 'N/A',
		id_pais: 999,
		nombre_pais : 'N/A',
		valor: 999,
		tipo_jjoo : 'N/A',
		veces_sede : 999
	}

});
