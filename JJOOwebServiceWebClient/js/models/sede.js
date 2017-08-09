/**
 * Modelo para sedes individuales.
 */
var SedeModel = Backbone.Model.extend({
	/**
	 * definiendo urlRoot no será necesario asociar a un modelo a ninguna colección para poder sincronizarlo
	 * con el servidor.
	 */
	urlRoot: CONTEXT_PATH + '/sedes',
	/**
	 * Lista de atributos mínimos que tendrán todas las instancias de sedeModel.
	 */

	defaults: {
    NOMBRE_CIUDAD : "N/A",
    ANYO : 9999,
    DESCRIPCION_TIPO : "N/A",
    ID_TIPO_JJOO : 0
	}

});
