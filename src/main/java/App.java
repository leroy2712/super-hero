import java.util.Map;

import javax.xml.ws.RespectBinding;

import org.eclipse.jetty.http.MetaData.Request;

import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main (String[]args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        //create index route
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //view all squads user has created
        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("squads", Squad.all());
            model.put("template", "templates/squads.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Route with form from which user can create a new form
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/squads-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Create a new super hero squad
        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            
			String squadName = request.queryParams("name");
			String squadCause = request.queryParams("cause");
            Integer squadSize = Integer.parseInt(request.queryParams("size"));
            
            Squad newTeam = new Squad(squadName, squadSize, squadCause);
            
            model.put("template", "templates/squad-success.vtl");
            return new ModelAndView(model, layout);

            
        }, new VelocityTemplateEngine());

        //Get details about an individual hero
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squad.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/squads/:id/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
			Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
			model.put("squad", squad);
			model.put("template", "templates/hero-form.vtl");
			return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Create heroes and add them to a squad
        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
			Integer squadId = Integer.parseInt(request.queryParams("squadId"));
			Squad squad = Squad.find(squadId);
            
			String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            
            Hero superHero = new Hero(name, age, power, weakness);
            squad.addHero(superHero);

            model.put("squad", squad);
            response.redirect("/squads/" + squadId);
            
			return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}