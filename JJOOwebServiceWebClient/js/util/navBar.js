/**
 * Este código ha sido extraído del tutorial Using Twitter Bootstrap NavBars with Backbone.js
 * disponible públicamente en la url: https://gist.github.com/stephenvisser/2711454
 */
//This is the Backbone controller that manages the Nav Bar
var NavBar = Backbone.View.extend({
    initialize:function(options){
        Backbone.history.on('route',function(source, path){
            this.render(path);
        }, this);
    },
    //This is a collection of possible routes and their accompanying
    //user-friendly titles
    titles: {
        "index": "Inicio",
        "sedes": "Sedes"
    },
    events:{
        'click a':function(source) {
            var hrefRslt = source.target.getAttribute('href');
            Backbone.history.navigate(hrefRslt, {trigger:true});
            //Cancel the regular event handling so that we won't actual change URLs
            //We are letting Backbone handle routing
            return false;
        }
    },
    //Each time the routes change, we refresh the navigation
    //items.
    render:function(route){
       this.$el.empty();
       var template = _.template("<li class='<%=active%>'><a href='<%=url%>'><%=visible%></a></li>");
       for (var key in this.titles) {
           this.$el.append(template({
        	   url : key == 'index' ? '' : key,
        	   visible : this.titles[key],
        	   active : route === key ? 'active' : ''
           }));
       }
    }
});
