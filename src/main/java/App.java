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

            String name = request.params("name");
            String cause = request.params("cause");
            Integer size = (Integer.parseInt(request.params("size")));

            Squad newTeam = new Squad(name, size, cause);
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

        get("squads/:id/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
			Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
			model.put("squad", squad);
			model.put("template", "templates/hero-form.vtl");
			return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        //Create heroes and add them to a squad
    }
}